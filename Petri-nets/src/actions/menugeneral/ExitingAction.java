/*
    Copyright (C)  2009  Sukharev Dmitriy, Dzyuban Yuriy, Vixen Tael.
    
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

/**
 * Action, which is occurred when user clicks "Exit" component, it saves current
 * chart if user wants and terminates the application.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class ExitingAction extends AbstractAction {

    /**
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = -3159018596429482640L;

    private Data data;

    private JFrame mainFrame;

    /**
     * @param data
     * @param mainFrame
     */
    public ExitingAction(Data data, JFrame mainFrame) {
        super();
        this.data = data;
        this.mainFrame = mainFrame;
    }

    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(final ActionEvent arg0) {
        exit(data, mainFrame);
    }

    /**
     * Saves current chart if user wants and terminates the application.
     */
    public static void exit(Data data, JFrame mainFrame) {
        if (data.isChanged() == false) {
            System.exit(0);
        }

        int opt = JOptionPane.showConfirmDialog(mainFrame,
                "Do you want to save changes?", "Exit",
                JOptionPane.YES_NO_CANCEL_OPTION);

        if (opt == JOptionPane.OK_OPTION) {
            SavingAction.save(data, mainFrame);
            if (data.isChanged() == false) {
                System.exit(0);
            }

        }
        if (opt == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

}
