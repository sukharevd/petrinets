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

package actions.menuadd;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import data.Data;
import data.elements.Arc;

/**
 * Action, which is occurred when user clicks "Add Arc" component, it adds new
 * arc to the graph.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class AddingArcAction extends AbstractAction {

    /**
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = 1311208811561183876L;

    private Data data;

    public AddingArcAction(final Data data) {
        this.data = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        data.setAddingModeElement(new Arc(new ArrayList<Integer>(),
                new ArrayList<Integer>(), -1, null));

        // String imageName = "arc24";
        //        
        // String imgLocationPng = "res/" + imageName + ".png";
        // URL imageURL = AppFrame.class.getResource(imgLocationPng);
        //
        // if (imageURL != null) { // image found
        // button.setIcon(new ImageIcon(imageURL, "Add New Arc"));
        // }

    }

}
