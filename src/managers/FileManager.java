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

import model.Term;

public class FileManager {
	private String previousFilePath;
	private static FileManager me;
	private FileManager() {
	}
	public static FileManager getInstance() {
		if(me == null) me = new FileManager();
		return me;
	}

	
	public List<Term> loadSubjectsFromFile(String fileName) {
		return null;
	}
	
	public void SaveAs(final Stage primaryStage) {
	     FileChooser fileChooser = new FileChooser();
	        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
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
	
	private void saveSubjectsToFile(String fileName) {
		Serializer serializer = new Persister();
		File file = new File(fileName);
		try {
			serializer.write(TermManager.getInstance(), file);
			previousFilePath = fileName;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
