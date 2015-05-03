package model;

import static org.junit.Assert.*;

import java.util.Date;

import model.WeekDateTime.Day;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import exceptions.IllegalHourException;
import exceptions.IllegalMinuteException;


public class WeekDateTimeTest {

	WeekDateTime sut;
	
	@Test
	public void ToMinutesShouldBeCorrect() throws Exception {
		sut = new WeekDateTime(Day.TUE,14,28);
		final int minutes = 1440 /*minutes for 1 days elapsed (MONDAY)*/ + 840 /*minutes for 14 hours elapsed*/ + 28;
		assertEquals(minutes, sut.toMinutes());
	}
	
	@Test
	public void compareToShouldBeGreaterThanZeroWhen_Tue_15_30__VS__Mon_14_13() throws Exception {
		sut = new WeekDateTime(Day.TUE,14,30);
		WeekDateTime other = new WeekDateTime(Day.MON,14,30);
		assertTrue(sut.compareTo(other) > 0);
	}
	
	@Test
	public void compareToShouldBeGreaterThanZeroWhen_Mon_15_30__VS__Mon_14_13() throws Exception {
		sut = new WeekDateTime(Day.MON,15,30);
		WeekDateTime other = new WeekDateTime(Day.MON,14,30);
		assertTrue(sut.compareTo(other) > 0);
	}
	
	@Test
	public void compareToShouldBeLessThanZeroWhen_Mon_00_30__VS__Sat_14_13() throws Exception {
		sut = new WeekDateTime(Day.MON,00,30);
		WeekDateTime other = new WeekDateTime(Day.SAT,14,30);
		assertTrue(sut.compareTo(other) < 0);
	}
	
	@Test
	public void compareToShouldBeZeroWhenSameTime() throws Exception {
		sut = new WeekDateTime(Day.MON,0,0);
		WeekDateTime other = new WeekDateTime(Day.MON,0,0);
		assertTrue(sut.compareTo(other) == 0);
	}
	
//	@Test
//	public void equalityTest() throws Exception {
//		sut = new WeekDateTime(Day.TUE,10,15);
//		WeekDateTime other = new WeekDateTime(Day.TUE,10,15);
//		assertTrue(sut == other);
//	}
	
	@Test
	public void equalsToTest() throws Exception {
		sut = new WeekDateTime(Day.TUE,10,15);
		WeekDateTime other = new WeekDateTime(Day.TUE,10,15);
		assertTrue(sut.equals(other));
	}
	
	//MINUTES EXCEPTIONS//
	@Test(expected=IllegalMinuteException.class)
	public void shouldThrowIllegalMinuteExceptionWhenMinutesBelowZero() throws Exception {
		sut = new WeekDateTime(Day.TUE,10,-5);
	}
	
	@Test(expected=IllegalMinuteException.class)
	public void shouldThrowIllegalMinuteExceptionWhenMinutesAbove60() throws Exception {
		sut = new WeekDateTime(Day.TUE,23,61);
	}
	
	@Test
	public void shouldntThrowIllegalMinuteExceptionWhenMinutesEqual60() throws Exception {
		sut = new WeekDateTime(Day.TUE,23,60);
	}
	
	@Test
	public void shouldntThrowIllegalMinuteExceptionWhenMinutesEqual0() throws Exception {
		sut = new WeekDateTime(Day.TUE,23,0);
	}
	
	//HOURS EXCEPTIONS//
	@Test(expected=IllegalHourException.class)
	public void shouldThrowIllegalHourExceptionWhenHoursBelowZero() throws Exception {
		sut = new WeekDateTime(Day.TUE,-5,15);
	}
	
	@Test(expected=IllegalHourException.class)
	public void shouldThrowIllegalHourExceptionWhenHoursAbove24() throws Exception {
		sut = new WeekDateTime(Day.TUE,26,15);
	}
	
	@Test
	public void shouldntThrowIllegalHourExceptionWhenHoursEqualsZero() throws Exception {
		sut = new WeekDateTime(Day.TUE,0,15);
	}
	
	@Test
	public void shouldntThrowIllegalHourExceptionWhenHoursEquals24() throws Exception {
		sut = new WeekDateTime(Day.TUE,24,15);
	}
	
}
