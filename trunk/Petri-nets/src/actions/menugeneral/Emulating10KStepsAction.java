/**
 * 
 */
package actions.menugeneral;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import data.modeling.EmulationManager;

/**
 * @author Admin
 *
 */
public class Emulating10KStepsAction extends AbstractAction {

    /**
     * 
     */
    private static final long serialVersionUID = -2212606254206837705L;
    private EmulationManager emulator;
    private JFrame mainFrame;
    
    public Emulating10KStepsAction(EmulationManager emulator, JFrame mainFrame) {
        this.emulator = emulator;
        this.mainFrame = mainFrame;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int numberOfSteps = 10000;
        for (int i = 0; i < numberOfSteps; i++) {
            emulator.nextStep();
        }        
        mainFrame.repaint();
    }

}
