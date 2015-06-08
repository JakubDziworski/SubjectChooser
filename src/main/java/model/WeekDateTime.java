package model;


import exceptions.IllegalTimeOfDayException;

public class WeekDateTime implements Comparable<WeekDateTime> {
    private Day day;
    private TimeOfDay timeOfDay;

    /**
     * For Serialization use only
     */
    private WeekDateTime() {}

    public WeekDateTime(Day day, TimeOfDay timeOfDay) {
        this.day = day;
        this.timeOfDay = timeOfDay;
    }

    public WeekDateTime(Day day, int hour, int min) throws IllegalTimeOfDayException {
        this(day,new TimeOfDay(hour, min));
    }

    public static WeekDateTime DefaultDateTime() {
        try {
            return new WeekDateTime(Day.MON, 0, 0);
        } catch (IllegalTimeOfDayException e) {}
        return null;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public void setTimeOfDay(TimeOfDay timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public TimeOfDay getTimeOfDay() {
        return timeOfDay;
    }

    /**
     * @return Returns minutes from Monday 00:00
     */
    public int toMinutes() {
        return day.ordinal() * 1440 + timeOfDay.getHour() * 60 + timeOfDay.getMinutes();
    }

    @Override
    public int compareTo(WeekDateTime otherDate) {
        return (int) Math.signum(this.toMinutes() - otherDate.toMinutes());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + day.ordinal();
        result = prime * result + timeOfDay.getHour();
        result = prime * result + timeOfDay.getMinutes();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WeekDateTime other = (WeekDateTime) obj;
        if (day != other.day)
            return false;
        if (timeOfDay.getHour() != other.timeOfDay.getHour())
            return false;
        if (timeOfDay.getMinutes() != other.timeOfDay.getMinutes())
            return false;
        return true;
    }

}
