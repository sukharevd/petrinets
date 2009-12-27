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

package actions.menuadd;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import data.Data;
import data.elements.Place;

/**
 * Contains features of the transition of petri-net, Provides methods for
 * setting and getting this features.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class AddingPlaceAction extends AbstractAction {

    /**
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = -3838500188334807889L;

    private Data data;

    /**
     * @param data
     */
    public AddingPlaceAction(Data data) {
        super();
        this.data = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        data.setAddingModeElement(new Place(0, -1, -1, -1));
    }

}
