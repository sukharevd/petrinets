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

/**
 * 
 */
package actions.menuundo;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import data.Data;

/**
 * Action, which is occurred when user clicks "Redo" component, it re-do one
 * operation of CommandStack.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public final class RedoAction extends AbstractAction {

    /**
     * 
     */
    private static final long serialVersionUID = -1308047552187361675L;

    private JFrame mainFrame;

    private Data data;

    /**
     * @param mainFrame
     */
    public RedoAction(Data data, JFrame mainFrame) {
        super();
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
        if (data.getCommandStack().getCurIndex() < data.getCommandStack()
                .getCommandList().size()) {
            data.getCommandStack().redoCur();
            mainFrame.repaint();
        }

    }

}
