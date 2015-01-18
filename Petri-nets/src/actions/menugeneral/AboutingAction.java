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

/**
 * Action, which is occurred when user clicks "About..." component, it shows
 * message with information about the application and author of application.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class AboutingAction extends AbstractAction implements Cloneable {

    /**
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = 5745683949359609280L;

    private JFrame mainFrame;

    /**
     * @param mainFrame
     */
    public AboutingAction(final JFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
    }

    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(final ActionEvent e) {
        JOptionPane.showMessageDialog(mainFrame, "Petri nets Emulator\n"
                + "Copyright (C) 2009, 2010 Sukharev Dmitriy, "
                + "Dzyuban Yuriy, Vixen Tael", "About",
                JOptionPane.INFORMATION_MESSAGE);
        // TODO: change names.
    }

}
