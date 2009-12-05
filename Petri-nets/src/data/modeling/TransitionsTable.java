package data.modeling;

import java.util.ArrayList;

import data.Marking;

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

//	/**
//	 * 
//	 * @param level
//	 */
//	public ArrayList<TransitionsTableRow> SelectAllWithLevel(int level) {
//		throw new UnsupportedOperationException();
//	}

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
        TransitionsTableRow root = null;
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i).getMarkType() == MarkType.ROOT) {
                root = rows.get(i);
            }
        }
        
        return root;
    }

}