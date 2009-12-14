/**
 * 
 */
package view.tabtables;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import data.Data;
import data.modeling.EmulationManager;
import data.modeling.EmulationStatisticMaker;

/**
 * Area and set of the method for painting emulation tables and charts at it.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class EmulationTablesDrawer extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = -4723199639971892957L;

    private EmulationManager emulator;

    private Data data;

    private JTable statisticTable;

    private JTable changingMarkStatisticTable;

    private JTable changingPMarkStatisticTable;

    private JScrollPane statisticScroll;

    private JScrollPane changingMarkStatisticScroll;

    private JScrollPane changingPMarkStatisticScroll;

    ChartPanel panel;

    private EmulationStatisticMaker statistic;

    // private JFrame mainFrame;
    /**
     * @param data
     */
    public EmulationTablesDrawer(Data data, EmulationManager emulator,
            JFrame mainFrame) {
        BoxLayout boxY = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxY);
        setAlignmentX(CENTER_ALIGNMENT);

        this.data = data;
        this.emulator = emulator;

        initialize();

    }

    private void initialize() {
        statisticTable = new JTable(new StringTableModel(new Object[0][0],
                new Object[0]));
        statisticScroll = new JScrollPane(statisticTable);
        statisticScroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(statisticScroll);

        changingMarkStatisticTable = new JTable(new StringTableModel(
                new Object[0][0], new Object[0]));
        changingMarkStatisticScroll = new JScrollPane(
                changingMarkStatisticTable);
        changingMarkStatisticScroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(changingMarkStatisticScroll);

        changingPMarkStatisticTable = new JTable(new StringTableModel(
                new Object[0][0], new Object[0]));
        changingPMarkStatisticScroll = new JScrollPane(
                changingPMarkStatisticTable);
        changingPMarkStatisticScroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(changingPMarkStatisticScroll);

        DefaultPieDataset data1 = new DefaultPieDataset();
        data1.setValue("Category 1", 43.2);
        data1.setValue("Category 2", 27.9);
        data1.setValue("Category 3", 79.5);
        // create a chart...
        JFreeChart chart = ChartFactory.createPieChart("Sample Pie Chart",
                data1, true, // legend?
                true, // tooltips?
                false // URLs?
                );
        // create and display a frame...
        panel = new ChartPanel(chart);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(panel);
        panel.setVisible(true);
    }

    public void paint(Graphics g) {
        if (data.getElements().size() != 0) {
            // logTable.updateRows();
            updateTables();
            panel.repaint();
        }
    }

    protected void updateTables() {
        Object[][] statisticRows;
        Object[] columns;

        statistic = new EmulationStatisticMaker(emulator);
        statistic.calcTimes();

        statisticRows = statistic.makeEmulationStatistic();
        columns = getEmulationStatisticColumns();
        ((DefaultTableModel) statisticTable.getModel()).setDataVector(
                statisticRows, columns);

        statisticRows = statistic.makeChangingMarkingsStatistic();
        columns = getChangingMarkingsStatisticColumns(statisticRows);
        ((DefaultTableModel) changingMarkStatisticTable.getModel())
                .setDataVector(statisticRows, columns);

        statisticRows = statistic.makeChangingProbabilityMarkingsStatistic();
        ((DefaultTableModel) changingPMarkStatisticTable.getModel())
                .setDataVector(statisticRows, columns);
    }

    protected Object[] getEmulationStatisticColumns() {
        Object[] columns = new String[6];
        columns[0] = "Marking";
        columns[1] = "Frequency";
        columns[2] = "Sum Time";
        columns[3] = "Return Time";
        columns[4] = "Probability";
        columns[5] = "Return Freq";
        return columns;
    }

    protected Object[] getChangingMarkingsStatisticColumns(Object[][] rows) {
        Object[] columns;

        int size = 0;
        if (rows.length != 0) {
            size = rows[0].length;
        }

        columns = new String[size];
        columns[0] = "";
        for (int i = 0; i < size - 1; i++) {
            columns[i + 1] = "M" + i;
        }
        return columns;
    }
}
