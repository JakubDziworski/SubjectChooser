package exceptions;

/**
 * Created by Jakub Dziworski on 2015-05-09 for purposes of subject "Zaawansowane zagadnienia programowania w Javie"
 */
public class IllegalMinuteFormatException extends IllegalTimeOfDayException {

    private int minutes;

    public IllegalMinuteFormatException(int totalTime, int minutes) {
        super(totalTime);
        this.minutes = minutes;
    }

    public int getMinutes() {
        return minutes;
    }
}
