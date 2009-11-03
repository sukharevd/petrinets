/**
 * This package contains Invokers, Commands and Stack of Commands
 * for Undo/Redo operations.
 */
package commands.change;

import commands.Command;

import data.Transition;

/**
 * Class provides Undo/Redo operations for changing of Transition.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class ChangeTransitionValuesCommand implements Command {

    private ChangeTransitionValuesInvoker invoker;

    public ChangeTransitionValuesCommand(Transition t, double lyambda, double g) {
        this.invoker = new ChangeTransitionValuesInvoker(t, lyambda, g);
    }

    /*
     * (non-Javadoc)
     * 
     * @see commands.Command#execute()
     */
    @Override
    public void execute() {
        invoker.changeTransitionValues();

    }

    /*
     * (non-Javadoc)
     * 
     * @see commands.Command#undo()
     */
    @Override
    public void undo() {
        invoker.undoChangeTransitionValues();
    }

}
