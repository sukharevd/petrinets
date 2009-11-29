/**
 * This package contains Invokers, Commands and Stack of Commands
 * for Undo/Redo operations.
 */
package commands.delete;

import commands.Command;

import data.Data;
import data.elements.Element;

/**
 * Class provides Undo/Redo operations for adding new Element.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class DeleteElementCommand implements Command {

    private DeleteElementInvoker invoker;

    /**
     * @param x
     * @param y
     * @param addedElement
     */
    public DeleteElementCommand(final Element deletedElement, final Data data) {
        this.invoker = new DeleteElementInvoker(deletedElement, data);
    }

    /*
     * (non-Javadoc)
     * 
     * @see commands.Command#execute()
     */
    @Override
    public void execute() {
        invoker.deleteElement();

    }

    /*
     * (non-Javadoc)
     * 
     * @see commands.Command#undo()
     */
    @Override
    public void undo() {
        invoker.undoDeleteElement();
    }

}
