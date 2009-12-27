/*
    Copyright (C)  2009  Sukharev Dmitriy, Dzyuban Yuriy, Voitova Anastasiia.
    
    This file is part of Petri nets Emulator.
    
    Petri nets Emulator is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    
    Petri nets Emulator is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    
    You should have received a copy of the GNU General Public License
    along with Petri nets Emulator. If not, see <http://www.gnu.org/licenses/>.
*/

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

/**
 * Action, which is occurred when user clicks "Emulate N events" component, it
 * calls methods for emulating N events of current petri-net.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class EmulatingEventsAction extends AbstractAction {

    /**
     * 
     */
    private static final long serialVersionUID = -2212606254206837705L;

    private EmulationManager emulator;

    private JFrame mainFrame;

    private Data data;

    public EmulatingEventsAction(EmulationManager emulator, Data data,
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

        int numberOfSteps = askNumberOfSteps();

        for (int i = 0; i < numberOfSteps; i++) {
            emulator.nextStep();
            if (emulator.isInDeadlock()) {
                break;
            }
        }
        mainFrame.repaint();
    }

    protected int askNumberOfSteps() {
        Integer numberOfSteps = 0;

        String title = "Input value";
        String message = "Number of steps:";
        String initialSelectionValue = "10000";
        numberOfSteps = Questioner.askInt(mainFrame, title, message,
                initialSelectionValue, null);

        // if was canceled
        if (numberOfSteps == null) {
            numberOfSteps = 0;
        }

        return numberOfSteps;
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
