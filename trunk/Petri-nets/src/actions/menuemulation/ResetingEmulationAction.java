/**
 * 
 */
package actions.menuemulation;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import data.Data;
import data.modeling.EmulationManager;

/**
 * Action, which is occurred when user clicks "Reset Emulation" component, it
 * calls methods for reseting all emulation history of current petri-net.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class ResetingEmulationAction extends AbstractAction {

    /**
     * 
     */
    private static final long serialVersionUID = -2212606254206837705L;

    private EmulationManager emulator;

    private JFrame mainFrame;

    private Data data;

    public ResetingEmulationAction(EmulationManager emulator, Data data,
            JFrame mainFrame) {
        this.emulator = emulator;
        this.mainFrame = mainFrame;
        this.data = data;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        emulator.setData(data);
        mainFrame.repaint();
    }
}
