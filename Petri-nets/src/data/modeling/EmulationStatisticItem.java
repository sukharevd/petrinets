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
	private ArrayList<Integer> fromMarkings;

	/**
	 * @param marking
	 */
	public EmulationStatisticItem(Marking marking, int markingsNumber) {
		this.marking = marking;
		this.firstTime = -1.0;
		this.lastTime = -1.0;
		this.fromMarkings = new ArrayList<Integer>();
		
		for (int i = 0; i < markingsNumber; i++) {
		    this.fromMarkings.add(0);
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
    public final ArrayList<Integer> getFromMarkings() {
        return fromMarkings;
    }

    /**
     * @param fromMarkings the fromMarkings to set
     */
    public final void setFromMarkings(ArrayList<Integer> fromMarkings) {
        this.fromMarkings = fromMarkings;
    }

    /**
	 * @return the marking
	 */
	public final Marking getMarking() {
		return marking;
	}

	/**
	 * @param marking
	 * @param frequency
	 * @param sumTime
	 * @param realTime
	 */
	public void addValues(double sumTime, double realTime, Marking prevM) {
		this.frequency += 1; 
		this.sumTime += sumTime;
		
		if (firstTime == -1.0) {
            firstTime = realTime;
            lastTime = realTime;
        } else {
            this.lastTime = realTime;
        }
		
		int no = prevM.getNo();
		fromMarkings.set(no, fromMarkings.get(no) + 1);
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
        Object[] array = new Object[fromMarkings.size() + 1];
        array[0] = (String)"M" + marking.getNo();
        
        Integer val;
        for (int i = 0; i < fromMarkings.size(); i++) {
            val = fromMarkings.get(i);
            if (val != 0) {
                array[i + 1] = val;
            } else {
                array[i + 1] = null;
            }
        }
        return array;
    }
    
    public Object[] getChangingProbabilityObjectArray() {
        Object[] array = getChangingObjectArray();
        double sum = 0.0;
        for (int i = 0; i < array.length; i++) {
            
            if (array[i] instanceof Integer) {
                array[i] = ((Integer)array[i] / getRepeatFreq(0.01));
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
