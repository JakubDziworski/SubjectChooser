package views;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import managers.SubjectManager;
import model.Day;
import model.Subject;
import model.Term;
import model.TimeOfDay;

import java.util.*;

import static managers.ApplicationManager.Constants;

/**
 * Created by Jakub Dziworski on 2015-06-08 for purposes of subject "Zaawansowane zagadnienia programowania w Javie"
 */
public class CalendarView extends HBox {

    private static int TIME_SEED = 15;

    private SubjectManager.SubjectsListener subjectsListener = new SubjectManager.SubjectsListener() {
        @Override
        public void onSubjectAdded(Subject subject) {
            refresh();
        }

        @Override
        public void onSubjectRemoved(Subject subject) {
            refresh();
        }

        @Override
        public void onSubjectChanged(Subject subject) {
            refresh();
        }
    };


    public CalendarView() {
        SubjectManager.getInstance().addSubjectsListener(subjectsListener);
        refresh();
    }

    public void refresh() {
        getChildren().clear();
        final List<Subject> subjects = SubjectManager.getInstance().getSubjects();
        final List<Term> terms = SubjectManager.getInstance().getTerms();

        //create grid for each day
        Map<Day, GridPane> gridPerDay = new EnumMap<>(Day.class);
        for (Day day : Day.values()) {
            gridPerDay.put(day, createDayGrid(day));
        }

        //add terms to grids
        for (Subject subject : subjects) {
            if (subject.getTerms().isEmpty()) continue;
            for (Term term : subject.getTerms()) {
                final Day day = term.getStart().getDay();
                GridPane pane = gridPerDay.get(day);
                int column = getColumnForTerm(term, terms);
                int rowStart = 1+term.getStart().getTimeOfDay().getTotalInMinutes() / TIME_SEED - Constants.MIN_TIME / TIME_SEED;
                int rowEnd = term.getEnd().getTimeOfDay().getTotalInMinutes() / TIME_SEED - Constants.MIN_TIME / TIME_SEED;
                pane.add(getLayoutForTerm(subject, term), column, rowStart, 1, rowEnd - rowStart + 1);
            }
        }
    }


    /**
     * If some terms colldie with each other they need to be offsetted (different columns)
     *
     * @param term
     * @param otherTerms
     */
    private int getColumnForTerm(Term term, Collection<Term> otherTerms) {
        int column = 1;
        for (Term intersectingTerm : term.getIntersectedTerms(otherTerms)) {
            if(term.compareTo(intersectingTerm) == 0) {
                if(term.hashCode() < intersectingTerm.hashCode()){
                    column++;
                }
            }
            else if (term.compareTo(intersectingTerm) < 0) {
                column++;
            }
        }
        return column;
    }

    private GridPane createDayGrid(Day day) {
        //add time on left
        GridPane pane = new GridPane();
        HBox header = new HBox(new Text(day.toString()));
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-background-color: gainsboro");
        pane.setGridLinesVisible(true);
        int i = 1;
        for (TimeOfDay time : TimeOfDay.getAllPossible(Constants.MIN_TIME, Constants.MAX_TIME, TIME_SEED)) {
            pane.add(new Text(time.toString()), 0, i);
            i++;
        }
        pane.add(header, 1, 0, Integer.MAX_VALUE, 1);
        this.getChildren().add(pane);
        return pane;
    }

    private Pane getLayoutForTerm(Subject subject, Term term) {
        VBox pane = new VBox();
        String style;

        if (subject.getSelectedTerm() != null) {
            if(subject.getSelectedTerm().equals(term)){
                style = "-fx-background-color: forestgreen;";
                style += "-fx-border-style: solid;";
                style += "-fx-border-width: 5px;";
            } else {
                style = "-fx-background-color: grey;";
            }
        } else {
            switch (subject.getSubjectType()) {
                case EXERCISES:
                    style = "-fx-background-color: red;";
                    break;
                case LABORATORY:
                    style = "-fx-background-color: darksalmon;";
                    break;
                case PROJECT:
                    style = "-fx-background-color: goldenrod;";
                    break;
                case SEMINARY:
                    style = "-fx-background-color: aquamarine;";
                    break;
                case LECTURE:
                    style = "-fx-background-color: lightskyblue;";
                    break;
                default:
                    style = "-fx-background-color: mistyrose;";
                    break;
            }
        }
        pane.setStyle(style);
        pane.getChildren().add(new Text(subject.getName()));
        pane.getChildren().add(new Text(subject.getSubjectType().toString()));
        pane.getChildren().add(new Text(term.getStart().getTimeOfDay().toString() + " - " + term.getEnd().getTimeOfDay().toString()));
	pane.getChildren().add(new Text(term.getTeacher()));        
	pane.setAlignment(Pos.CENTER);
        pane.setOnMouseClicked(event -> {
            subject.setSelectedTerm(term);
        });
        return pane;
    }
}
