package data.modeling;

import java.util.ArrayList;

import data.Data;
import data.Marking;
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
     * ArrayList of conditions, which are true if transition has enough tokens
     * for triggering and false if not.
     */
    private ArrayList<Boolean> statuses;

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
        generateStatuses();
        updateTransitionsStatus();
    }

    /**
     * Filters transitions from data-object.
     * 
     * @return ArrayList of transitions.
     */
    protected ArrayList<Transition> generateTransitions() {
        ArrayList<Transition> list = new ArrayList<Transition>();
        for (int i = 0; i < data.getElements().size(); i++) {
            Element el = data.getElements().get(i);
            if (el.getType() == "T") {
                list.add((Transition) el);
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

    protected void generateStatuses() {
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
        this.data = data;
    }

    public void updateTransitionsStatus() {
        for (int i = 0; i < transitions.size(); i++) {
            statuses.set(i, false);
        }

        ArrayList<TransitionsTableRow> tableRows;
        tableRows = transTable.SelectAllWithLevelPrevMarking(curLevel,
                curMarking);

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
                return i;
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
            double scale = ranges.get(ranges.size() - 1);
            rand *= scale;

            // Security:
            if (rand > ranges.get(ranges.size() - 1)) {
                throw new RuntimeException();
            }

            RaffleStatus(collisionSet, ranges, rand);

        }
    }

    protected ArrayList<Double> generateVectorOfRanges(
            ArrayList<Integer> collisionSet) {
        ArrayList<Double> ranges;
        ranges = new ArrayList<Double>(collisionSet.size());

        for (int j = 0; j < collisionSet.size(); j++) {
            // data.getTransitionWithNo(collSet.get(j))
            int no = collisionSet.get(j);
            double range = ranges.get(j - 1);
            if (statuses.get(no)) {
                range += data.<Transition>getElementWithNo(no).getR();
            }
            ranges.add(range);
        }

        return ranges;
    }

    protected void RaffleStatus(ArrayList<Integer> collisionSet,
            ArrayList<Double> ranges, double rand) {
        // Step3: selecting able one.
        int index;
        for (index = 0; index < ranges.size(); index++) {
            if (rand > ranges.get(index)) {
                break;
            }
        }

        // Step4: Setting able one.
        for (int j = 0; j < collisionSet.size(); j++) {
            statuses.set(collisionSet.get(j), false);
        }
        statuses.set(index, true);
    }
    
    public void updateActiveTransition() {
        // Step1: Generating list of possible transitions.
        ArrayList<Integer> possibles = new ArrayList<Integer>();
        for (int i = 0; i < statuses.size(); i++) {
            if (statuses.get(i)) {
                possibles.add(i);
            }
        }
        
        // Step2: Rand and scaling
        double rand = generatorsPool.getLCG().generateValue();
        double scale = possibles.size();
        rand *= scale;
        int position = (int)rand;
        
        // Step3: Reset time for active.
        updateTimeForTransition(position);        
    }

    public void updateTransitionsTime() {
        double shift = times.get(1) - times.get(0);
        
        for (int i = 0; i < times.size(); i++) {
            times.set(i, times.get(i) - shift);
        }
    }
    
    // TODO: what??? O_o
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
    public void updateTimeForTransition(int position) {
        Transition transition = transitions.get(position);
        Double g = transition.getG();
        Generator generator = generatorsPool.chooseGenerator(g);
        times.set(position, generator.generateValue());        
    }

    public void update() {
        updateTransitionsStatus();
        updateTransitionsTime();
        updateActiveTransition();
    }

    public void nextStep() {
        throw new UnsupportedOperationException();
    }

    public void prevStep() {
        throw new UnsupportedOperationException();
    }

}