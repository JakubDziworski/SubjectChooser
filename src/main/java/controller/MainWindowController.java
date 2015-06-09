package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import managers.ApplicationManager;
import managers.FileManager;
import managers.SubjectManager;
import model.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;
import views.CalendarView;

public class MainWindowController implements Initializable {
	
	@FXML
	private Button editSubjectButton;
	@FXML
	private Button addSubjectButton;
	@FXML
	private Button removeSubjectButton;
	@FXML
	private Parent root;
	@FXML
	private ScrollPane timeTableScrollPane;
	@FXML
	private ListView<Subject> subjectsListView;
	private ObservableList<Subject> listViewtems;
	
	private SubjectManager.SubjectsListener subjectsListener = new SubjectManager.SubjectsListener () {
		
		@Override
		public void onSubjectRemoved(Subject subject) {
			listViewtems.remove(subject);
		}
		
		@Override
		public void onSubjectChanged(Subject subject) {
			final int index = listViewtems.indexOf(subject);
			listViewtems.remove(subject);
			listViewtems.add(index,subject);
		}
		
		@Override
		public void onSubjectAdded(Subject subject) {
			listViewtems.add(subject);
		}
	};

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listViewtems = FXCollections.observableArrayList();
		subjectsListView.setItems(listViewtems);
		SubjectManager.getInstance().addSubjectsListener(subjectsListener);
		CalendarView calendar = new CalendarView();
		timeTableScrollPane.setContent(calendar);
	}
	
	@FXML
	public void editSubjectButtonClicked(ActionEvent event) {
		if(subjectsListView.getSelectionModel().isEmpty()) return;
		final int index = subjectsListView.getSelectionModel().getSelectedIndex();
		Subject subject = listViewtems.get(index);
		
		 final String editDialogFilePath = ApplicationManager.Resources.VIEW_EDIT_SUBJECT_DIALOG_FILE_PATH;
		 final URL editDialogFileURL = getClass().getResource(editDialogFilePath);
		 try {
			 FXMLLoader loader = new FXMLLoader(editDialogFileURL);
			 Parent root = loader.load();
			 EditSubjectDialogController controller = loader.getController();
			 controller.setSubject(subject);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void addSubjectButtonClicked(ActionEvent event) {
		Subject subject = new Subject();
		SubjectManager.getInstance().addSubject(subject);
	}
	
	@FXML
	public void removeSubjectButtonClicked(ActionEvent event) {
		final int index = subjectsListView.getSelectionModel().getSelectedIndex();
		Subject subject = listViewtems.get(index);
		SubjectManager.getInstance().removeSubject(subject);
	}
	
	@FXML
	public void aboutButtonClicked(ActionEvent event) {
		PopupControl popup = new PopupControl();
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(ApplicationManager.Strings.DIALOG_ABOUT_TITLE);
		alert.setHeaderText(ApplicationManager.Strings.DIALOG_ABOUT_HEADER_TEXT);
		alert.setContentText(ApplicationManager.Strings.DIALOG_ABOUT_CONTENT_TEXT);
		alert.showAndWait();
	}
	
	
	@FXML
	public void openButtonClicked(ActionEvent event) {
		Stage currentStage = (Stage)root.getScene().getWindow();
		FileManager.getInstance().Load(currentStage);
	}
	
	@FXML
	public void saveButtonClicked(ActionEvent event) {
		Stage currentStage = (Stage)root.getScene().getWindow();
		FileManager.getInstance().Save(currentStage);
	}
	
	@FXML
	public void saveAsButtonClicked(ActionEvent event) {
		Stage currentStage = (Stage)root.getScene().getWindow();
		FileManager.getInstance().SaveAs(currentStage);
	}

	@FXML
	void resetChoicesButtonClicked(ActionEvent event) {
		SubjectManager.getInstance().getSubjects().forEach(subject -> {
			subject.setSelectedTerm(null);
		});
	}
	
	@FXML
	public void closeButtonClicked(ActionEvent event) {
	}
}
