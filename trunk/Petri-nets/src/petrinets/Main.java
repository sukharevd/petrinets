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

package petrinets;

import java.util.ArrayList;

import view.AppFrame;
import data.Data;
import data.elements.Element;
import data.modeling.EmulationManager;

/**
 * Entry point of application.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * @author <a href="mailto:ydzyuban@gmail.com">Dzyuban Yuriy</a>
 * @author <a href="mailto:vixentael@gmail.com">Voitova Anastasiia</a>
 * 
 */
public final class Main {

    /**
     * Data of the application.
     */
    private static Data data = new Data(new ArrayList<Element>());
    
    /**
     * Emulation manager of application, it emulates the work of petri-net. 
     */
    private static EmulationManager emulator = new EmulationManager();
    
    /**
     * Frame of the application.
     */
    private static AppFrame appFrame = new AppFrame(data, emulator);
    
    

    /**
     * Starts GUI shell.
     * 
     * @param args
     *            the args of cmd line
     */
    public static synchronized void main(final String[] args) {
        appFrame.createAndShowGUI();
    }

}