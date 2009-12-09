/**
 * 
 */
package view.tabtables;

import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.Data;
import data.modeling.EmulationManager;

/**
 * @author Admin
 *
 */
public class EmulationTablesDrawer extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = -4723199639971892957L;
    
    //private EmulationManager emulator;
    private Data data;
    
    private JEmulLogTable logTable;
    private JEmulStatisticTable statisticTable;

    private JScrollPane logScroll;
    private JScrollPane statisticScroll;
    

    //private JFrame mainFrame;
    /**
     * @param data
     */
    public EmulationTablesDrawer(Data data, EmulationManager emulator) {
        this.data = data;
        //this.emulator = emulator;
        
        logTable = new JEmulLogTable(emulator);
        logScroll = new JScrollPane(logTable);
        add(logScroll);
        
        statisticTable = new JEmulStatisticTable(emulator);
        statisticScroll = new JScrollPane(statisticTable);
        add(statisticScroll);
    }
    
    public void paint(Graphics g) {
        if (data.getElements().size() != 0) {
            logTable.updateRows();
            statisticTable.updateRows();
            //table.repaint();
        }
    }
    
    
}
