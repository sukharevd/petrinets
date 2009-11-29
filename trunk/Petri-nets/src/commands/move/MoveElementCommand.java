/**
 * This package contains Invokers, Commands and Stack of Commands
 * for Undo/Redo operations.
 */
package commands.move;

import commands.Command;

import data.elements.Element;

/**
 * Class provides Undo/Redo operations for adding new Element.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class MoveElementCommand implements Command {

    private MoveElementInvoker invoker;

    /**
     * @param x
     * @param y
     * @param addedElement
     */
    public MoveElementCommand(int x, int y, Element selElement) {
        this.invoker = new MoveElementInvoker(x, y, selElement);
    }

    /*
     * (non-Javadoc)
     * 
     * @see commands.Command#execute()
     */
    @Override
    public void execute() {
        invoker.moveElement();
    }

    /*
     * (non-Javadoc)
     * 
     * @see commands.Command#undo()
     */
    @Override
    public void undo() {
        invoker.undoMoveElement();
    }

}
