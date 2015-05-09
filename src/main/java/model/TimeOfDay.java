package model;

import exceptions.IllegalHourFormatException;
import exceptions.IllegalMinuteFormatException;
import exceptions.IllegalTimeOfDayException;

import java.util.stream.Stream;

/**
 * Created by Jakub Dziworski on 2015-05-09 for purposes of subject "Zaawansowane zagadnienia programowania w Javie"
 */
public class TimeOfDay {
    private static final int MINUTES_MIN = 0;
    private static final int HOURS_MIN = 0;
    private static final int MINUTES_MAX = 60;
    private static final int HOURS_MAX = 24;

    private int time; //in minutes

    public TimeOfDay(int hour, int minutes) throws IllegalTimeOfDayException {
        verifyHourFormat(hour);
        verifyMinuteFormat(minutes);
        setTottalInMinutes(hour * MINUTES_MAX + minutes);
    }

    public TimeOfDay(int minutes) throws IllegalTimeOfDayException {
        setTottalInMinutes(minutes);
    }

    /**
     * @return Time of a day at 00:00
     */
    public static TimeOfDay defaultInstance() {
        try {
            return new TimeOfDay(MINUTES_MIN);
        } catch (IllegalTimeOfDayException e) {
        }
        return null;
    }

    public void setTime(int hour,int minutes) throws IllegalTimeOfDayException {
        verifyHourFormat(hour);
        verifyMinuteFormat(minutes);
        setTottalInMinutes(hour*MINUTES_MAX + minutes);
    }

    public int getHour() {
        return time / MINUTES_MAX;
    }

    public void setHour(int hour) throws IllegalHourFormatException {
        verifyHourFormat(hour);
        time = getMinutes() + hour*MINUTES_MAX;
    }

    public int getMinutes() {
        return time % MINUTES_MAX;
    }

    public void setMinutes(int minutes) throws IllegalMinuteFormatException {
        verifyMinuteFormat(minutes);
        time = getHour()*MINUTES_MAX + minutes;
    }

    public void setTottalInMinutes(int totalMinutes) throws IllegalTimeOfDayException {
        verifyTotalTimeBounds(totalMinutes);
        this.time = totalMinutes;
    }

    public int getTotalInMinutes() {
        return time;
    }

    public static TimeOfDay[] getAllPossibleWithSeed(int seed) {

        return Stream
                .iterate(TimeOfDay.defaultInstance(), prev -> {
                    try {
                        return new TimeOfDay(prev.getTotalInMinutes() + seed);
                    } catch (IllegalTimeOfDayException e) {
                    }
                    return null;
                })
                .limit(HOURS_MAX * (MINUTES_MAX / seed))
                .toArray(TimeOfDay[]::new);
    }


    private void verifyMinuteFormat(int minutes) throws IllegalMinuteFormatException {
        if (minutes < MINUTES_MIN || minutes > MINUTES_MAX) throw new IllegalMinuteFormatException(time, minutes);
    }

    private void verifyHourFormat(int hour) throws IllegalHourFormatException {
        if (hour < HOURS_MIN || hour > HOURS_MAX - 1) throw new IllegalHourFormatException(time, hour);
    }

    private void verifyTotalTimeBounds(int pendingTime) throws IllegalTimeOfDayException {
        if (pendingTime < MINUTES_MIN || pendingTime > HOURS_MAX * MINUTES_MAX)
            throw new IllegalTimeOfDayException(pendingTime);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", getHour(), getMinutes());
    }
}
