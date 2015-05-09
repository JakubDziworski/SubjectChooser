package application;
	
import java.net.URL;

import managers.ApplicationManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			final String mainWindowFilePath = ApplicationManager.Resources.VIEW_MAIN_WINDOW_FILE_PATH;
			final URL mainWindowFileURL = getClass().getResource(mainWindowFilePath);
			Parent root = FXMLLoader.load(mainWindowFileURL);
			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource(ApplicationManager.Resources.STYLE_APPLICATION).toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
