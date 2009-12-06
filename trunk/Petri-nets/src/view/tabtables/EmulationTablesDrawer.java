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
    
    private EmulLogTable table;

    private JScrollPane scroll;

    //private JFrame mainFrame;
    /**
     * @param data
     */
    public EmulationTablesDrawer(Data data, EmulationManager emulator) {
        this.data = data;
        //this.emulator = emulator;
        
        table = new EmulLogTable(emulator);
        scroll = new JScrollPane(table);
        add(scroll);
    }
    
    public void paint(Graphics g) {
        if (data.getElements().size() != 0) {
            table.updateRows();
            //table.repaint();
        }
    }
    
    
}
