package data.modeling;

import java.util.ArrayList;

import data.Marking;
import data.elements.Transition;

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
//	 */
//	public Marking SelectRootMarking() {
//	    Marking marking = null;
//        
//        for (int i = 0; i < rows.size(); i++) {
//            TransitionsTableRow row = rows.get(i);
//            if (row.getMarkType() == MarkType.ROOT) {
//                marking = row.getPrevMarking();
//            }
//        }
//        
//        return marking;
//	}

	/**
	 * 
	 * @param marking
	 */
	public ArrayList<TransitionsTableRow> selectAllWithPrevMarking(Marking marking) {
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
    public ArrayList<TransitionsTableRow> selectAllWithTransPrevMarking(Transition transition, Marking marking) {
        ArrayList<TransitionsTableRow> list = new ArrayList<TransitionsTableRow>();
        
        for (int i = 0; i < rows.size(); i++) {
            TransitionsTableRow row = rows.get(i);
            if ((row.getLevel() > 0) && (row.getPrevMarking().equals(marking))) {
                ArrayList<Transition> trans = rows.get(i).getWorkedTransitions();
                for (int j = 0; j < trans.size(); j++) {
                    if (trans.get(j).getNo() == transition.getNo()) {
                        list.add(row);
                    }
                }
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