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
 * This package contains Invokers, Commands and Stack of Commands
 * for Undo/Redo operations.
 */
package commands.add;

import javax.swing.JOptionPane;

import view.ElementFinder;

import commands.Command;
import commands.CommandFilter;

import data.Data;
import data.elements.Arc;
import data.elements.Element;
import data.elements.Place;
import data.elements.Transition;

/**
 * Filters element adding command if it is wrong.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class AddElementCommandFilter implements CommandFilter {

    private Data data;

    private int mouseX;

    private int mouseY;

    /**
     * @param data
     * @param mouseX
     * @param mouseY
     */
    public AddElementCommandFilter(Data data, int mouseX, int mouseY) {
        this.data = data;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    protected boolean isFiltratedPlaceTransition() {
        Element selectedEl = ElementFinder.findElement(mouseX, mouseY, data);

        if ((selectedEl != null) && (!(selectedEl instanceof Arc))) {

            String message = "You cann't add element over other element.";
            String title = "Error of drawing";
            JOptionPane.showMessageDialog(null, message, title,
                    JOptionPane.WARNING_MESSAGE);
            return true;
        }

        return false;
    }
    
    protected boolean isFiltratedArc() {
        Arc addedArc = (Arc) data.getAddingModeElement();

        Element selectedEl = ElementFinder.findElement(mouseX,
                mouseY, data);

        if (addedArc.getXsequence().size() > 0) {
            if ((selectedEl != null)
                    && (!(selectedEl instanceof Arc))) {
                int x1 = addedArc.getXsequence().get(0);
                int y1 = addedArc.getYsequence().get(0);
                String s1 = ElementFinder.findElement(x1, y1, data)
                        .getType();
                String s2 = selectedEl.getType();

                if (s1 == s2) {
                    JOptionPane.showMessageDialog(null,
                            "You cann't link equal elements.",
                            "Error of drawing",
                            JOptionPane.WARNING_MESSAGE);
                    return true;
                }
            }
        } else {
            if ((selectedEl == null)
                    || (selectedEl instanceof Arc)) {
                return true;
            }
        }

        return false;
    }

    public Command filtrate(Command command) {
        Element addedElement = data.getAddingModeElement();
        if (addedElement != null) {

            if ((addedElement instanceof Place)
                    || (addedElement instanceof Transition)) {
                if (isFiltratedPlaceTransition()) {
                    return null;
                }
            } else {
                if (addedElement instanceof Arc) {
                    if (isFiltratedArc()) {
                        return null;
                    }
                }
            }
        }
        return command;

    }
}
