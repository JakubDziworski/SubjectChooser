package managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Subject;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class SubjectManager {
	@ElementList
	private List<Subject> subjects;
	private List<SubjectsListener> subjectsListeners;
	private static SubjectManager me;
	private SubjectManager() {
		subjects = new ArrayList<>();
		subjectsListeners = new ArrayList<>();
	}
	public static SubjectManager getInstance() {
		if(me == null) me = new SubjectManager();
		return me;
	}
	public List<Subject> getSubjects() {
		return subjects;
	}
	public void addSubject(Subject subject) {
		if(subjects.contains(subject) == false) {
			subjects.add(subject);
			subjectsListeners.forEach(sl -> sl.onSubjectAdded(subject));
		}
	}
	public void removeSubject(Subject subject) {
		if (subjects.contains(subject)) {
			subjects.remove(subject);
			subjectsListeners.forEach(sl -> sl.onSubjectRemoved(subject));
		}
	}

	public void clear() {
		subjects.clear();
	}
	
	public void notifySubjectChanged(Subject subject) {
		subjectsListeners.forEach(sl -> sl.onSubjectChanged(subject));
	}
	
	public void addSubjectsListener(SubjectsListener listener) {
		if(subjectsListeners.contains(listener) == false) subjectsListeners.add(listener);
	}
	public void removeSubjectsListener(SubjectsListener listener) {
		subjectsListeners.remove(listener);
	}
	
	public interface SubjectsListener {
		void onSubjectAdded(Subject subject);
		void onSubjectRemoved(Subject subject);
		void onSubjectChanged(Subject subject);
	}
}
