package exceptions;

import model.Term;
import model.WeekDateTime;

public class IllegalTermException extends Exception {
    private WeekDateTime start;
    private WeekDateTime end;

    public IllegalTermException(WeekDateTime start, WeekDateTime end) {
        super();
        this.start = start;
        this.end = end;
    }
}
