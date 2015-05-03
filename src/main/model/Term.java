package model;

import managers.TermManager;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import exceptions.IllegalHourException;
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
		this(start,end,"Unknown");
	}
	
	public Term(WeekDateTime start,WeekDateTime end,String teacher) throws IllegalTermException {
		this.teacher = teacher;
		this.start = start;
		this.end = end;
		verifyStartBeforeEnd();
	}
	
	public Term DefaultTerm() {
		final WeekDateTime start = WeekDateTime.DefaultDateTime();
		WeekDateTime end = WeekDateTime.DefaultDateTime();
		try {
			end.setHour(end.getHour() + 2);
			return new Term(start,end);
		} catch (IllegalHourException | IllegalTermException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getTeacher() {
		return teacher;
	}
	
	public void setTeacher(String teacher) {
		this.teacher = teacher;
		TermManager.getInstance().notifyTermChanged(this);
	}
	
	public WeekDateTime getStart() {
		return start;
	}
	
	public void setStart(WeekDateTime start) throws IllegalTermException {
		this.start = start;
		verifyStartBeforeEnd();
		TermManager.getInstance().notifyTermChanged(this);
	}
	
	public WeekDateTime getEnd() {
		return end;
	}
	
	public void setEnd(WeekDateTime end) throws IllegalTermException {
		this.end = end;
		verifyStartBeforeEnd();
		TermManager.getInstance().notifyTermChanged(this);
	}
	
	private void verifyStartBeforeEnd() throws IllegalTermException {
		if(start.compareTo(end) > 0) throw new IllegalTermException();
	}
	
	public boolean weekDateTimeIsDuringTerm(WeekDateTime time) {
		return time.compareTo(start) == 1 && time.compareTo(end) == -1;
	}
	
	public boolean intersects(Term otherTerm) {
		return weekDateTimeIsDuringTerm(otherTerm.start) || weekDateTimeIsDuringTerm(otherTerm.end);
	}
}
