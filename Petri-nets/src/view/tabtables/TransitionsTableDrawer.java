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
package view.tabtables;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import data.Data;
import data.TableManagment;
import data.modeling.TreeofPetriNet;

/**
 * Some table and set of the method for painting at it.
 *
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * @author <a href="mailto:ydzyuban@gmail.com">Dzyuban Yuriy</a>
 *
 */
public class TransitionsTableDrawer extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1418687695919321969L;

    /**
     * Table on the current panel.
     */
    private JTable table;

    private JScrollPane scroll;

    private String[] columns;

    private Object[][] rows;

    private int numColumns = 7;

    private int numRows;
    
    private Data data;

    private JFrame mainFrame;

    /**
     * Single constructor of the class, calls parent constructor only.
     */
    public TransitionsTableDrawer(Data data, JFrame mainFrame) {
        super();

        this.data = data;
        this.mainFrame = mainFrame;
        table = new JTable();
        scroll = new JScrollPane(table);
        add(scroll);

        initializeColumns();
        configureTable();
        resizeScrollPane();
    }

    protected void initializeColumns() {
        columns = new String[numColumns];
        columns[0] = "#";
        columns[1] = "Branch";
        columns[2] = "Prev.marking";
        columns[3] = "Worked Transiotions";
        columns[4] = "Cur. marking";
        columns[5] = "MarkType";
        columns[6] = "Level";
    }

    protected void initializeRows() {

    	TableManagment myTable = new TableManagment(this.data);
        int[] typecrossing = new int[myTable.getAllT().size()];
        for (int i = 0; i < typecrossing.length; i++) {
            typecrossing[i] = 0;
            if (myTable.getAllT().get(i).getLyambda() == 0) {
                typecrossing[i] = 1;
            }
        }

        TreeofPetriNet mytree = new TreeofPetriNet(myTable.getAllP().size(),
                myTable.getAllT().size(), myTable.getMatrixDi(), myTable
                        .getMatrixDq(), myTable.getMarkirovka(), typecrossing, data);

        mytree.WriteResult(0);
        int numRows = mytree.getTransTable().count();
        rows = new Object[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            rows[i] = mytree.getTransTable().getAt(i).getObjectArray();
        }
    }

    protected void configureTable() {
        table.setRowSelectionAllowed(true);
        table.setFont(new Font("Lucida", 0, 11));

        int cellHeight = getFont().getSize() + 5;
        table.setRowHeight(cellHeight);
        table.getTableHeader().setReorderingAllowed(false);
        resizeTable();
    }

    protected void resizeTable() {
        int cellWidth = 100;
        int cellHeight = getFont().getSize() + 5;

        int tableWidth = numColumns * cellWidth;
        int tableHeight = numRows * cellHeight;

        table.setMaximumSize(new Dimension(tableWidth, tableHeight));
        table.setPreferredSize(new Dimension(tableWidth, tableHeight));
        table.setSize(new Dimension(tableWidth, tableHeight));
    }

    protected void resizeScrollPane() {
        int menuAndToolbarHeight = 135;
        int panelHeight = mainFrame.getHeight() - menuAndToolbarHeight;

        scroll.setMaximumSize(new Dimension(table.getWidth(), panelHeight));
        scroll.setPreferredSize(new Dimension(table.getWidth(), panelHeight));
        scroll.setSize(new Dimension(table.getWidth(), panelHeight));
        scroll.repaint();
    }

    /**
     * @param g
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    public void paint(Graphics g) {
        initializeRows();
        numRows = rows.length;

        StringTableModel stringTableModel = new StringTableModel(rows, columns);
        table.setModel(stringTableModel);

        resizeTable();
        resizeScrollPane();
    }

}
