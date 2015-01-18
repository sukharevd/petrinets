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
package actions.menugeneral;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import view.Scalable;

/**
 * Action, which is occurred when user clicks "Scale Plus" or "Scale Minus"
 * components, it changes scale-variable of Scaleable objects.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class ScalingPanelAction extends AbstractAction {

    /**
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = 6646941469680936637L;

    private Scalable scaledPanel;

    private boolean isPlus;

    public ScalingPanelAction(Scalable scaledPanel, boolean isPlus) {
        this.scaledPanel = scaledPanel;
        this.isPlus = isPlus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isPlus) {
            scaledPanel.incScale();
        } else {
            scaledPanel.decScale();
        }
        // scaledPanel.repaint();
    }

}
