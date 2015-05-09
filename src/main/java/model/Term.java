package model;

import exceptions.IllegalTimeOfDayException;
import managers.ApplicationManager;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import exceptions.IllegalTermException;


@Root
public class Term {

	@Element
	private String teacher;
	@Element
	private WeekDateTime start;
	@Element
	private WeekDateTime end;
	
	public Term(WeekDateTime start,WeekDateTime end) throws IllegalTermException {
		this(start, end, ApplicationManager.Strings.DAY_UNDEFINED);
	}
	
	public Term(WeekDateTime start,WeekDateTime end,String teacher) throws IllegalTermException {
		this.teacher = teacher;
		this.start = start;
		this.end = end;
		verifyStartBeforeEnd(this.start, end);
	}
	
	public static Term DefaultTerm() {
		final WeekDateTime start = WeekDateTime.DefaultDateTime();
		WeekDateTime end = WeekDateTime.DefaultDateTime();
		try {
			end.getTimeOfDay().setTottalInMinutes(end.getTimeOfDay().getTotalInMinutes() + 120);
			return new Term(start, end);
		} catch (IllegalTimeOfDayException | IllegalTermException e) {}
		return null;
	}
	
	public String getTeacher() {
		return teacher;
	}
	
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	
	public WeekDateTime getStart() {
		return start;
	}
	
	public void setStart(WeekDateTime start) throws IllegalTermException {
		verifyStartBeforeEnd(start,this.end);
		this.start = start;
	}
	
	public WeekDateTime getEnd() {
		return end;
	}
	
	public void setEnd(WeekDateTime end) throws IllegalTermException {
		verifyStartBeforeEnd(this.start,end);
		this.end = end;
	}
	
	private void verifyStartBeforeEnd(WeekDateTime start, WeekDateTime end) throws IllegalTermException {
		if(start.compareTo(end) >= 0) {
			throw new IllegalTermException(start, end);
		}
	}

	public boolean weekDateTimeIsDuringTerm(WeekDateTime time) {
		return time.compareTo(start) == 1 && time.compareTo(end) == -1;
	}
	
	public boolean intersects(Term otherTerm) {
		return weekDateTimeIsDuringTerm(otherTerm.start) || weekDateTimeIsDuringTerm(otherTerm.end);
	}
}
