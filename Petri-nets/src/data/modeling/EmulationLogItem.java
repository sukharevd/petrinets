/*
    Copyright (C)  2009  Sukharev Dmitriy, Dzyuban Yuriy, Voitova Anastasiia.
    
    This file is part of Petri nets Emulator.
    
    Petri nets Emulator is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    
    Petri nets Emulator is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    
    You should have received a copy of the GNU General Public License
    along with Petri nets Emulator. If not, see <http://www.gnu.org/licenses/>.
*/

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
 */public class EmulationLogItem {
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
    public EmulationLogItem(double time, Marking prevMarking,
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
