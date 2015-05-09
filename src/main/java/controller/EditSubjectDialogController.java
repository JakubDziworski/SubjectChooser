package controller;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import exceptions.ExceptionHandler;
import exceptions.IllegalTermException;
import exceptions.IllegalTimeOfDayException;
import javafx.event.EventHandler;
import managers.SubjectManager;
import model.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.stage.Stage;

public class EditSubjectDialogController implements Initializable {

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
	private Stage stage;
	
	private Subject subject;
	private ObservableList<Term> terms;

	private EventHandler<TableColumn.CellEditEvent<Term,TimeOfDay>> startTimeOfTermChanged = event -> {
		try {
			Term term = event.getRowValue();
			term.setStart(new WeekDateTime(term.getStart().getDay(), event.getNewValue()));
		} catch (IllegalTermException e) {
			ExceptionHandler.getInstance().handleException(e);
			TimeOfDay newVal = event.getNewValue();
			newVal = event.getOldValue();
		}
	};

	EventHandler<TableColumn.CellEditEvent<Term,TimeOfDay>> endTimeOfTermChanged = event -> {
		try {
			Term term = event.getRowValue();
			term.setEnd(new WeekDateTime(term.getEnd().getDay(), event.getNewValue()));
		} catch (IllegalTermException e) {
			ExceptionHandler.getInstance().handleException(e);
			TimeOfDay newVal = event.getNewValue();
			newVal = event.getOldValue();
		}
	};

	EventHandler<TableColumn.CellEditEvent<Term,Day>> dayChanged = event -> {
		Term term = event.getRowValue();
		term.getStart().setDay(event.getNewValue());
		term.getEnd().setDay(event.getNewValue());
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
		initTable();
		initCells();
		initCellEditedCallbacks();
	}
	
	private void initTable() {
		dayTableColumn.setCellValueFactory(param -> new SimpleObjectProperty<Day>(param.getValue().getStart().getDay()));
		startTableColumn.setCellValueFactory(param -> new SimpleObjectProperty<TimeOfDay>(param.getValue().getStart().getTimeOfDay()));
		endTableColumn.setCellValueFactory(param -> new SimpleObjectProperty<TimeOfDay>(param.getValue().getEnd().getTimeOfDay()));
		termsTableView.setItems(terms);
	}

	private void initCells() {
		ObservableList<Day> subjectTypes = FXCollections.observableArrayList(Day.values());
		ObservableList<TimeOfDay> possibleTimes = FXCollections.observableArrayList(TimeOfDay.getAllPossibleWithSeed(15));

		dayTableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(subjectTypes));
		startTableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(possibleTimes));
		endTableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(possibleTimes));
	}
	
	private void initCellEditedCallbacks() {
		dayTableColumn.setOnEditCommit(dayChanged);
		startTableColumn.setOnEditCommit(startTimeOfTermChanged);
		endTableColumn.setOnEditCommit(endTimeOfTermChanged);
	}

	private void refreshTerm(Term term) {
		final int index = terms.indexOf(term);
		terms.remove(index);
		terms.add(index,term);
		termsTableView.getSelectionModel().select(index);
	}

	@FXML
	public void applyButtonClicked(ActionEvent event) {
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
