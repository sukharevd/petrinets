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

package data.modeling;

import java.util.ArrayList;

import data.Marking;
import data.elements.Transition;

/**
 * Transition table of a Petri net.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class TransitionsTable {

    private ArrayList<TransitionsTableRow> rows;

    /**
     * 
     */
    public TransitionsTable() {
        this.rows = new ArrayList<TransitionsTableRow>();
    }

    /**
     * @param rows
     */
    public TransitionsTable(ArrayList<TransitionsTableRow> rows) {
        this.rows = rows;
    }

    /**
     * @return the rows
     */
    public final ArrayList<TransitionsTableRow> getRows() {
        return rows;
    }

    /**
     * @param rows
     *            the rows to set
     */
    public final void setRows(ArrayList<TransitionsTableRow> rows) {
        this.rows = rows;
    }

    /**
     * 
     * @param index
     */
    public TransitionsTableRow getAt(int index) {
        return rows.get(index);
    }

    /**
     * 
     * @param index
     * @param transitionsTableRow
     */
    public void setAt(int index, TransitionsTableRow transitionsTableRow) {
        rows.set(index, transitionsTableRow);
    }

    /**
     * 
     * @param transitionsTableRow
     */
    public void add(TransitionsTableRow transitionsTableRow) {
        rows.add(transitionsTableRow);
    }

    public int count() {
        return rows.size();
    }

    /**
     * 
     * @param marking
     */
    public ArrayList<TransitionsTableRow> selectAllWithPrevMarking(
            Marking marking) {
        ArrayList<TransitionsTableRow> list = new ArrayList<TransitionsTableRow>();

        for (int i = 0; i < rows.size(); i++) {
            TransitionsTableRow row = rows.get(i);
            if ((row.getLevel() > 0) && (row.getPrevMarking().equals(marking))) {
                list.add(row);
            }
        }

        return list;
    }

    /**
     * 
     * @param transition
     * @param marking
     */
    public ArrayList<TransitionsTableRow> selectAllWithTransPrevMarking(
            Transition transition, Marking marking) {
        ArrayList<TransitionsTableRow> list = new ArrayList<TransitionsTableRow>();

        for (int i = 0; i < rows.size(); i++) {
            TransitionsTableRow row = rows.get(i);
            if ((row.getLevel() > 0) && (row.getPrevMarking().equals(marking))) {
                ArrayList<Transition> trans = rows.get(i)
                        .getWorkedTransitions();
                for (int j = 0; j < trans.size(); j++) {
                    if (trans.get(j).getNo() == transition.getNo()) {
                        list.add(row);
                    }
                }
            }
        }

        return list;
    }

    public ArrayList<TransitionsTableRow> selectDeadlocks() {
        ArrayList<TransitionsTableRow> list = new ArrayList<TransitionsTableRow>();

        for (int i = 0; i < rows.size(); i++) {
            TransitionsTableRow row = rows.get(i);
            if (row.getMarkType() == MarkType.DEADLOCK) {
                list.add(row);
            }
        }
        return list;
    }

    /**
     * 
     */
    public TransitionsTableRow selectRoot() {
        TransitionsTableRow root = null;
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i).getMarkType() == MarkType.ROOT) {
                root = rows.get(i);
            }
        }

        return root;
    }

    public ArrayList<Marking> getListOfMarking() {
        ArrayList<Marking> markings = new ArrayList<Marking>();

        for (int i = 0; i < rows.size(); i++) {
            Marking marking = rows.get(i).getNextMarking();
            boolean is_contains = false;
            for (int j = 0; j < markings.size(); j++) {
                if (markings.get(j).equals(marking)) {
                    is_contains = true;
                    break;
                }
            }
            if (!is_contains) {
                markings.add(marking);
            }
        }
        return markings;
    }
}