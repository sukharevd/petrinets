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
import org.jfree.data.category.DefaultCategoryDataset;

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

    private EmulationStatisticMaker statistic;

    private Data data;

    private int curStep;

    private JTable statisticTable;

    private JTable changingMarkStatisticTable;

    private JTable changingPMarkStatisticTable;

    private JTable summaryTable;

    private DefaultCategoryDataset frequencyDataset;

    private DefaultCategoryDataset timeAvgDataset;

    private DefaultCategoryDataset timeAvgReturnDataset;

    private DefaultCategoryDataset probabilityDataset;

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

        tablesInitialize();
        graphsInitialize();
    }

    private void tablesInitialize() {
        JScrollPane scroll;

        statisticTable = new JTable(new StringTableModel(new Object[0][0],
                new Object[0]));
        statisticTable.getTableHeader().setReorderingAllowed(false);
        scroll = new JScrollPane(statisticTable);
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(scroll);

        changingMarkStatisticTable = new JTable(new StringTableModel(
                new Object[0][0], new Object[0]));
        scroll = new JScrollPane(changingMarkStatisticTable);
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(scroll);

        changingPMarkStatisticTable = new JTable(new StringTableModel(
                new Object[0][0], new Object[0]));
        scroll = new JScrollPane(changingPMarkStatisticTable);
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(scroll);

        summaryTable = new JTable(new StringTableModel(new Object[0][0],
                new Object[0]));
        scroll = new JScrollPane(summaryTable);
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(scroll);
    }

    protected void graphsInitialize() {
        JPanel panel;
        JFreeChart chart;

        frequencyDataset = new DefaultCategoryDataset();
        chart = ChartFactory.createBarChart("Frequency", // chart title
                "Marking", // domain axis label
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
                "Marking", // domain axis label
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

        timeAvgReturnDataset = new DefaultCategoryDataset();
        chart = ChartFactory.createBarChart("Avg Return Time", // chart title
                "Marking", // domain axis label
                "Value", // range axis label
                timeAvgReturnDataset, // data
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

        probabilityDataset = new DefaultCategoryDataset();
        chart = ChartFactory.createBarChart("Probability", // chart title
                "Marking", // domain axis label
                "Value", // range axis label
                probabilityDataset, // data
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

        statisticRows = new Object[1][3];
        statisticRows[0][0] = emulator.getData().getMarking().toString();
        statisticRows[0][1] = statistic.getStepsQuantity();
        statisticRows[0][2] = statistic.getSumTime();
        columns = getResumeColumns();
        ((DefaultTableModel) summaryTable.getModel()).setDataVector(
                statisticRows, columns);

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

    protected Object[] getResumeColumns() {
        Object[] columns = new String[3];
        columns[0] = "Current Marking";
        columns[1] = "Current Step";
        columns[2] = "Current Time";
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
        timeAvgDataset.clear();
        timeAvgReturnDataset.clear();
        probabilityDataset.clear();
        String series1 = "M0";
        for (int i = 0; i < emulator.getTransTable().getListOfMarking().size(); i++) {
            String descriptive = "M" + (i + 1);
            int freq = statistic.getStatisticItemAt(i).getFrequency();
            double sumTime = statistic.getStatisticItemAt(i).getSumTime();
            double sumReturnTime = statistic.getStatisticItemAt(i)
                    .getReturnTime();
            double probability = statistic.getStatisticItemAt(i)
                    .getProbability(statistic.getSumTime());
            frequencyDataset.addValue(freq, "Frequency1", descriptive);
            timeAvgDataset.addValue(sumTime / (freq - 1), series1, descriptive);
            timeAvgReturnDataset.addValue(sumReturnTime / freq, series1,
                    descriptive);
            probabilityDataset.addValue(probability, series1, descriptive);
        }
    }
}
