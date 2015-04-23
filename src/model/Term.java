package model;

import managers.TermManager;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root
public class Term {

	@Element
	Subject subject;
	@Element
	private String teacher;
	@Element
	private WeekDateTime start;
	@Element
	private WeekDateTime end;
	
	public Term() {
		subject = new Subject();
		teacher = "Unknown";
		start = new WeekDateTime();
		end = new WeekDateTime();
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
	
	public void setStart(WeekDateTime start) {
		this.start = start;
		TermManager.getInstance().notifyTermChanged(this);
	}
	
	public WeekDateTime getEnd() {
		return end;
	}
	
	public void setEnd(WeekDateTime end) {
		this.end = end;
		TermManager.getInstance().notifyTermChanged(this);
	}
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}
