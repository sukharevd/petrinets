/**
 * 
 */
package actions.menuemulation;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import actions.Questioner;

import data.Data;
import data.Marking;
import data.modeling.EmulationManager;
import data.modeling.EmulationStatisticMaker;

/**
 * Action, which is occurred when user clicks "Emulate while time" component, it
 * calls methods for emulating current petri-net while current time is less time
 * which is set by user.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class EmulatingTimesAction extends AbstractAction {

    /**
     * 
     */
    private static final long serialVersionUID = -2212606254206837705L;

    private EmulationManager emulator;

    private JFrame mainFrame;

    private Data data;

    public EmulatingTimesAction(EmulationManager emulator, Data data,
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

        double finishTime = askTimeOfEmulation();
        EmulationStatisticMaker statistic = new EmulationStatisticMaker(
                emulator);
        statistic.calcTimes();
        while (statistic.getSumTime() < finishTime) {
            emulator.nextStep();
            statistic.calcTimes();
            if (emulator.isInDeadlock()) {
                break;
            }
        }

        mainFrame.repaint();
    }

    protected double askTimeOfEmulation() {
        Double timeOfEmulation;

        String title = "Input value";
        String message = "Time of emulation (in seconds):";
        String initialSelectionValue = "100.0";
        timeOfEmulation = Questioner.askDouble(mainFrame, title, message,
                initialSelectionValue, null);

        // if was canceled
        if (timeOfEmulation == null) {
            timeOfEmulation = 0.0;
        }

        return timeOfEmulation;
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
