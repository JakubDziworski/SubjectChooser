package model;

import managers.SubjectManager;
import managers.TermManager;

import org.simpleframework.xml.Element;

public class Subject {
	@Element
	private String name;
	@Element
	private Subject.Type type;
	public Subject() {
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
	
	public Subject.Type getType() {
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
		UNKNOWN
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	};
	
	
}
