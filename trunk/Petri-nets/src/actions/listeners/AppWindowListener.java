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

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import actions.menugeneral.ExitingAction;
import data.Data;

/**
 * This class listens the window closing.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class AppWindowListener extends WindowAdapter {

    /**
     * Application's {@link Data}-object.
     */
    private Data data;

    /**
     * Application's frame.
     */
    private JFrame mainFrame;

    /**
     * Constructor of {@link AppWindowListener}.
     * 
     * @param data
     *            new data value
     * @param mainFrame
     *            new mainFrame value
     */
    public AppWindowListener(Data data, JFrame mainFrame) {
        super();
        this.data = data;
        this.mainFrame = mainFrame;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
     */
    public void windowClosing(final WindowEvent we) {
        ExitingAction.exit(data, mainFrame);
    }

}
