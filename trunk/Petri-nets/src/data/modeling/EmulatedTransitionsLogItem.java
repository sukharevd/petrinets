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
    
    public Object[] getObjectArray() {
        Object[] array = new Object[5];
        array[0] = time;
        array[1] = prevMarking.toString();
        array[2] = nextMarking.toString();
        array[3] = activeTransition.getTitle();
        array[4] = startedTransitions;
        
        return array;
    }
    
}
