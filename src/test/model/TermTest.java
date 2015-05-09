package model;

import static org.junit.Assert.*;
import model.WeekDateTime.Day;

import org.junit.Before;
import org.junit.Test;

import exceptions.IllegalTermException;

public class TermTest {

	Term sut;
	
	@Test
	public void weekDateTimeShouldBeDuringTerm() throws Exception {
		sut = new Term(new WeekDateTime(Day.MON,12,15),new WeekDateTime(Day.MON,14,15));
		WeekDateTime wdt = new WeekDateTime(Day.MON,13,15);
		assertTrue(sut.weekDateTimeIsDuringTerm(wdt));
	}
	
	@Test
	public void weekDateTimeShouldNotBeDuringTerm() throws Exception {
		sut = new Term(new WeekDateTime(Day.MON,12,15),new WeekDateTime(Day.MON,14,15));
		WeekDateTime wdt = new WeekDateTime(Day.MON,15,15);
		sut.weekDateTimeIsDuringTerm(wdt);
		assertFalse(sut.weekDateTimeIsDuringTerm(wdt));
	}
	
	@Test
	public void intersectingTermsShouldIntersect() throws Exception {
		sut = new Term(new WeekDateTime(Day.MON,12,15),new WeekDateTime(Day.MON,14,15));
		Term otherTerm = new Term(new WeekDateTime(Day.MON,11,15),new WeekDateTime(Day.MON,13,15));
		assertTrue(sut.intersects(otherTerm));
	}
	
	@Test
	public void nonIntersectingTermsShouldNotIntersect() throws Exception {
		sut = new Term(new WeekDateTime(Day.MON,14,15),new WeekDateTime(Day.MON,16,15));
		Term otherTerm = new Term(new WeekDateTime(Day.MON,11,15),new WeekDateTime(Day.MON,13,15));
		assertFalse(sut.intersects(otherTerm));
	}
	
	@Test(expected=IllegalTermException.class)
	public void shouldThrowIllegalTermWhenStartAfterEnd() throws Exception {
		sut = new Term(new WeekDateTime(Day.MON,14,15),new WeekDateTime(Day.MON,13,15));
	}
	
	@Test(expected=IllegalTermException.class)
	public void shouldThrowIllegalTermWhenStartEqualsEnd() throws Exception {
		sut = new Term(new WeekDateTime(Day.MON,14,15),new WeekDateTime(Day.MON,14,15));
	}
}
