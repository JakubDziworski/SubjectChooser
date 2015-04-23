package managers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import model.Subject;
import model.Term;

public class FileManager {
	private static String FILE_FILTER =  "*.xml";
	private static String FILE_FILTER_DESC = "XML files (*.xml)";
	
	private String previousFilePath;
	private static FileManager me;
	private FileManager() {
	}
	public static FileManager getInstance() {
		if(me == null) me = new FileManager();
		return me;
	}
	
	public void SaveAs(final Stage primaryStage) {
	     FileChooser fileChooser = new FileChooser();
	        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(FILE_FILTER_DESC,FILE_FILTER);
	        fileChooser.getExtensionFilters().add(extFilter);
	        File file = fileChooser.showSaveDialog(primaryStage);
	        if(file != null){
	        	saveSubjectsToFile(file.getAbsolutePath());
	        }
	}
	
	public void Save(final Stage primaryStage) {
		if(previousFilePath == null) {
			SaveAs(primaryStage);
			return;
		}
		saveSubjectsToFile(previousFilePath);
	}
	
	public void Load(final Stage primaryStage){
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(primaryStage);
        if(file != null){
        	loadSubjectsFromFile(file.getAbsolutePath());
        }
	}
	
	private void loadSubjectsFromFile(String fileName) {
		File file = new File(fileName);
		Serializer serializer = new Persister();
    	try {
			SubjectManager manager = serializer.read(SubjectManager.class, file);
			previousFilePath = fileName;
			for(Subject subject : manager.getSubjects()) {
				SubjectManager.getInstance().addSubject(subject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void saveSubjectsToFile(String fileName) {
		File file = new File(fileName);
		Serializer serializer = new Persister();
		try {
			serializer.write(SubjectManager.getInstance(), file);
			previousFilePath = fileName;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
