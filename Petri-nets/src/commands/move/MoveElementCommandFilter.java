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
package commands.move;

import view.ElementFinder;

import commands.Command;
import commands.CommandFilter;

import data.Data;
import data.elements.Arc;
import data.elements.Element;

/**
 * Filters element adding command if it is wrong.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class MoveElementCommandFilter implements CommandFilter {

    private Data data;

    private int mouseX;

    private int mouseY;

    /**
     * @param data
     * @param mouseX
     * @param mouseY
     */
    public MoveElementCommandFilter(Data data, int mouseX, int mouseY) {
        this.data = data;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    public Command filtrate(Command command) {
        Element addedElement = data.getAddingModeElement();

        if (addedElement == null) {
            Element selElement = data.getActiveElement();

            Element atRelease = ElementFinder.findElement(mouseX, mouseY, data);

            if ((selElement != null) && (!(selElement instanceof Arc))
                    && (mouseX > 0)
                    && (mouseY > 0)
                    && ((atRelease == null) || (atRelease == selElement))
                    // TODO: magic number (1)
                    && ((Math.abs(selElement.getX() - mouseX) > 1) || (Math
                            .abs(selElement.getY() - mouseY)) > 1)) {
                return command;
            }
        }

        return null;

    }
}
