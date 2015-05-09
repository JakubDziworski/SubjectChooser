package exceptions;

import javafx.scene.control.Alert;
import managers.ApplicationManager;

/**
 * Created by Jakub Dziworski on 2015-05-09 for purposes of subject "Zaawansowane zagadnienia programowania w Javie"
 */
public class ExceptionHandler {
    private static ExceptionHandler me = new ExceptionHandler();

    public static ExceptionHandler getInstance() {
        return me;
    }

    private ExceptionHandler() {

    }

    private void showErrorPopUp(String title,String headerText,String contentText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void handleException(IllegalTermException e) {
        showErrorPopUp(
                ApplicationManager.Strings.DIALOG_ERROR_TITLE,
                ApplicationManager.Strings.DIALOG_ILLEGAL_TERM_HEADER,
                ApplicationManager.Strings.DIALOG_ILLEGAL_TERM_DESC);
    }

    public void handleException(IllegalTimeOfDayException e) {
        showErrorPopUp(
                ApplicationManager.Strings.DIALOG_ERROR_TITLE,
                ApplicationManager.Strings.DIALOG_ILLEGAL_TIME_OF_DAY_HEADER,
                ApplicationManager.Strings.DIALOG_ILLEGAL_TIME_OF_DAY_DESC);
    }

    public void handleException(IllegalHourFormatException e){
        showErrorPopUp(
                ApplicationManager.Strings.DIALOG_ERROR_TITLE,
                ApplicationManager.Strings.DIALOG_ILLEGAL_TIME_OF_DAY_HEADER,
                ApplicationManager.Strings.DIALOG_ILLEGAL_TIME_OF_DAY_DESC);
    }

    public void handleException(IllegalMinuteFormatException e){
        showErrorPopUp(
                ApplicationManager.Strings.DIALOG_ERROR_TITLE,
                ApplicationManager.Strings.DIALOG_ILLEGAL_TIME_OF_DAY_HEADER,
                ApplicationManager.Strings.DIALOG_ILLEGAL_TIME_OF_DAY_DESC);
    }
}
