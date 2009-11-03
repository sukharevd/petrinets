/**
 * This package contains Invokers, Commands and Stack of Commands
 * for Undo/Redo operations.
 */
package commands;

import data.Data;
import data.Element;

/**
 * Class provides Undo/Redo operations for adding new Element.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class AddElementCommand implements Command {

    private AddElementInvoker invoker;

    /**
     * @param x
     * @param y
     * @param addedElement
     */
    public AddElementCommand(int x, int y, Element addedElement, Data data) {
        this.invoker = new AddElementInvoker(x, y, addedElement, data);
    }

    /*
     * (non-Javadoc)
     * 
     * @see commands.Command#execute()
     */
    @Override
    public void execute() {
        invoker.addNewElement();
    }

    /*
     * (non-Javadoc)
     * 
     * @see commands.Command#undo()
     */
    @Override
    public void undo() {
        invoker.undoAddNewElement();
    }

}
