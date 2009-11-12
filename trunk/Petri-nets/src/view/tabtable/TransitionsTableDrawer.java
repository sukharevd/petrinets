/**
 * 
 */
package view.tabtable;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

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

    private int numColumns = 6;

    private int numRows;

    private JFrame mainFrame;

    /**
     * Single constructor of the class, calls parent constructor only.
     */
    public TransitionsTableDrawer(JFrame mainFrame) {
        super();

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
        columns[5] = "Level";
    }

    protected void initializeRows() {
        // TODO: rewrite this method.
        int numRows = 50;
        rows = new Object[numRows][numColumns];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                rows[i][j] = i;
            }
        }
    }

    protected void configureTable() {
        table.setRowSelectionAllowed(true);
        table.setFont(new Font("Lucida", 0, 11));

        int cellHeight = getFont().getSize() + 5;
        table.setRowHeight(cellHeight);

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

        TransitionsTableModel transitionsTableModel = new TransitionsTableModel(rows, columns);
        table.setModel(transitionsTableModel);

        resizeTable();
        resizeScrollPane();
    }

}
