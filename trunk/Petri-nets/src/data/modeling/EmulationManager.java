package data.modeling;

import java.util.ArrayList;

import data.*;
import data.elements.Arc;
import data.elements.Element;
import data.elements.Transition;
import data.generators.Generator;
import data.generators.GeneratorsPool;

public class EmulationManager {

	/**
	 * ArrayList of times when Transitions will be able to trigger.
	 */
	private ArrayList<Double> times;
	/**
	 * ArrayList of conditions, which are true if transition has enough tokens for trigging and false if not.
	 */
	private ArrayList<Boolean> isAllow;
	private ArrayList<ArrayList<Integer>> collisions;
	private Marking curMarking;
	private int curLevel;
	private TransitionsTable transTable;
	private Data data;
	private ArrayList<Transition> transitions;
	private GeneratorsPool generatorsPool;

	/**
	 * 
	 * @param transTable
	 * @param data
	 */
	public EmulationManager(TransitionsTable transTable, Data data) {
	    this.transTable = transTable;
	    this.data = data;
	    this.curLevel = 1;
	        
	    this.generatorsPool = new GeneratorsPool();
	    this.transitions = generateTransitions();
	    this.collisions = generateCollisions();
	    generateTimes();	    
	}
	
	/**
	 * Filters transitions from data-object. 
	 * @return ArrayList of transitions.
	 */
	protected ArrayList<Transition> generateTransitions() {
	    ArrayList<Transition> list = new ArrayList<Transition>();
	    for (int i = 0; i < data.getElements().size(); i++) {
	        Element el = data.getElements().get(i);
	        if (el.getType() == "T") {
                list.add((Transition)el);
            }
        }
	    
	    return list;
	}
	
	protected void generateTimes() {
	    times = new ArrayList<Double>();
	    for (int i = 0; i < transitions.size(); i++) {
	        Transition transition = transitions.get(i); 
            Double g = transition.getG();
            Generator generator = generatorsPool.chooseGenerator(g);
            times.add(generator.generateValue());
        }
	}

    protected ArrayList<ArrayList<Integer>> generateCollisions() {
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        list.add(new ArrayList<Integer>());
        int curIndex = 0;

        for (int i = 0; i < data.getElements().size(); i++) {
            Element el = data.getElements().get(i);
            if (el.getType() == "P") {
                for (int j = 0; j < el.getOutputArcs().size(); j++) {
                    Arc arc = el.getOutputArcs().get(j);
                    if (!list.get(curIndex).contains(arc.getTo())) {
                        list.get(curIndex).add(arc.getTo());
                    }
                }
                if (list.get(curIndex).size() >= 2) {
                    curIndex++;
                    list.add(new ArrayList<Integer>());
                } else {
                    list.get(curIndex).clear();
                }
            }
        }
        
        return list;
    }
	/**
     * @return the transTable
     */
    public final TransitionsTable getTransTable() {
        return transTable;
    }

    /**
     * @param transTable the transTable to set
     */
    public final void setTransTable(TransitionsTable transTable) {
        this.transTable = transTable;
    }

    /**
     * @return the data
     */
    public final Data getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public final void setData(Data data) {
        this.data = data;
    }

	public void updateTransitionsStatus() {
		throw new UnsupportedOperationException();
	}

	public void updateTransitionsTime() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param transition
	 */
	public void updateStatusForTransition(Transition transition) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param transition
	 */
	public void updateTimeForTransition(Transition transition) {
		throw new UnsupportedOperationException();
	}

	public void update() {
		throw new UnsupportedOperationException();
	}

	public void nextStep() {
		throw new UnsupportedOperationException();
	}

	public void prevStep() {
		throw new UnsupportedOperationException();
	}

}