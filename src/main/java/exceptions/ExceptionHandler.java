package exceptions;

import javafx.scene.control.Alert;
import managers.ApplicationManager;
import sun.plugin.util.UIUtil;
import utils.UiUtils;

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

    public void handleException(IllegalTermException e) {
        UiUtils.showWarningPopUp(
                ApplicationManager.Strings.DIALOG_ERROR_TITLE,
                ApplicationManager.Strings.DIALOG_ILLEGAL_TERM_HEADER,
                ApplicationManager.Strings.DIALOG_ILLEGAL_TERM_DESC);
    }

    public void handleException(IllegalTimeOfDayException e) {
        UiUtils.showWarningPopUp(
                ApplicationManager.Strings.DIALOG_ERROR_TITLE,
                ApplicationManager.Strings.DIALOG_ILLEGAL_TIME_OF_DAY_HEADER,
                ApplicationManager.Strings.DIALOG_ILLEGAL_TIME_OF_DAY_DESC);
    }

    public void handleException(IllegalHourFormatException e){
        UiUtils.showWarningPopUp(
                ApplicationManager.Strings.DIALOG_ERROR_TITLE,
                ApplicationManager.Strings.DIALOG_ILLEGAL_TIME_OF_DAY_HEADER,
                ApplicationManager.Strings.DIALOG_ILLEGAL_TIME_OF_DAY_DESC);
    }

    public void handleException(IllegalMinuteFormatException e){
        UiUtils.showWarningPopUp(
                ApplicationManager.Strings.DIALOG_ERROR_TITLE,
                ApplicationManager.Strings.DIALOG_ILLEGAL_TIME_OF_DAY_HEADER,
                ApplicationManager.Strings.DIALOG_ILLEGAL_TIME_OF_DAY_DESC);
    }
}
