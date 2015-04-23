package model;

import java.util.ArrayList;
import java.util.List;

import managers.ApplicationManager;
import managers.SubjectManager;
import managers.TermManager;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;

public class Subject {
	@Element
	private String name;
	@Element
	private Subject.Type type;
	@ElementList
	List<Term> terms;
	public Subject() {
		terms = new ArrayList<Term>();
		name = "Unknown";
		type = Type.UNKNOWN;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String subjectName) {
		this.name = subjectName;
		SubjectManager.getInstance().notifySubjectChanged(this);
	}
	
	public Subject.Type getSubjectType() {
		return type;
	}
	public void setType(Subject.Type type) {
		this.type = type;
		SubjectManager.getInstance().notifySubjectChanged(this);
	}

	public enum Type {
		EXCERSISES,
		LABORATORY,
		PROJECT,
		LECTURE,
		SEMINARY,
		UNKNOWN;

		@Override
		public String toString() {
			switch (this) {
			case EXCERSISES:
				return ApplicationManager.Strings.SUBJECT_TYPE_EXCERSISES;
			case LABORATORY:
				return ApplicationManager.Strings.SUBJECT_TYPE_LABORATORY;
			case PROJECT:
				return ApplicationManager.Strings.SUBJECT_TYPE_PROJECT;
			case LECTURE:
				return ApplicationManager.Strings.SUBJECT_TYPE_LECTURE;
			case SEMINARY:
				return ApplicationManager.Strings.SUBJECT_TYPE_SEMINARY;
			default:
				return ApplicationManager.Strings.SUBJECT_TYPE_UNKNOWN;
			}
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + " (" + type.toString() + ")";
	};
	
	
}
