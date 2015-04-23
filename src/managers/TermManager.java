package managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Term;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class TermManager {
	@ElementList
	private List<Term> terms;
	private List<TermsListener> termsListeners;
	private static TermManager me;
	private TermManager() {
		terms = new ArrayList<>();
		termsListeners = new ArrayList<>();
	}
	public static TermManager getInstance() {
		if(me == null) me = new TermManager();
		return me;
	}
	public List<Term> getTerms() {
		return terms;
	}
	public void addTerm(Term term) {
		if(terms.contains(term) == false) {
			terms.add(term);
			for(TermsListener listener : termsListeners) {
				listener.onTermAdded(term);
			}
		}
	}
	public void removeTerm(Term term) {
		if (terms.contains(term)) {
			terms.remove(term);
			for(TermsListener listener : termsListeners) {
				listener.onTermRemoved(term);
			}
		}
	}
	
	public void notifyTermChanged(Term term) {
		for(TermsListener listener : termsListeners) {
			listener.onTermChanged(term);
		}
	}
	
	public void addTermsListener(TermsListener listener) {
		if(termsListeners.contains(listener) == false) termsListeners.add(listener);
	}
	public void removeTermsListener(TermsListener listener) {
		termsListeners.remove(listener);
	}
	
	public interface TermsListener {
		void onTermAdded(Term term);
		void onTermRemoved(Term term);
		void onTermChanged(Term term);
	}
}
