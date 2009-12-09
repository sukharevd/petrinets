/**
 * 
 */
package data.modeling;

import java.util.ArrayList;

import data.Marking;


/**
 * Transitions log of emulating, has table structure.  
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class EmulatedStatisticMaker {
    private EmulationManager emulator;
    private ArrayList<EmulatedTransitionsLogItem> logItems;
    private ArrayList<EmulationStatisticItem> rows;
    private ArrayList<Marking> markings;
    private double sumTime = 0.0;

    /**
     * 
     */
    public EmulatedStatisticMaker(EmulationManager emulator) {
        this.emulator = emulator;
        initialize();
    }
    
    protected void initialize() {
        if (emulator.getLog() != null) {
            this.logItems = emulator.getLog().getRows();
        
        if (emulator.getTransTable()!= null) {
            this.markings = emulator.getTransTable().getListOfMarking();
        
        this.rows = new ArrayList<EmulationStatisticItem>(markings.size());
        for (int i = 0; i < markings.size(); i++) {
			rows.add(new EmulationStatisticItem(markings.get(i)));
		}}}
    }
    
    protected void calcTimes() {
        for (int i = 0; i < logItems.size(); i++) {
            int index = getIndexOfMarking(markings, logItems.get(i).getNextMarking());
            double time = logItems.get(i).getTime();
            rows.get(index).addValues(time, sumTime);
            sumTime += time;
        }
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
    
    public Object[][] makeStatistic() {
        calcTimes();
        return getObjectMatrix();
    }
    
    protected Object[][] getObjectMatrix() {
        int numColumns = 6;
        double delta = 0.01;
        
        Object[][] matrix = new Object[markings.size()][numColumns];
        
        for (int i = 0; i < matrix.length; i++) {
            Object[] array = rows.get(i).getObjectArray(sumTime, delta);
            for (int j = 0; j < matrix[0].length; j++) {                
                matrix[i][j] = array[j];
                System.out.print(array[j] + "\t");
            }
            System.out.println("\n");
        }
        
        return matrix;
    }
    
    public void clear() {
        logItems.clear();
    }
}
