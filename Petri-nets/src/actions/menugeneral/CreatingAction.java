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

package actions.menugeneral;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import data.Data;
import data.modeling.EmulationManager;

/**
 * Action, which is occurred when user clicks "New" component, it saves current
 * chart (if user wants it), and creates new empty chart, which has only one new
 * empty row in the table.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class CreatingAction extends AbstractAction {

    /**
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = 8032711014811260318L;

    private Data data;
    
    private EmulationManager emulator;

    private JFrame mainFrame;

    /**
     * @param data
     */
    public CreatingAction(Data data, EmulationManager emulator, JFrame parentFrame) {
        super();
        this.data = data;
        this.emulator = emulator;
        this.mainFrame = parentFrame;
    }

    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(ActionEvent arg0) {

        if (data.isChanged() == true) {

            int opt = JOptionPane.showConfirmDialog(mainFrame,
                    "Do you want to save changes?", "Create",
                    JOptionPane.YES_NO_CANCEL_OPTION);

            if (opt == JOptionPane.OK_OPTION) {
                SavingAction.save(data, mainFrame);
                if (data.isChanged() == true) {
                    return;
                }
            }
            if (opt == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }

        data.getElements().clear();
        data.setChanged(false);

        data.setActiveElement(null);
        data.setAddingModeElement(null);
        
        emulator.setData(data);
        
        // Main.getAppFrame().getElementDrawer().repaint();
        mainFrame.repaint();
    }

}
