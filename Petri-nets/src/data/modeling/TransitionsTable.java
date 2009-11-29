package data.modeling;

import java.util.ArrayList;

import data.Marking;

public class TransitionsTable {

	private ArrayList<TransitionsTableRow> rows;

	/**
     * 
     */
    public TransitionsTable() {
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
     * @param rows the rows to set
     */
    public final void setRows(ArrayList<TransitionsTableRow> rows) {
        this.rows = rows;
    }


	/**
	 * 
	 * @param index
	 */
	public TransitionsTableRow getAt(int index) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param index
	 * @param TransitionsTableRow
	 */
	public void setAt(int index, int TransitionsTableRow) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param level
	 */
	public ArrayList<TransitionsTableRow> SelectAllWithLevel(int level) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param level
	 * @param marking
	 */
	public ArrayList<TransitionsTableRow> SelectAllWithLevelPrevMarking(int level, Marking marking) {
		throw new UnsupportedOperationException();
	}
	
	/**
     * 
     */
    public TransitionsTableRow SelectRoot() {
        throw new UnsupportedOperationException();
    }

}