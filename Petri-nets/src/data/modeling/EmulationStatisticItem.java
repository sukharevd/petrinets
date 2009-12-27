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

/**
 * Item what is used by EmulationStatisticMaker, counts its statistic values.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class EmulationStatisticItem {
	private Marking marking;
	private int frequency;
	private double sumTime;
	private double lastTime;
	private double firstTime;
	private ArrayList<Integer> toMarkings;

	/**
	 * @param marking
	 */
	public EmulationStatisticItem(Marking marking, int markingsNumber) {
		this.marking = marking;
		this.firstTime = -1.0;
		this.lastTime = -1.0;
		this.toMarkings = new ArrayList<Integer>();
		
		for (int i = 0; i < markingsNumber; i++) {
		    this.toMarkings.add(0);
        }
	}

	/**
     * @return the frequency
     */
    public final int getFrequency() {
        return frequency;
    }

    /**
     * @param frequency the frequency to set
     */
    public final void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    /**
     * @return the sumTime
     */
    public final double getSumTime() {
        return sumTime;
    }

    /**
     * @param sumTime the sumTime to set
     */
    public final void setSumTime(double sumTime) {
        this.sumTime = sumTime;
    }

    /**
     * @return the fromMarkings
     */
    public final ArrayList<Integer> getToMarkings() {
        return toMarkings;
    }

    /**
     * @param fromMarkings the fromMarkings to set
     */
    public final void setToMarkings(ArrayList<Integer> fromMarkings) {
        this.toMarkings = fromMarkings;
    }

    /**
	 * @return the marking
	 */
	public final Marking getMarking() {
		return marking;
	}

	
	/**
	 * @param sumTime
	 * @param realTime
	 * @param nextM
	 */
	public void addValues(double sumTime, double realTime, Marking nextM) {
		this.frequency += 1;
		this.sumTime += sumTime;
		
		if (firstTime == -1.0) {
            firstTime = realTime;
            lastTime = realTime;
        } else {
            this.lastTime = realTime;
        }
		
		int no = nextM.getNo();
		toMarkings.set(no, toMarkings.get(no) + 1);
	}
	
	public double getReturnTime() {
	    return lastTime - firstTime;
	}
	
	public double getProbability(double allTime) {
	    return sumTime / allTime;
	}
	
    public double getRepeatFreq(double delta) {
        return sumTime / delta;
    }
    
    public Object[] getObjectArray(double allTime, double delta) {
        Object[] array = new Object[6];
        array[0] = marking.toString();
        array[1] = frequency;
        array[2] = sumTime;
        array[3] = getReturnTime();
        array[4] = getProbability(allTime);
        array[5] = getRepeatFreq(delta);
        return array;
    }

    public Object[] getChangingObjectArray() {
        Object[] array = new Object[toMarkings.size() + 1];
        array[0] = (String)"M" + marking.getNo();
        
        Integer val;
        for (int i = 0; i < toMarkings.size(); i++) {
            val = toMarkings.get(i);
            if (val != 0) {
                array[i + 1] = val;
            } else {
                array[i + 1] = null;
            }
        }
        return array;
    }
    
    public Object[] getChangingProbabilityObjectArray(double delta) {
        Object[] array = getChangingObjectArray();
        double sum = 0.0;
        for (int i = 0; i < array.length; i++) {
            
            if (array[i] instanceof Integer) {
                array[i] = ((Integer)array[i] / getRepeatFreq(delta));
                sum += (Double) array[i];
            }
        }
        
        double atDiagonal = 0.0;
        if ((array[marking.getNo()+1] instanceof Double)) {
            atDiagonal = (Double) array[marking.getNo()+1];
        }
        array[marking.getNo()+1] = 1.0 - (sum - atDiagonal);
        
        return array;
    }
}
