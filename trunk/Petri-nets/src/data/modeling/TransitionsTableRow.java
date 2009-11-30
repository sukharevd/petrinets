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
	
    /**
     * 
     */
    public TransitionsTableRow() {
        throw new UnsupportedOperationException();
    }

	/**
     * @param id
     * @param branch
     * @param level
     * @param prevMarking
     * @param nextMarking
     * @param workedTransitions
     * @param markType
     */
    public TransitionsTableRow(int id, int branch, int level,
            Marking prevMarking, Marking nextMarking,
            ArrayList<Transition> workedTransitions, MarkType markType) {
        this.id = id;
        this.branch = branch;
        this.level = level;
        this.prevMarking = prevMarking;
        this.nextMarking = nextMarking;
        this.workedTransitions = workedTransitions;
        this.markType = markType;
        throw new UnsupportedOperationException();
    }

    /**
     * @return the id
     */
    public final int getId() {
        return id;
    }


    /**
     * @param id the id to set
     */
    public final void setId(int id) {
        this.id = id;
    }


    /**
     * @return the branch
     */
    public final int getBranch() {
        return branch;
    }


    /**
     * @param branch the branch to set
     */
    public final void setBranch(int branch) {
        this.branch = branch;
    }


    /**
     * @return the level
     */
    public final int getLevel() {
        return level;
    }


    /**
     * @param level the level to set
     */
    public final void setLevel(int level) {
        this.level = level;
    }


    /**
     * @return the prevMarking
     */
    public final Marking getPrevMarking() {
        return prevMarking;
    }


    /**
     * @param prevMarking the prevMarking to set
     */
    public final void setPrevMarking(Marking prevMarking) {
        this.prevMarking = prevMarking;
    }


    /**
     * @return the nextMarking
     */
    public final Marking getNextMarking() {
        return nextMarking;
    }


    /**
     * @param nextMarking the nextMarking to set
     */
    public final void setNextMarking(Marking nextMarking) {
        this.nextMarking = nextMarking;
    }


    /**
     * @return the workedTransitions
     */
    public final ArrayList<Transition> getWorkedTransitions() {
        return workedTransitions;
    }


    /**
     * @param workedTransitions the workedTransitions to set
     */
    public final void setWorkedTransitions(ArrayList<Transition> workedTransitions) {
        this.workedTransitions = workedTransitions;
    }


    /**
     * @return the markType
     */
    public final MarkType getMarkType() {
        return markType;
    }


    /**
     * @param markType the markType to set
     */
    public final void setMarkType(MarkType markType) {
        this.markType = markType;
    }


    public Object[] getObjectArray() {
		throw new UnsupportedOperationException();
	}

	public String toString() {
		throw new UnsupportedOperationException();
	}

}