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


// TODO: make subclasses for every table. Use OOP.
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;


import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import data.Data;
import data.TableManagment;
import data.elements.Element;
import data.elements.Transition;


/**
 * Some table and set of the method for painting at it.
 *
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * @author <a href="mailto:ydzyuban@gmail.com">Dzyuban Yuriy</a>
 *
 */
public class DescriptiveTableDrawer extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1418687695919321969L;


    /**
     * Table on the current panel.
     */
    private JTable diTable;
    private JTable dqTable;
    private JTable markingTable;
    private JTable lyambdaTable;


    private JScrollPane diScroll;
    private JScrollPane dqScroll;
    private JScrollPane markingScroll;
    private JScrollPane lyambdaScroll;


    private String[] columns;


    private Object[][] rows;


    private int numColumns;


    private int numRows;
    
    private Data data;


    private TableManagment tableManager;


    /**
     * Single constructor of the class, calls parent constructor only.
     */
    public DescriptiveTableDrawer(Data data) {
        super();


        this.data = data;
        
        BoxLayout boxY = new BoxLayout(this,BoxLayout.Y_AXIS);
        setLayout(boxY);
        setAlignmentX(CENTER_ALIGNMENT);


        diTable = new JTable();
        dqTable = new JTable();
        markingTable = new JTable();
        lyambdaTable = new JTable();


        diScroll = new JScrollPane(diTable);
        dqScroll = new JScrollPane(dqTable);
        markingScroll = new JScrollPane(markingTable);
        lyambdaScroll = new JScrollPane(lyambdaTable);
        
        addScrollTable(diScroll, diTable, "Di");
        addScrollTable(dqScroll, dqTable, "Dq");
        addScrollTable(markingScroll, markingTable, "Marking");
        addScrollTable(lyambdaScroll, lyambdaTable, "Lyambda");
    }


    protected void addScrollTable(JScrollPane scroll, JTable table, String name) {
        JLabel l = new JLabel(name);
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        l.setVisible(true);
        add(l);

        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(scroll);


        configureTable(table);
        resizeScrollPane(scroll, table);
    }


    protected void initializeColumns(Object[] elements) {
        int width = elements.length;
        columns = new String[width+1];
        for (int i = 0; i < width; i++) {
            columns[i+1] = ((Element)elements[i]).getTitle();            
        }
        columns[0] = "";
        numColumns = columns.length;
    }


    protected void initializeRowsAsDouble(double[][] rowsTable, Object[] elements) {
        // TODO: rewrite this method.
        double dRows[][] = rowsTable;
        int height = dRows.length;
        int width = dRows[0].length;
        rows = new Object[height][width+1];
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                rows[i][j+1] = rowsTable[i][j];
            }
        }
        
        numRows = rows.length;
    }
    
    protected void initializeRows(int[][] rowsTable, Object[] trans) {
        // TODO: rewrite this method.
        int intDi[][] = rowsTable;
        int height = intDi.length;
        int width = intDi[0].length;
        rows = new Object[height][width+1];
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                rows[i][j+1] = rowsTable[i][j];
            }
        }
        
        for (int i = 0; i < height; i++) {
            if (trans != null) {
                rows[i][0] = ((Transition)trans[i]).getTitle();
            }
        }
        numRows = rows.length;
    }
    protected void configureTable(JTable table) {
        table.setRowSelectionAllowed(true);
        table.setFont(new Font("Lucida", 0, 11));


        int cellHeight = getFont().getSize() + 5;
        table.setRowHeight(cellHeight);
        table.getTableHeader().setReorderingAllowed(false);
        resizeTable(table);
    }


    protected void resizeTable(JTable table) {
        int cellWidth = 50;
        int cellHeight = getFont().getSize() + 5;


        int tableWidth = numColumns * cellWidth;
        int tableHeight = numRows * cellHeight;
        
        for (int i = 0; i < numColumns; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(cellWidth);
            table.getColumnModel().getColumn(i).setMaxWidth(cellWidth);
        }


        table.setMaximumSize(new Dimension(tableWidth, tableHeight));
        table.setPreferredSize(new Dimension(tableWidth, tableHeight));
        table.setSize(new Dimension(tableWidth, tableHeight));
    }


    protected void resizeScrollPane(JScrollPane scroll, JTable table) {
        //int menuAndToolbarHeight = 135;
        //int panelHeight = mainFrame.getHeight() - menuAndToolbarHeight;
        int tableHeight = table.getHeight() + table.getTableHeader().getHeight();
        int tableWidth = table.getWidth()+5;
        
        scroll.setMaximumSize(new Dimension(tableWidth, tableHeight));
        scroll.setPreferredSize(new Dimension(tableWidth, tableHeight));
        scroll.setSize(new Dimension(tableWidth, tableHeight));
        scroll.repaint();
    }
    
    public void paintDi(Graphics g) {
        tableManager = new TableManagment(data);
        int[][] rowsTable = tableManager.getMatrixDi();
        Object[] placeNames = tableManager.getAllP().toArray();
        Object[] tranNames = tableManager.getAllT().toArray();
        initializeColumns(placeNames);
        initializeRows(rowsTable, tranNames);
        


        DescriptiveTableModel tableModel = new DescriptiveTableModel(rows, columns);
        diTable.setModel(tableModel);


        resizeTable(diTable);
        resizeScrollPane(diScroll, diTable);
    }
    
    public void paintDq(Graphics g) {
        tableManager = new TableManagment(data);
        int[][] rowsTable = tableManager.getMatrixDq();
        Object[] placeNames = tableManager.getAllP().toArray();
        Object[] tranNames = tableManager.getAllT().toArray();
        initializeColumns(placeNames);
        initializeRows(rowsTable, tranNames);
        


        DescriptiveTableModel tableModel = new DescriptiveTableModel(rows, columns);
        dqTable.setModel(tableModel);


        resizeTable(dqTable);
        resizeScrollPane(dqScroll, dqTable);
    }
    
    public void paintMarking(Graphics g) {
        
        tableManager = new TableManagment(data);
        int[] rowsTable0 = tableManager.getMarkirovka();
        int[][] rowsTable = new int[1][rowsTable0.length];
        rowsTable[0] = rowsTable0;
        Object[] placeNames = tableManager.getAllP().toArray();
        initializeColumns(placeNames);
        initializeRows(rowsTable, null);
        


        DescriptiveTableModel tableModel = new DescriptiveTableModel(rows, columns);
        markingTable.setModel(tableModel);


        resizeTable(markingTable);
        resizeScrollPane(markingScroll, markingTable);
    }
    
    public void paintLyambda(Graphics g) {
        
        tableManager = new TableManagment(data);
        double[] rowsTable0 = tableManager.getLyambdaArray();
        double[][] rowsTable = new double[1][rowsTable0.length];
        rowsTable[0] = rowsTable0;
        Object[] tranNames = tableManager.getAllT().toArray();
        initializeColumns(tranNames);
        initializeRowsAsDouble(rowsTable, null);
        


        DescriptiveTableModel tableModel = new DescriptiveTableModel(rows, columns);
        lyambdaTable.setModel(tableModel);


        resizeTable(lyambdaTable);
        resizeScrollPane(lyambdaScroll, markingTable);
    }


    /**
     * @param g
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    public void paintComponent(Graphics g) {
               
        if ((data.getElements() != null) && (data.getElements().size() != 0)) {
            paintDi(g);
            paintDq(g);
            paintMarking(g);
            paintLyambda(g);
        }
    }   
}