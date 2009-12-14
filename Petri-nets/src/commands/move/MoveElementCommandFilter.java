/**
 * This package contains Invokers, Commands and Stack of Commands
 * for Undo/Redo operations.
 */
package commands.move;

import view.ElementFinder;

import commands.Command;
import commands.CommandFilter;

import data.Data;
import data.elements.Arc;
import data.elements.Element;

/**
 * Filters element adding command if it is wrong.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class MoveElementCommandFilter implements CommandFilter {

    private Data data;

    private int mouseX;

    private int mouseY;

    /**
     * @param data
     * @param mouseX
     * @param mouseY
     */
    public MoveElementCommandFilter(Data data, int mouseX, int mouseY) {
        this.data = data;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    public Command filtrate(Command command) {
        Element addedElement = data.getAddingModeElement();

        if (addedElement == null) {
            Element selElement = data.getActiveElement();

            Element atRelease = ElementFinder.findElement(mouseX, mouseY, data);

            if ((selElement != null) && (!(selElement instanceof Arc))
                    && (mouseX > 0)
                    && (mouseY > 0)
                    && ((atRelease == null) || (atRelease == selElement))
                    // TODO: magic number (1)
                    && ((Math.abs(selElement.getX() - mouseX) > 1) || (Math
                            .abs(selElement.getY() - mouseY)) > 1)) {
                return command;
            }
        }

        return null;

    }
}
