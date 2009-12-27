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

package data.modeling;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import data.Data;
import data.Marking;
import data.TableManagment;
import data.elements.Arc;
import data.elements.Element;
import data.elements.Place;
import data.elements.Transition;
import data.generators.Generator;
import data.generators.GeneratorsPool;

/**
 * Main class of emulation, it manages and executes emulation process.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class EmulationManager {

    /**
     * ArrayList of times when Transitions will be able to trigger.
     */
    private ArrayList<Double> times;

    /**
     * ArrayList of conditions, which are true if transition has enough tokens
     * for triggering and false if not.
     */
    private ArrayList<Boolean> statuses;

    /**
     * {@link ArrayList} of {@link ArrayList}s, which contains the numbers of
     * {@link Transition}s, which are in collision.
     */
    private ArrayList<ArrayList<Integer>> collisions;

    /**
     * Current {@link Marking} of emulation process.
     */
    private Marking curMarking;

    /**
     * Reachability table of the current Petri net.
     */
    private TransitionsTable transTable;

    /**
     * {@link Data} object of current Petri net.
     */
    private Data data;

    /**
     * {@link ArrayList} of {@link Transition}s of current Petri net.
     */
    private ArrayList<Transition> transitions;

    /**
     * Emulation log of current Petri net.
     */
    private EmulationLog log;

    /**
     * Pull of different kinds of {@link Generator}s
     */
    private GeneratorsPool generatorsPool;

    /**
     * Status of deadlocked of the current step of emulation.
     */
    private boolean isInDeadlock;

    /**
     * Constructor of {@link EmulationManager}.
     */
    public EmulationManager() {
        this.data = new Data(new ArrayList<Element>());

        initializeAll();
    }

    /**
     * Constructor of {@link EmulationManager}.
     * 
     * @param data
     *            new data
     */
    public EmulationManager(Data data) {
        this.data = (Data) data.clone();

        initializeAll();
    }

    /**
     * Sets the initial values of the {@link EmulationManager} objects.
     */
    protected void initializeAll() {
        this.isInDeadlock = false;
        this.generatorsPool = new GeneratorsPool();
        this.log = new EmulationLog();
        this.transTable = generateTransTable();
        this.curMarking = null;
        if (transTable.count() > 1) {
            this.curMarking = transTable.selectRoot().getPrevMarking();
        }
        this.transitions = data.getTransitions();
        this.collisions = initializeCollisions();
        initializeTimes();
        initializeStatuses();
        updateTransitionsStatus();
    }

    /**
     * Generates the {@link TransitionsTable} of current Petri net.
     */
    protected TransitionsTable generateTransTable() {
        TableManagment myTable = new TableManagment(data);
        int[] typecrossing = new int[myTable.getAllT().size()];
        for (int i = 0; i < typecrossing.length; i++) {
            typecrossing[i] = 0;
            if (myTable.getAllT().get(i).getLyambda() == 0) {
                typecrossing[i] = 1;
            }
        }

        TreeofPetriNet mytree = new TreeofPetriNet(myTable.getAllP().size(),
                myTable.getAllT().size(), myTable.getMatrixDi(), myTable
                        .getMatrixDq(), myTable.getMarkirovka(), typecrossing,
                data);

        mytree.WriteResult(0);
        return mytree.getTransTable();
    }

    /**
     * Sets zero values for times field.
     */
    protected void initializeTimes() {
        times = new ArrayList<Double>();
        for (int i = 0; i < transitions.size(); i++) {
            times.add(0.0);
        }
    }

    /**
     * Initialize collisions array field for current Petri net.
     * 
     * @return collisions array field for current Petri net
     */
    protected ArrayList<ArrayList<Integer>> initializeCollisions() {
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        list.add(new ArrayList<Integer>());
        int curIndex = 0;

        for (int i = 0; i < data.getElements().size(); i++) {
            Element el = data.getElements().get(i);
            if (el instanceof Place) {
                for (int j = 0; j < el.getOutputArcs().size(); j++) {
                    Arc arc = el.getOutputArcs().get(j);

                    Transition tran = data.getTransitionWithNo(arc.getTo());
                    int pos = transitions.indexOf(tran);
                    if (!list.get(curIndex).contains(pos)) {
                        list.get(curIndex).add(pos);
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
        list.remove(list.size() - 1);

        return list;
    }

    /**
     * Initializes statuses for current Petri net, which are true if transition
     * has enough tokens for triggering and false if not.
     */
    protected void initializeStatuses() {
        statuses = new ArrayList<Boolean>();
        for (int i = 0; i < transitions.size(); i++) {
            statuses.add(i, false);
        }
    }

    /**
     * @return the transTable
     */
    public final TransitionsTable getTransTable() {
        return transTable;
    }

    /**
     * @param transTable
     *            the transTable to set
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
     * @param data
     *            the data to set
     */
    public final void setData(Data data) {
        Data clone = (Data) data.clone();

        ArrayList<Element> elements = clone.getElements();
        this.data.setElements(elements);

        initializeAll();
    }

    /**
     * @return the log
     */
    public final EmulationLog getLog() {
        return log;
    }

    /**
     * @param log
     *            the log to set
     */
    public final void setLog(EmulationLog log) {
        this.log = log;
    }

    /**
     * @return the isInDeadlock
     */
    public final boolean isInDeadlock() {
        return isInDeadlock;
    }

    protected void updateTransitionsStatus() {
        for (int i = 0; i < transitions.size(); i++) {
            statuses.set(i, false);
        }

        ArrayList<TransitionsTableRow> tableRows;
        tableRows = transTable.selectAllWithPrevMarking(curMarking);

        for (int i = 0; i < tableRows.size(); i++) {
            int no = findTimeTransition(tableRows.get(i).getWorkedTransitions());
            statuses.set(no, true);
        }
        updateCollisionDeadLock();
    }

    protected int findTimeTransition(ArrayList<Transition> workedTransitions) {
        for (int i = 0; i < workedTransitions.size(); i++) {
            Transition tran = workedTransitions.get(i);
            if (tran.getLyambda() != 0.0) {
                return transitions.indexOf(tran);
            }
        }
        return -1;
    }

    protected void updateCollisionDeadLock() {
        for (int i = 0; i < collisions.size(); i++) {
            ArrayList<Integer> collisionSet = collisions.get(i);

            // Step1: generate vector of ranges of probability
            ArrayList<Double> ranges = generateVectorOfRanges(collisionSet);

            // Step2: generating number and scaling.
            double rand = generatorsPool.getLCG().generateValue();
            if (ranges.size() > 0) {
                double scale = ranges.get(ranges.size() - 1);
                rand *= scale;

                if (scale != 0.0) {
                    raffleStatus(collisionSet, ranges, rand);
                }
            }
        }
    }

    protected ArrayList<Double> generateVectorOfRanges(
            ArrayList<Integer> collisionSet) {
        ArrayList<Double> ranges;
        ranges = new ArrayList<Double>(collisionSet.size());

        for (int j = 0; j < collisionSet.size(); j++) {
            // data.getTransitionWithNo(collSet.get(j))
            int no = collisionSet.get(j);
            double range;
            if (j == 0) {
                range = 0;
            } else {
                range = ranges.get(j - 1);
            }

            if (statuses.get(no)) {
                range += transitions.get(no).getR();
            }
            ranges.add(range);
        }

        return ranges;
    }

    protected void raffleStatus(ArrayList<Integer> collisionSet,
            ArrayList<Double> ranges, double rand) {
        // Step3: selecting able one.
        int index;
        for (index = 0; index < ranges.size(); index++) {
            if (rand < ranges.get(index)) {
                break;
            }
        }

        // Step4: Setting able one.
        for (int j = 0; j < collisionSet.size(); j++) {
            statuses.set(collisionSet.get(j), false);
        }
        statuses.set(collisionSet.get(index), true);
    }

    protected void updateActiveTransition() {
        // Step1: Generating list of possible transitions.
        ArrayList<Integer> possibles = getPossibles();
        ArrayList<Transition> possibleTrans = getPossiblesTrans(possibles);
        if (possibles.size() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Emulation is in deadlock, it was stoped.", "Deadlock",
                    JOptionPane.WARNING_MESSAGE);
            isInDeadlock = true;
            return;
        }
        // Step2: select active transition.
        Transition active = selectActiveTransition(possibles);
        int activePos = transitions.indexOf(active);

        Marking prevMarking = curMarking;
        curMarking = transTable.selectAllWithTransPrevMarking(active,
                curMarking).get(0).getNextMarking();
        data.changeDataMarking(curMarking);
        data.setActiveElement(active);

        // Step4: Reset time for active.
        double time = updateTransitionsTime(activePos, possibles);
        generateTimeForTransition(active);

        // Step5: Logging
        EmulationLogItem item;
        item = new EmulationLogItem(time, prevMarking, curMarking, active,
                possibleTrans);
        log.add(item);
    }

    protected ArrayList<Integer> getPossibles() {
        ArrayList<Integer> possibles = new ArrayList<Integer>();
        for (int i = 0; i < statuses.size(); i++) {
            if (statuses.get(i)) {
                possibles.add(i);
            }
        }
        return possibles;
    }

    protected ArrayList<Transition> getPossiblesTrans(
            ArrayList<Integer> possibles) {
        ArrayList<Transition> transitions = new ArrayList<Transition>();
        for (int i = 0; i < possibles.size(); i++) {
            Transition tran = this.transitions.get(possibles.get(i));
            transitions.add(tran);
        }
        return transitions;
    }

    protected Transition selectActiveTransition(ArrayList<Integer> possibles) {
        int min = possibles.get(0);
        for (int i = 1; i < possibles.size(); i++) {
            if (times.get(possibles.get(i)) < times.get(min)) {
                min = possibles.get(i);
            }
        }

        int active = min;
        return transitions.get(active);
    }

    protected double updateTransitionsTime(int activePos,
            ArrayList<Integer> possibles) {
        double shift = times.get(activePos);

        for (int i = 0; i < possibles.size(); i++) {
            times.set(possibles.get(i), times.get(possibles.get(i)) - shift);
        }

        return shift;
    }

    /**
     * 
     * @param transition
     */
    protected void generateTimeForTransition(Transition transition) {
        Double g = transition.getG();
        if (g == 0.0) {
            JOptionPane.showMessageDialog(null, transition.getTitle()
                    + " has g = 0, it will be set in g = 1.0", "Deadlock",
                    JOptionPane.WARNING_MESSAGE);
            g = 1.0;
        }
        Double lyambda = transition.getLyambda();
        int position = transitions.indexOf(transition);

        if (lyambda > 0.0) {
            Generator generator = generatorsPool.chooseGenerator(g, lyambda);
            times.set(position, generator.generateValue());
        } else {
            // TODO: generating time for imm.transition
            throw new RuntimeException();
        }
    }

    protected void updateTimes() {
        for (int i = 0; i < transitions.size(); i++) {
            if ((times.get(i) == 0.0) && (statuses.get(i) == true)) {
                Transition transition = transitions.get(i);
                generateTimeForTransition(transition);
            }

        }
    }

    public void nextStep() {
        if ((transTable != null) && (transTable.count() != 0)) {
            updateTransitionsStatus();
            updateTimes();
            updateActiveTransition();
        }
    }
    //
    // public void prevStep() {
    // throw new UnsupportedOperationException();
    // }

}