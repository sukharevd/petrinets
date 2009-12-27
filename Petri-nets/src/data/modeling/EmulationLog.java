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

/**
 * Transitions log of emulating, has table structure.  
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class EmulationLog {
    private ArrayList<EmulationLogItem> rows;

    /**
     * Constructor of {@link EmulationLog}.
     */
    public EmulationLog() {
        rows = new ArrayList<EmulationLogItem>();
    }
    
    /**
     * @return the rows
     */
    public final ArrayList<EmulationLogItem> getRows() {
        return rows;
    }

    public void add(EmulationLogItem row) {
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
