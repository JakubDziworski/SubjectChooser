package model;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.IllegalTermException;

import java.util.Arrays;

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
	public void identicatlTermsShouldIntersect4() throws Exception {
		sut = new Term(new WeekDateTime(Day.MON,12,15),new WeekDateTime(Day.MON,14,15));
		Term otherTerm = new Term(new WeekDateTime(Day.MON,12,15),new WeekDateTime(Day.MON,14,15));
		assertTrue(sut.intersects(otherTerm));
	}

	@Test
	public void intersectingTermsShouldIntersect2() throws Exception {
		sut = new Term(new WeekDateTime(Day.WED,16,15),new WeekDateTime(Day.WED,18,15));
		Term otherTerm = new Term(new WeekDateTime(Day.WED,16,15),new WeekDateTime(Day.WED,17,00));
		assertTrue(sut.intersects(otherTerm));
	}

	@Test
	public void intersectingTermsShouldIntersect3() throws Exception {
		sut = new Term(new WeekDateTime(Day.WED,16,15),new WeekDateTime(Day.WED,16,35));
		Term otherTerm = new Term(new WeekDateTime(Day.WED,14,15),new WeekDateTime(Day.WED,17,00));
		assertTrue(sut.intersects(otherTerm));
	}

	@Test
	public void termShouldIntersectWithAny() throws Exception {
		sut = new Term(new WeekDateTime(Day.MON,12,15),new WeekDateTime(Day.MON,14,15));
		Term otherTerm1 = new Term(new WeekDateTime(Day.MON,11,15),new WeekDateTime(Day.MON,13,15));
		Term otherTerm2 = new Term(new WeekDateTime(Day.MON,15,15),new WeekDateTime(Day.MON,18,15));
		assertTrue(sut.intersectsAny(Arrays.asList(otherTerm1, otherTerm2)));
	}

	@Test
	public void termShouldNotIntersectWithAny() throws Exception {
		sut = new Term(new WeekDateTime(Day.MON,15,15),new WeekDateTime(Day.MON,18,15));
		Term otherTerm1 = new Term(new WeekDateTime(Day.MON,11,15),new WeekDateTime(Day.MON,13,15));
		assertFalse(sut.intersects(otherTerm1));
	}

	@Test
	public void nonIntersectingTermsShouldNotIntersect() throws Exception {
		sut = new Term(new WeekDateTime(Day.MON,14,15),new WeekDateTime(Day.MON,16,15));
		Term otherTerm = new Term(new WeekDateTime(Day.MON,11,15),new WeekDateTime(Day.MON,13,15));
		assertFalse(sut.intersects(otherTerm));
	}

	@Test public void termShouldBeGreater() throws Exception {
		sut = new Term(new WeekDateTime(Day.MON,14,15),new WeekDateTime(Day.MON,16,15));
		Term otherTerm = new Term(new WeekDateTime(Day.MON,11,15),new WeekDateTime(Day.MON,13,15));
		assertTrue(sut.compareTo(otherTerm) > 0);
	}

	@Test public void termShouldBeLess() throws Exception {
		sut = new Term(new WeekDateTime(Day.MON,12,15),new WeekDateTime(Day.MON,16,15));
		Term otherTerm = new Term(new WeekDateTime(Day.MON,13,15),new WeekDateTime(Day.MON,14,15));
		assertTrue(sut.compareTo(otherTerm) < 0);
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
