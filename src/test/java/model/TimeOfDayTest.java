package model;

import exceptions.IllegalHourFormatException;
import exceptions.IllegalMinuteFormatException;
import org.junit.Test;

/**
 * Created by Jakub Dziworski on 2015-05-09 for purposes of subject "Zaawansowane zagadnienia programowania w Javie"
 */
public class TimeOfDayTest {
    TimeOfDay sut;

    //MINUTES EXCEPTIONS//
    @Test(expected= IllegalMinuteFormatException.class)
    public void shouldThrowIllegalMinuteExceptionWhenMinutesBelowZero() throws Exception {
        sut = new TimeOfDay(10,-5);
    }

    @Test(expected= IllegalMinuteFormatException.class)
    public void shouldThrowIllegalMinuteExceptionWhenMinutesAbove60() throws Exception {
        sut = new TimeOfDay(23,61);
    }

    @Test
    public void shouldntThrowIllegalMinuteExceptionWhenMinutesEqual60() throws Exception {
        sut = new TimeOfDay(23,60);
    }

    @Test
    public void shouldntThrowIllegalMinuteExceptionWhenMinutesEqual0() throws Exception {
        sut = new TimeOfDay(23,0);
    }

    //HOURS EXCEPTIONS//
    @Test(expected= IllegalHourFormatException.class)
    public void shouldThrowIllegalHourExceptionWhenHoursBelowZero() throws Exception {
        sut = new TimeOfDay(-5,15);
    }

    @Test(expected=IllegalHourFormatException.class)
    public void shouldThrowIllegalHourExceptionWhenHoursAbove2359() throws Exception {
        sut = new TimeOfDay(24,00);
    }

    @Test
    public void shouldntThrowIllegalHourExceptionWhenHoursEqualsZero() throws Exception {
        sut = new TimeOfDay(0,15);
    }

    @Test
     public void shouldntThrowIllegaTermExceptionWhenOnBounds() throws Exception {
        sut = new TimeOfDay(23,59);
        sut = new TimeOfDay(0,0);
    }
}