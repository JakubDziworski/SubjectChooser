package utils;

import javafx.scene.control.Alert;
import managers.ApplicationManager;
import model.Term;

import java.util.Collection;

/**
 * Created by Jakub Dziworski on 2015-06-08 for purposes of subject "Zaawansowane zagadnienia programowania w Javie"
 */
public class UiUtils {
    public static void showWarningPopUp(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public static void showInvalidTermsWarmomgPopUp(Collection<Term> invalidTerms) {
        showWarningPopUp(ApplicationManager.Strings.DIALOG_ERROR_TITLE,
                ApplicationManager.Strings.DIALOG_EDIT_TERM_HEADER,
                String.format(ApplicationManager.Strings.DIALOG_EDIT_TERM_DESC_FORMAT,invalidTerms.size()));
    }
}
