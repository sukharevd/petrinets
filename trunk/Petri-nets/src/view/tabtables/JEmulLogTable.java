/**
 * 
 */
package view.tabtables;

import javax.swing.JTable;

import data.modeling.EmulationManager;
// TODO: add this class to project in feature.
/**
 * Tabular representation of transitions log of emulating, step by step.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class JEmulLogTable extends JTable {

    /**
     * 
     */
    private static final long serialVersionUID = 655581167803749436L;

    private String[] columns;

    private Object[][] rows;

    private StringTableModel stringTableModel;

    private EmulationManager emulator;

    public JEmulLogTable(EmulationManager emulator) {
        super();
        this.emulator = emulator;
        this.rows = null;
        initializeColumns();
        this.stringTableModel = new StringTableModel(rows, columns);
        setModel(stringTableModel);
    }

    protected void initializeColumns() {
        columns = new String[5];
        columns[0] = "Time";
        columns[1] = "Prev.Marking";
        columns[2] = "Next Marking";
        columns[3] = "Active Tran";
        columns[4] = "Started Trans";
    }

    public void updateRows() {
        int maxItemNumber = 50000;
        if (emulator.getLog().size() > maxItemNumber) {
            rows = new Object[1][5];
            stringTableModel.setDataVector(rows, columns);
            return;
        }
        rows = emulator.getLog().getObjectTable();
        stringTableModel.setDataVector(rows, columns);
        
//        EmulatedStatisticMaker maker = new EmulatedStatisticMaker(emulator);
//        maker.makeStatistic();
    }

}
