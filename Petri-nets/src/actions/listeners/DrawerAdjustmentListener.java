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
 * This package contains listeners for mouse, keyboard, scroll motion and window.
 */
package actions.listeners;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JFrame;

/**
 * This class listens the scroll motions, it performs repainting of a scrolled
 * component.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class DrawerAdjustmentListener implements AdjustmentListener {

    /**
     * Application's frame.
     */
    private JFrame frame;

    /**
     * Constructor of {@link DrawerAdjustmentListener}.
     * 
     * @param frame
     *            new frame value
     */
    public DrawerAdjustmentListener(JFrame frame) {
        this.frame = frame;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.AdjustmentListener#adjustmentValueChanged(java.awt.event
     * .AdjustmentEvent)
     */
    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        frame.repaint();
    }
}
