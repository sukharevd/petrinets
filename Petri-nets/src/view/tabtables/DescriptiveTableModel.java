/*
    Copyright (C)  2009  Sukharev Dmitriy, Dzyuban Yuriy, Vixen Tael.
    
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
package view.tabtables;

import javax.swing.table.DefaultTableModel;

/**
 * Table model for table in class DescriptiveTableDrawer.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class DescriptiveTableModel extends DefaultTableModel {

    /**
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = 4869321333024726705L;

    /**
     * Constructor. It contains superclass constructor.
     * 
     * @param data
     *            array of data of the table.
     * @param columnNames
     *            array of column names of the table.
     */
    public DescriptiveTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Class getColumnClass(final int columnIndex) {
        if (columnIndex == 0) {
            return String.class;
        } else {
            return Integer.class;
        }
    }
}
