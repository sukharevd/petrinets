package data.modeling;

import java.util.ArrayList;

import data.Marking;
import data.elements.Transition;

public class TransitionsTableRow {

	private int id;
	private int branch;
	private int level;
	private Marking prevMarking;
	private Marking nextMarking;
	private ArrayList<Transition> workedTransitions;
	private MarkType markType;

	public void EMPTY_AND_FULL_CONSTRUCTORS() {
		throw new UnsupportedOperationException();
	}

	public void GETTERS_and_SETTERS() {
		throw new UnsupportedOperationException();
	}

	public Object getObjectArray() {
		throw new UnsupportedOperationException();
	}

	public String toString() {
		throw new UnsupportedOperationException();
	}

}