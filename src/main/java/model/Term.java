package model;

import exceptions.IllegalTimeOfDayException;
import managers.ApplicationManager;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import exceptions.IllegalTermException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Root
public class Term implements Comparable<Term> {

	@Element
	private String teacher;
	@Element
	private WeekDateTime start;
	@Element
	private WeekDateTime end;

	/**
	 * For Serialization use only
	 */
	private Term(){}

	public Term(WeekDateTime start,WeekDateTime end) throws IllegalTermException {
		this(start, end, ApplicationManager.Strings.TEACHER_DEFAULT);
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
	
	public static void verifyStartBeforeEnd(WeekDateTime start, WeekDateTime end) throws IllegalTermException {
		if(start.compareTo(end) >= 0) {
			throw new IllegalTermException(start, end);
		}
	}

	public boolean weekDateTimeIsDuringTerm(WeekDateTime time) {
		return time.compareTo(start) == 1 && time.compareTo(end) == -1;
	}
	
	public boolean intersects(Term otherTerm) {
		return weekDateTimeIsDuringTerm(otherTerm.start) || weekDateTimeIsDuringTerm(otherTerm.end)
				|| otherTerm.weekDateTimeIsDuringTerm(end) || otherTerm.weekDateTimeIsDuringTerm(start);
	}

	public boolean intersectsAny(Collection<Term> terms) {
		return terms.stream().filter(term1 -> term1.intersects(this)).findFirst().isPresent();
	}

	public List<Term> getIntersectedTerms(Collection<Term> terms) {
		return terms.stream().filter(term1 -> term1.intersects(this)).collect(Collectors.toList());
	}

	@Override
	public int compareTo(Term o) {
		return getStart().compareTo(o.getStart());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Term term = (Term) o;

		if (teacher != null ? !teacher.equals(term.teacher) : term.teacher != null) return false;
		if (start != null ? !start.equals(term.start) : term.start != null) return false;
		return !(end != null ? !end.equals(term.end) : term.end != null);
	}

	@Override
	public int hashCode() {
		int result = teacher != null ? teacher.hashCode() : 0;
		result = 31 * result + (start != null ? start.hashCode() : 0);
		result = 31 * result + (end != null ? end.hashCode() : 0);
		return result;
	}
}
