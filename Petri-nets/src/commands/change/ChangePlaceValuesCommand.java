/**
 * This package contains Invokers, Commands and Stack of Commands
 * for Undo/Redo operations.
 */
package commands.change;

import commands.Command;

import data.elements.Place;

/**
 * Class provides Undo/Redo operations for changing of Place.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class ChangePlaceValuesCommand implements Command {

    private ChangePlaceValuesInvoker invoker;

    /**
     * @param place
     * @param numToken
     */
    public ChangePlaceValuesCommand(Place place, int numToken) {
        this.invoker = new ChangePlaceValuesInvoker(place, numToken);
    }

    /*
     * (non-Javadoc)
     * 
     * @see commands.Command#execute()
     */
    @Override
    public void execute() {
        invoker.changePlaceValues();

    }

    /*
     * (non-Javadoc)
     * 
     * @see commands.Command#undo()
     */
    @Override
    public void undo() {
        invoker.undoChangePlaceValues();
    }

}
