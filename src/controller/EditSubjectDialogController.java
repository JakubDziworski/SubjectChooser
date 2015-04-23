package controller;

import java.net.URL;
import java.util.ResourceBundle;

import model.Subject;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditSubjectDialogController implements Initializable {

	private Subject subject;
	@FXML
	private TextField nameTextBox;
	@FXML
	private ComboBox<Subject.Type> typeComboBox;
	@FXML
	private Parent root;
	private Stage stage;
	
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
	}
	
	@FXML
	public void applyButtonClicked(ActionEvent event) {
		subject.setName(nameTextBox.getText());
		subject.setType(typeComboBox.getValue());
		stage.close();
	}
	
	@FXML
	public void cancelButtonClicked(ActionEvent event) {
		
	}
	
	@FXML
	public void addTermButtonClicked(ActionEvent event) {
		
	}
	
	@FXML
	public void deleteTermButtonClicked(ActionEvent event) {
		
	}

}
