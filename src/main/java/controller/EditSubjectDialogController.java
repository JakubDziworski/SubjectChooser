package controller;

import java.net.URL;
import java.util.*;

import com.sun.istack.internal.Nullable;
import exceptions.ExceptionHandler;
import exceptions.IllegalTermException;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import managers.SubjectManager;
import model.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.stage.Stage;
import utils.UiUtils;
import static managers.ApplicationManager.Constants;

public class EditSubjectDialogController implements Initializable {

	public static final int TIME_SEED = 15; //in minutes

	@FXML
	private Parent root;
	@FXML
	private TextField nameTextBox;
	@FXML
	private ComboBox<Subject.Type> typeComboBox;
	@FXML
	private TableView<Term> termsTableView;
	@FXML
	private TableColumn<Term,Day> dayTableColumn;
	@FXML
	private TableColumn<Term,TimeOfDay> startTableColumn;
	@FXML
	private TableColumn<Term,TimeOfDay> endTableColumn;
    @FXML
    private TableColumn<Term,String> teacherTableColumn;

	private Stage stage;
	private Subject subject;
	private ObservableList<Term> terms;
    private List<Term> invalidTerms;

    EventHandler<TableColumn.CellEditEvent<Term,Day>> dayChanged = event -> {
        Term term = event.getRowValue();
        term.getStart().setDay(event.getNewValue());
        term.getEnd().setDay(event.getNewValue());
    };

	private EventHandler<TableColumn.CellEditEvent<Term,TimeOfDay>> startTimeOfTermChanged = event -> {
        final Term term = event.getRowValue();
        final TimeOfDay newValue = event.getNewValue();
        tryToUpdateTerm(term, newValue, null);
	};

	EventHandler<TableColumn.CellEditEvent<Term,TimeOfDay>> endTimeOfTermChanged = event -> {
        final Term term = event.getRowValue();
        final TimeOfDay newValue = event.getNewValue();
        tryToUpdateTerm(term,null, newValue);
	};

    private void tryToUpdateTerm(Term term, @Nullable TimeOfDay startTime,@Nullable TimeOfDay endTime) {
        try {
            if (startTime != null) term.getStart().setTimeOfDay(startTime);
            if (endTime != null) term.getEnd().setTimeOfDay(endTime);
            Term.verifyStartBeforeEnd(term.getStart(),term.getEnd());
            invalidTerms.remove(term);
		} catch (IllegalTermException e) {
			ExceptionHandler.getInstance().handleException(e);
			if(!invalidTerms.contains(term)) {
				invalidTerms.add(term);
			}
		}
	}

    private EventHandler<TableColumn.CellEditEvent<Term,String>> teacherChanged = event -> {
        Term term = event.getRowValue();
        term.setTeacher(event.getNewValue());
    };


    @Override
	public void initialize(URL location, ResourceBundle resources) {
		typeComboBox.setItems(FXCollections.observableArrayList(Subject.Type.values()));
		stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void setSubject(Subject subject) {
		this.subject = subject;
		nameTextBox.setText(subject.getName());
		typeComboBox.setValue(subject.getSubjectType());
		terms = FXCollections.observableArrayList(SubjectManager.getInstance().getSubjects().stream().filter(s -> s == subject).findFirst().get().getTerms());
		invalidTerms = new ArrayList<>();
        initTable();
		initCells();
		initCellEditedCallbacks();
	}
	
	private void initTable() {
		dayTableColumn.setCellValueFactory(param -> new SimpleObjectProperty<Day>(param.getValue().getStart().getDay()));
		startTableColumn.setCellValueFactory(param -> new SimpleObjectProperty<TimeOfDay>(param.getValue().getStart().getTimeOfDay()));
		endTableColumn.setCellValueFactory(param -> new SimpleObjectProperty<TimeOfDay>(param.getValue().getEnd().getTimeOfDay()));
        teacherTableColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTeacher()));
		termsTableView.setItems(terms);
	}

	private void initCells() {
		ObservableList<Day> subjectTypes = FXCollections.observableArrayList(Day.values());
		ObservableList<TimeOfDay> possibleTimes = FXCollections.observableArrayList(TimeOfDay.getAllPossible(Constants.MIN_TIME,Constants.MAX_TIME, TIME_SEED));

		dayTableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(subjectTypes));
		startTableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(possibleTimes));
		endTableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(possibleTimes));
        teacherTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	}
	
	private void initCellEditedCallbacks() {
		dayTableColumn.setOnEditCommit(dayChanged);
		startTableColumn.setOnEditCommit(startTimeOfTermChanged);
		endTableColumn.setOnEditCommit(endTimeOfTermChanged);
        teacherTableColumn.setOnEditCommit(teacherChanged);
	}

	@FXML
	public void applyButtonClicked(ActionEvent event) {
        if(!invalidTerms.isEmpty()) {
            UiUtils.showInvalidTermsWarmomgPopUp(invalidTerms);
            return;
        }
		subject.setName(nameTextBox.getText());
		subject.setType(typeComboBox.getValue());
		stage.close();
		subject.setTerms(terms);
	}
	
	@FXML
	public void cancelButtonClicked(ActionEvent event) {
		stage.close();
	}
	
	@FXML
	public void addTermButtonClicked(ActionEvent event) {
		Term term = Term.DefaultTerm();
		terms.add(term);
	}
	
	@FXML
	public void deleteTermButtonClicked(ActionEvent event) {
		terms.remove(termsTableView.getSelectionModel().getSelectedItem());
	}

}
