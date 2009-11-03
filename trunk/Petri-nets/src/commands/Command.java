/**
 * This package contains Invokers, Commands and Stack of Commands
 * for Undo/Redo operations.
 */
package commands;

/**
 * Interface for Undo/Redo operations.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public interface Command {
    public void execute();

    public void undo();
}
