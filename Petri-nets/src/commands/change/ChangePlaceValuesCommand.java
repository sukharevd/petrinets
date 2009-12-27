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
 * This package contains Invokers, Commands and Stack of Commands
 * for Undo/Redo operations.
 */
package commands.change;

import commands.Command;

import data.elements.Place;

/**
 * Class provides Undo/Redo operations for changing of Place.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class ChangePlaceValuesCommand implements Command {

    private ChangePlaceValuesInvoker invoker;

    /**
     * @param place
     * @param numToken
     */
    public ChangePlaceValuesCommand(Place place, int numToken) {
        this.invoker = new ChangePlaceValuesInvoker(place, numToken);
    }

    /*
     * (non-Javadoc)
     * 
     * @see commands.Command#execute()
     */
    @Override
    public void execute() {
        invoker.changePlaceValues();

    }

    /*
     * (non-Javadoc)
     * 
     * @see commands.Command#undo()
     */
    @Override
    public void undo() {
        invoker.undoChangePlaceValues();
    }

}
