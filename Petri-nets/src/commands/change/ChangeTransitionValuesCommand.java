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

import commands.Command;

import data.elements.Transition;

/**
 * Class provides Undo/Redo operations for changing of Transition.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class ChangeTransitionValuesCommand implements Command {

    private ChangeTransitionValuesInvoker invoker;
    
    // TODO: somevar
    public ChangeTransitionValuesCommand(Transition t, double lyambda, double g, double r) {
        this.invoker = new ChangeTransitionValuesInvoker(t, lyambda, g, r);
    }

    /*
     * (non-Javadoc)
     * 
     * @see commands.Command#execute()
     */
    @Override
    public void execute() {
        invoker.changeTransitionValues();

    }

    /*
     * (non-Javadoc)
     * 
     * @see commands.Command#undo()
     */
    @Override
    public void undo() {
        invoker.undoChangeTransitionValues();
    }

}
