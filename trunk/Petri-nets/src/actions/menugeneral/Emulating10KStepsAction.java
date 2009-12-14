/**
 * 
 */
package actions.menugeneral;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import data.Data;
import data.Marking;
import data.modeling.EmulationManager;

/**
 * Action, which is occurred when user clicks "Emulate 10K steps" component, it
 * calls methods for emulating 10 000 steps of current petri-net.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class Emulating10KStepsAction extends AbstractAction {

    /**
     * 
     */
    private static final long serialVersionUID = -2212606254206837705L;

    private EmulationManager emulator;

    private JFrame mainFrame;

    private Data data;

    public Emulating10KStepsAction(EmulationManager emulator, Data data,
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
        if (!areEqualWithoutMarking()) {
            emulator.setData(data);
        }

        int numberOfSteps = 10000;
        for (int i = 0; i < numberOfSteps; i++) {
            emulator.nextStep();
        }
        mainFrame.repaint();
    }

    protected boolean areEqualWithoutMarking() {
        boolean isEquals;

        // backup emulation marking
        Marking emulationMarking = emulator.getData().getMarking();

        // equalize emulation marking
        emulator.getData().changeDataMarking(data.getMarking());

        // compare main data with emulation data
        isEquals = data.equals(emulator.getData());

        // restore emulation marking
        emulator.getData().changeDataMarking(emulationMarking);

        return isEquals;
    }

}
