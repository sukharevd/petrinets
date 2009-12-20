/**
 * 
 */
package data.modeling;

import java.util.ArrayList;

import data.Marking;

/**
 * Analyzer of emulation log, counts statistic values, uses for creating the
 * emulation tables.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class EmulationStatisticMaker {
    private EmulationManager emulator;

    private ArrayList<EmulatedTransitionsLogItem> logItems;

    private ArrayList<EmulationStatisticItem> rows;

    private ArrayList<Marking> markings;

    private double sumTime = 0.0;

    private double delta = 0.01;

    /**
     * 
     */
    public EmulationStatisticMaker(EmulationManager emulator) {
        this.emulator = emulator;
        initialize();
    }

    protected void initialize() {
        if (emulator.getLog() != null) {
            this.logItems = emulator.getLog().getRows();

            if (emulator.getTransTable() != null) {
                this.markings = emulator.getTransTable().getListOfMarking();

                this.rows = new ArrayList<EmulationStatisticItem>(markings
                        .size());
                for (int i = 0; i < markings.size(); i++) {
                    rows.add(new EmulationStatisticItem(markings.get(i),
                            markings.size()));
                }
            }
        }
    }

    public void calculateStatistic() {
        sumTime = 0.0;
        for (int i = 0; i < logItems.size(); i++) {
            int index = getIndexOfMarking(markings, logItems.get(i)
                    .getPrevMarking());
            double time = logItems.get(i).getTime();
            Marking nextMarking = logItems.get(i).getNextMarking();
            rows.get(index).addValues(time, sumTime, nextMarking);
            sumTime += time;
        }

        calculateDeltaT();
    }

    protected void calculateDeltaT() {
        int sum = 0;
        ArrayList<Double> deltaT = new ArrayList<Double>();
        for (int i = 0; i < rows.size(); i++) {
            sum = 0;
            for (int j = 0; j < rows.get(i).getToMarkings().size(); j++) {
                if (i != j) {
                sum += rows.get(i).getToMarkings().get(j);
                }
            }
            double tsum = rows.get(i).getSumTime();
            if (tsum != 0) {
                deltaT.add(tsum / sum);
            }
        }
        
        double tmin = Double.MAX_VALUE; 
        for (int i = 0; i < deltaT.size(); i++) {
            if (tmin > deltaT.get(i)) {
                tmin = deltaT.get(i);
            }
        }
        if (tmin == Double.MIN_VALUE) {
            tmin = 1.0; // any number
        }
        
        delta = 0.9 * tmin;
    }

    protected int getIndexOfMarking(ArrayList<Marking> markings, Marking marking) {
        int index = -1;
        for (int i = 0; i < markings.size(); i++) {
            if (marking.equals(markings.get(i))) {
                index = i;
                break;
            }
        }
        return index;
    }

    public EmulationStatisticItem getStatisticItemAt(int index) {
        return rows.get(index);
    }

    public int getStepsQuantity() {
        return logItems.size();
    }

    /**
     * @return the sumTime
     */
    public final double getSumTime() {
        return sumTime;
    }

    // /**
    // * @return the delta
    // */
    // public final double getDelta() {
    // return delta;
    // }

    public Object[][] makeEmulationStatistic() {
        int numColumns = 6;

        Object[][] matrix = new Object[markings.size()][numColumns];

        for (int i = 0; i < matrix.length; i++) {
            Object[] array = rows.get(i).getObjectArray(sumTime, delta);
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = array[j];
                // System.out.print(array[j] + "\t");
            }
            // System.out.println("\n");
        }

        return matrix;
    }

    public Object[][] makeChangingMarkingsStatistic() {
        int length = markings.size();
        Object[][] matrix = new Object[length][length + 1];
        Object[] array;
        for (int i = 0; i < length; i++) {
            array = rows.get(i).getChangingObjectArray();
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = array[j];
            }
        }
        return matrix;
    }

    public Object[][] makeChangingProbabilityMarkingsStatistic() {
        int length = markings.size();
        Object[][] matrix = new Object[length][length + 1];
        Object[] array;
        for (int i = 0; i < length; i++) {
            array = rows.get(i).getChangingProbabilityObjectArray(delta);
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = array[j];
            }
        }
        return matrix;
    }

    public void clear() {
        logItems.clear();
    }
}
