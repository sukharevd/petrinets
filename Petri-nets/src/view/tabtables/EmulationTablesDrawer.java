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
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultIntervalXYDataset;

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

    private int curStep;

    private JTable statisticTable;

    private JTable changingMarkStatisticTable;

    private JTable changingPMarkStatisticTable;

    private JScrollPane statisticScroll;

    private JScrollPane changingMarkStatisticScroll;

    private JScrollPane changingPMarkStatisticScroll;

    DefaultCategoryDataset frequencyDataset;
    DefaultCategoryDataset timeAvgDataset;
    DefaultCategoryDataset timeAvgReturnDataset;
    DefaultCategoryDataset probabilityDataset;

    JFreeChart chart;

    ChartPanel frequenceChartPanel;
    ChartPanel timeAvgChartPanel;
    ChartPanel timeAvgReturnChartPanel;
    ChartPanel probabilityChartPanel;

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
        this.curStep = 0;

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
        // ----------------------------------------------------
        JPanel panel;
        frequencyDataset = new DefaultCategoryDataset();
        chart = ChartFactory.createBarChart("Frequency", // chart title
                "Category", // domain axis label
                "Value", // range axis label
                frequencyDataset, // data
                PlotOrientation.VERTICAL, // orientation
                false, // include legend
                true, // tooltips?
                false // URLs?
                );
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryItemRenderer renderer = plot.getRenderer();
        renderer
                .setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);
        panel = new ChartPanel(chart, false);
        add(panel);
        
        timeAvgDataset = new DefaultCategoryDataset();
        chart = ChartFactory.createBarChart("Avg Time", // chart title
                "Category", // domain axis label
                "Value", // range axis label
                timeAvgDataset, // data
                PlotOrientation.VERTICAL, // orientation
                false, // include legend
                true, // tooltips?
                false // URLs?
                );
        plot = chart.getCategoryPlot();
        renderer = plot.getRenderer();
        renderer
                .setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);
        panel = new ChartPanel(chart, false);
        add(panel);
    }

    public void paint(Graphics g) {
        if (data.getElements().size() != 0) {
            // logTable.updateRows();
            updateTables();
            updateGraphs();
        }
    }   

    protected void updateTables() {
        Object[][] statisticRows;
        Object[] columns;

        updateStatistic();

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

    protected void updateStatistic() {
        if ((statistic == null) || (curStep != emulator.getLog().size())) {
            statistic = new EmulationStatisticMaker(emulator);
            statistic.calcTimes();
            curStep = emulator.getLog().size();
        }
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
    
    private void updateGraphs() {
        updateStatistic();
        
        frequencyDataset.clear();
        String series1 = "M0";
        for (int i = 0; i < emulator.getTransTable().getListOfMarking().size(); i++) {
            int val;
            val = statistic.getStatisticItemAt(i).getFrequency();
            frequencyDataset.addValue(val, "Frequency1", "M" + (i+1));
        }
//        frequencyDataset.addValue(Math.random(), series1, "0");
//        frequencyDataset.addValue(Math.random(), series1, "1");
//        frequencyDataset.addValue(3.0, series1, "2");
//        frequencyDataset.addValue(5.0, series1, "3");
//        frequencyDataset.addValue(5.0, series1, "4");
        //frequenceChartPanel.repaint();
        
        timeAvgDataset.clear();
        //String series1 = "M0";
        timeAvgDataset.addValue(Math.random(), series1, "0");
        timeAvgDataset.addValue(Math.random(), series1, "1");
        timeAvgDataset.addValue(3.0, series1, "2");
        timeAvgDataset.addValue(5.0, series1, "3");
        timeAvgDataset.addValue(5.0, series1, "4");
    }

}
