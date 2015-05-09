package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import managers.SubjectManager;
import model.Subject;
import model.Term;
import model.Subject.Type;
import model.WeekDateTime;
import model.WeekDateTime.Day;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;

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
	private TableColumn<Term,String> startTableColumn;
	@FXML
	private TableColumn<Term,String> endTableColumn;
	private Stage stage;
	
	private Subject subject;
	private ObservableList<Term> terms;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		typeComboBox.setItems(FXCollections.observableArrayList( Subject.Type.values()));
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
		initCellEditedCallbacks();
	}
	
	private void initTable() {
		dayTableColumn.setCellValueFactory(param -> new SimpleObjectProperty<Day>(param.getValue().getStart().getDay()));
		startTableColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getStart().getTimeOnlyString()));
		endTableColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getEnd().getTimeOnlyString()));
		termsTableView.setItems(terms);
	}
	
	private void initCellEditedCallbacks() {
		ObservableList<Day> subjectTypes = FXCollections.observableArrayList(Day.values());
		ObservableList<String> possibleTimes = FXCollections.observableArrayList(WeekDateTime.getAllPossibleDateTimesAsString());
		
		dayTableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(subjectTypes));
		startTableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(possibleTimes));
		endTableColumn.setCellFactory(ComboBoxTableCell.forTableColumn(possibleTimes));
		
		
	}
	
	
	
	private void refreshTermsList() {
		
	}
	
	@FXML
	public void applyButtonClicked(ActionEvent event) {
		subject.setName(nameTextBox.getText());
		subject.setType(typeComboBox.getValue());
		stage.close();
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
		
	}

}
