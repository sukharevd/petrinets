/**
 * 
 */
package view.tabtables;

import javax.swing.JTable;

import data.modeling.EmulatedStatisticMaker;
import data.modeling.EmulationManager;

/**
 * @author Admin
 * 
 */
public class JEmulStatisticTable extends JTable {

    /**
     * 
     */
    private static final long serialVersionUID = 655581167803749436L;

    private String[] columns;

    private Object[][] rows;

    private StringTableModel stringTableModel;

    private EmulationManager emulator;
    private EmulatedStatisticMaker statistic;

    public JEmulStatisticTable(EmulationManager emulator) {
        super();
        this.emulator = emulator;
        this.statistic = new EmulatedStatisticMaker(emulator);
        this.rows = null;
        initializeColumns();
        this.stringTableModel = new StringTableModel(rows, columns);
        setModel(stringTableModel);
    }

    protected void initializeColumns() {
        columns = new String[6];
        columns[0] = "Marking";
        columns[1] = "Frequency";
        columns[2] = "Sum Time";
        columns[3] = "Return Time";
        columns[4] = "Probability";
        columns[5] = "Return Freq";
    }

    public void updateRows() {
        this.statistic = new EmulatedStatisticMaker(emulator);  //??????
        rows = statistic.makeStatistic();
        stringTableModel.setDataVector(rows, columns);
        
    }

}
