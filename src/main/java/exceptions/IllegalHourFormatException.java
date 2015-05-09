package exceptions;

/**
 * Created by Jakub Dziworski on 2015-05-09 for purposes of subject "Zaawansowane zagadnienia programowania w Javie"
 */
public class IllegalHourFormatException extends IllegalTimeOfDayException {

    private int hour;

    public IllegalHourFormatException(int hour, int totalTime) {
        super(totalTime);
        this.hour = hour;
    }

    public int getHour() {
        return hour;
    }
}
