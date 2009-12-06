/**
 * 
 */
package view.tabtables;

import javax.swing.JTable;

import data.modeling.EmulationManager;

/**
 * @author Admin
 * 
 */
public class EmulLogTable extends JTable {

    /**
     * 
     */
    private static final long serialVersionUID = 655581167803749436L;

    private String[] columns;

    private Object[][] rows;

    private StringTableModel stringTableModel;

    private EmulationManager emulator;

    public EmulLogTable(EmulationManager emulator) {
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
        rows = emulator.getLog().getObjectTable();
        stringTableModel.setDataVector(rows, columns);
    }

}
