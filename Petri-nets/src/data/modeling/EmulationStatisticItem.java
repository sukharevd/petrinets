/**
 * 
 */
package data.modeling;

import data.Marking;

/**
 * @author Admin
 * 
 */
public class EmulationStatisticItem {
	private Marking marking;
	private int frequency;
	private double sumTime;
	private double lastTime;
	private double firstTime;

	/**
	 * @param marking
	 */
	public EmulationStatisticItem(Marking marking) {
		this.marking = marking;
		this.firstTime = -1.0;
		this.lastTime = -1.0;
	}

//	/**
//	 * @param marking
//	 * @param frequency
//	 * @param sumTime
//	 * @param returnTime
//	 */
//	public EmulationStatisticRow(Marking marking, int frequency,
//			double sumTime, double returnTime) {
//		super();
//		this.marking = marking;
//		this.frequency = frequency;
//		this.sumTime = sumTime;
//		this.lastTime = returnTime;
//		this.firstTime = -1.0; //???????????????????????
//	}

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
	public void addValues(/*int frequency, */double sumTime, double realTime) {
		this.frequency += 1;
		this.sumTime += sumTime;
		
		if (firstTime == -1.0) {
            firstTime = realTime;
            lastTime = realTime;
        } else {
            this.lastTime = realTime;
        }		
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

}
