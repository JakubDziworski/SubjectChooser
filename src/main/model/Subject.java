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
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((terms == null) ? 0 : terms.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Subject other = (Subject) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (terms == null) {
			if (other.terms != null)
				return false;
		} else if (!terms.equals(other.terms))
			return false;
		if (type != other.type)
			return false;
		return true;
	};
}
