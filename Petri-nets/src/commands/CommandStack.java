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
package commands;

import java.util.ArrayList;

/**
 * Stack of the commands, It is used for Undo/Redo operations, It provides
 * saving of commands and executing the commands.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class CommandStack {
    private int curIndex = -1;

    private ArrayList<Command> commandList = new ArrayList<Command>();

    /**
     * @return the commandList
     */
    public final ArrayList<Command> getCommandList() {
        return commandList;
    }

    /**
     * @param commandList
     *            the commandList to set
     */
    public final void setCommandList(ArrayList<Command> commandList) {
        this.commandList = commandList;
    }

    /**
     * @param curIndex
     *            the curIndex to set
     */
    public void setCurIndex(int curIndex) {
        this.curIndex = curIndex;
    }

    /**
     * @return the curIndex
     */
    public int getCurIndex() {
        return curIndex;
    }

    public void add(final Command command) {
        if (curIndex < commandList.size() - 1) {
            for (; curIndex < commandList.size() - 1;) {
                commandList.remove(curIndex + 1);
            }
        }
        commandList.add(command);
        curIndex++;
        // System.out.println("Added at " + curIndex);
        // TODO: not more than 100
    }

    public Command get(final int index) {
        return commandList.get(index);
    }

    public void set(final int index, final Command command) {
        commandList.set(index, command);
    }

    public void redoCur() {

        if (getCurIndex() + 1 < getCommandList().size()) {
            curIndex++;

            // System.out.println("Redo to " + curIndex);
            commandList.get(curIndex).execute();

        }
    }

    public void undoCur() {
        if (curIndex >= 0) {
            // System.out.println("Undo From " + curIndex);

            commandList.get(curIndex).undo();
            curIndex--;
        }
    }

}
