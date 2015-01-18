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
package commands.change;

import data.elements.Place;

/**
 * Provides executing of Undo/Redo operations for changing of Place.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class ChangePlaceValuesInvoker {
    private Place place;

    private int v1;

    private int v2;

    /**
     * @param place
     * @param numToken
     */
    public ChangePlaceValuesInvoker(Place place, int numToken) {
        super();
        this.place = place;
        this.v1 = place.getNumTokens();
        this.v2 = numToken;
    }

    public void changePlaceValues() {
        place.setNumTokens(v2);
    }

    public void undoChangePlaceValues() {
        place.setNumTokens(v1);
    }

}
