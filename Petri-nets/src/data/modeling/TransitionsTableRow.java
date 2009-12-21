package data.modeling;

import java.util.ArrayList;

import data.Marking;
import data.elements.Transition;


/**
 * Transition table item of a Petri net.
 *
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * @author Jacky
 *
 */
public class TransitionsTableRow {

	private int no;
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
     * @param no
     * @param branch
     * @param level
     * @param prevMarking
     * @param nextMarking
     * @param workedTransitions
     * @param markType
     */
    public TransitionsTableRow(int no, int branch, int level,
            Marking prevMarking, Marking nextMarking,
            ArrayList<Transition> workedTransitions, MarkType markType) {
        this.no = no;
        this.branch = branch;
        this.level = level;
        this.prevMarking = prevMarking;
        this.nextMarking = nextMarking;
        this.workedTransitions = workedTransitions;
        this.markType = markType;
    }
    
    
    /**
     * @return the no
     */
    public final int getNo() {
        return no;
    }


    /**
     * @param no the id to set
     */
    public final void setNo(int no) {
        this.no = no;
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
        Object[] array = new Object[7];

        array[0] = (Integer) no;
        array[1] = (Integer) branch;
        array[2] = prevMarking;
        String s = "";
        if (workedTransitions.size() != 0) {
            for (int j = 0; j < workedTransitions.size(); j++) {
                if (workedTransitions.get(j) != null)
                    s += workedTransitions.get(j).getTitle();
            }
        }
        array[3] = s;
        array[4] = nextMarking;
        array[5] = markType;
        array[6] = (Integer) level;
        return array;
    }

    public String toString() {
        // TODO: realize me.
        throw new UnsupportedOperationException();
    }

}