package exceptions;

/**
 * Created by Jakub Dziworski on 2015-05-09 for purposes of subject "Zaawansowane zagadnienia programowania w Javie"
 */
public class IllegalTimeOfDayException extends Exception {
    private int totalTime;

    public IllegalTimeOfDayException(int totalTime) {
        super();
        this.totalTime = totalTime;
    }

    public int getTotalTime() {
        return totalTime;
    }
}
