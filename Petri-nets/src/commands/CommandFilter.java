/**
 * 
 */
package commands;

/**
 * Interface for Undo/Redo filters.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public interface CommandFilter {
    public Command filtrate(Command command);
}
