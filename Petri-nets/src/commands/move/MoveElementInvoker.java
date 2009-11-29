/**
 * This package contains Invokers, Commands and Stack of Commands
 * for Undo/Redo operations.
 */
package commands.move;

import java.util.ArrayList;

import data.elements.Arc;
import data.elements.Element;

/**
 * Provides executing of Undo/Redo operations for moving of Element.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class MoveElementInvoker {

    private int xNew;

    private int yNew;

    private int xPrevious;

    private int yPrevious;

    private Element selElement;

    /**
     * @param x
     * @param y
     * @param addedElement
     */
    public MoveElementInvoker(int xNew, int yNew, Element selElement) {
        this.xNew = xNew;
        this.yNew = yNew;
        this.xPrevious = selElement.getX();
        this.yPrevious = selElement.getY();
        // TODO: what about if(selEl == null)?
        this.selElement = selElement;
    }

    protected void moveItsInputArcs(final int x, final int y) {
        ArrayList<Arc> inputArcs = selElement.getInputArcs();

        for (int i = 0; i < inputArcs.size(); i++) {
            int inputSize = inputArcs.get(i).getXsequence().size();

            inputArcs.get(i).getXsequence().set(inputSize - 1, x);
            inputArcs.get(i).getYsequence().set(inputSize - 1, y);
        }
    }

    protected void moveItsOutputArcs(final int x, final int y) {
        ArrayList<Arc> outputArcs = selElement.getOutputArcs();

        for (int i = 0; i < outputArcs.size(); i++) {
            outputArcs.get(i).getXsequence().set(0, x);
            outputArcs.get(i).getYsequence().set(0, y);
        }
    }

    protected void moveElementTo(final int x, final int y) {
        selElement.setPosition(x, y);

        moveItsInputArcs(x, y);
        moveItsOutputArcs(x, y);

    }

    public void moveElement() {
        moveElementTo(xNew, yNew);
    }

    public void undoMoveElement() {
        moveElementTo(xPrevious, yPrevious);
    }
}
