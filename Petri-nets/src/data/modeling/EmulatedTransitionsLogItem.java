/**
 * 
 */
package data.modeling;

import java.util.ArrayList;

import data.Marking;
import data.elements.Transition;

/**
 * Row of emulating transitions log table.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */public class EmulatedTransitionsLogItem {
    private double time;
    private Marking prevMarking;
    private Marking nextMarking;
    private Transition activeTransition;
    private ArrayList<Transition> startedTransitions;
    /**
     * @param time
     * @param prevMarking
     * @param nextMarking
     * @param activeTransition
     * @param startedTransitions
     */
    public EmulatedTransitionsLogItem(double time, Marking prevMarking,
            Marking nextMarking, Transition activeTransition,
            ArrayList<Transition> startedTransitions) {
        this.time = time;
        this.prevMarking = prevMarking;
        this.nextMarking = nextMarking;
        this.activeTransition = activeTransition;
        this.startedTransitions = startedTransitions;
    }
    
    /**
     * @return the time
     */
    public final double getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public final void setTime(double time) {
        this.time = time;
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
     * @return the activeTransition
     */
    public final Transition getActiveTransition() {
        return activeTransition;
    }

    /**
     * @param activeTransition the activeTransition to set
     */
    public final void setActiveTransition(Transition activeTransition) {
        this.activeTransition = activeTransition;
    }

    /**
     * @return the startedTransitions
     */
    public final ArrayList<Transition> getStartedTransitions() {
        return startedTransitions;
    }

    /**
     * @param startedTransitions the startedTransitions to set
     */
    public final void setStartedTransitions(ArrayList<Transition> startedTransitions) {
        this.startedTransitions = startedTransitions;
    }

    public Object[] getObjectArray() {
        Object[] array = new Object[5];
        array[0] = time;
        array[1] = prevMarking.toString();
        array[2] = nextMarking.toString();
        array[3] = activeTransition.getTitle();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < startedTransitions.size(); i++) {
            str.append(startedTransitions.get(i).getTitle());
            str.append(" ");
        }
        array[4] = str.toString();
        
        return array;
    }
    
}
