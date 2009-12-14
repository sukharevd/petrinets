/**
 * 
 */
package data.modeling;

import java.util.ArrayList;

/**
 * Transitions log of emulating, has table structure.  
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class EmulatedTransitionsLog {
    private ArrayList<EmulatedTransitionsLogItem> rows;

    /**
     * 
     */
    public EmulatedTransitionsLog() {
        rows = new ArrayList<EmulatedTransitionsLogItem>();
    }
    
    /**
     * @return the rows
     */
    public final ArrayList<EmulatedTransitionsLogItem> getRows() {
        return rows;
    }

    public void add(EmulatedTransitionsLogItem row) {
        rows.add(row);
    }
    
    public Object[][] getObjectTable() {
        Object[][] table = new Object[rows.size()][5];
        
        for (int i = 0; i < rows.size(); i++) {
            Object[] array = rows.get(i).getObjectArray();
            for (int j = 0; j < array.length; j++) {
                table[i][j] = array[j];
            }
        }
        
        return table;
    }
    
    public void clear() {
        rows.clear();
    }
    
    public int size() {
        return rows.size();
    }
}
