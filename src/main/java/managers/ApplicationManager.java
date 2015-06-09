package managers;

public class ApplicationManager {
    public class Resources {
        public static final String STYLE_APPLICATION = "/styles/application.css";
        public static final String VIEW_MAIN_WINDOW_FILE_PATH = "/views/MainWindowGUIProject.fxml";
        public static final String VIEW_EDIT_SUBJECT_DIALOG_FILE_PATH = "/views/EditSubjectDialog.fxml";
    }

    public static class Constants {
        public static final int MIN_TIME = 60*7; //lessons can start at 7 earliest
        public static final int MAX_TIME = 60*21; //lessons can end at 21 latest
    }

    public static class Strings {
        public static final String DIALOG_ABOUT_TITLE = "About this program";
        public static final String DIALOG_ABOUT_HEADER_TEXT = "Projekt na przedmiot Zaawanansowane zagadnienia programowania z Javy";
        public static final String DIALOG_ABOUT_CONTENT_TEXT = "Autor: Jakub Dziworski. Politechnika Lodzka,Ftims, 2015";

        public static final String SUBJECT_NAME_DEFAULT = "Mathematics";

        public static final String SUBJECT_TYPE_EXERCISES = "Exercises";
        public static final String SUBJECT_TYPE_LABORATORY = "Laboratory";
        public static final String SUBJECT_TYPE_PROJECT = "Project";
        public static final String SUBJECT_TYPE_LECTURE = "Lecture";
        public static final String SUBJECT_TYPE_SEMINARY = "Seminary";
        public static final String SUBJECT_TYPE_UNDEFINED = "Unknown";

        public static final String DAY_MON = "Monday";
        public static final String DAY_TUE = "Tuesday";
        public static final String DAY_WED = "Wednesday";
        public static final String DAY_THU = "Thursday";
        public static final String DAY_FRI = "Friday";
        public static final String DAY_SAT = "Saturday";
        public static final String DAY_SUN = "Sunday";


        public static final String TEACHER_DEFAULT = "Kowalski";

        public static final String DIALOG_ERROR_TITLE = "Error";

        public static final String DIALOG_ILLEGAL_TIME_OF_DAY_HEADER = "Invalid time format.";
        public static final String DIALOG_ILLEGAL_TIME_OF_DAY_DESC = "You inserted a time which does not exists";

        public static final String DIALOG_ILLEGAL_TERM_HEADER = "Term end has to be after start!";
        public static final String DIALOG_ILLEGAL_TERM_DESC = "No changes were made.";

        public static final String DIALOG_EDIT_TERM_HEADER = "Wrong class time provided";
        public static final String DIALOG_EDIT_TERM_DESC_FORMAT = "%d invalid term(s). Correct them and try again";
    }
}
