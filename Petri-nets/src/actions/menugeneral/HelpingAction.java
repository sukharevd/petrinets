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

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Action, which is occurred when user clicks "Context help..." component, it
 * tries to open help-file in the browser.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class HelpingAction extends AbstractAction {

    /**
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = 2960508224617600516L;

    private JFrame mainFrame;

    /**
     * @param mainFrame
     */
    public HelpingAction(JFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
    }

    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(final ActionEvent arg0) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();

            // File file1 = null;
            // file1 = new
            // File(getClass().getResource("UserGuide.htm").getFile());

            File file = new File("UserGuide.htm");

            try {
                desktop.open(file);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(mainFrame,
                        "Help file \"UserGuide.htm\" can't be opened.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                System.err.println("Error: Help file \"UserGuide.htm\""
                        + "can't be opened.");

            }
        }

    }

}
