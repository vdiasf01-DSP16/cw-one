package sml;

import java.util.ArrayList;

/**
 * The list of Strings, called "labels" in the order in which 
 * they were added to the list. 
 * 
 * @author Vasco
 * 
 */
public class Labels {

	/**
	 * The list of all labels.
	 */
    private ArrayList<String> labels = new ArrayList<>();

    /**
     * Add a label to this list and return its number in the list
     * (the first one added is number 0)
     * Precondition: the list has at most 49 entries
     * 
     * @param label
     * @return Integer
     */
    public int addLabel(String label) {
        labels.add(label);
        return labels.size() - 1;
    }

    /**
     * The index in list for given label
     * -1 if label is not in the list
     * 
     * @param label
     * @return Integer
     */
    public int indexOf(String label) {

        // invariant: lab is not in labels[0..i-1]
        for (int i = 0; i != labels.size(); i++) {
            if (label.equals(labels.get(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * representation of this instance, "(label 0, label 1, ..., label (n-1))"
     */
    @Override
    public String toString() {
        StringBuilder r = new StringBuilder("(");
        // invariant: r contains the representation for labels[0..i-1]
        // (with the opening "(" but no closing ")")
        for (int i = 0; i != labels.size(); i++) {
            if (i == 0) {
                r.append(labels.get(i));
            } else {
                r.append(", ").append(labels.get(i));
            }
        }
        r.append(")");
        return r.toString();
    }

    /**
     * Set the number of elements in the list to 0
     */
    public void reset() {
        labels.clear();
    }
}