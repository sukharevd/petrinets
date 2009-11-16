/**
 * This package contains Invokers, Commands and Stack of Commands
 * for Undo/Redo operations.
 */
package commands.add;

import javax.swing.JOptionPane;

import commands.Command;
import commands.CommandFilter;

import view.ElementFinder;
import data.Arc;
import data.Data;
import data.Element;

/**
 * Filters element adding command if it is wrong.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class AddElementCommandFilter implements CommandFilter {

    private Data data;

    private int mouseX;

    private int mouseY;

    /**
     * @param data
     * @param mouseX
     * @param mouseY
     */
    public AddElementCommandFilter(Data data, int mouseX, int mouseY) {
        this.data = data;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    protected boolean isFiltratedPlaceTransition() {
        Element selectedEl = ElementFinder.findElement(mouseX, mouseY, data);

        if ((selectedEl != null) && (selectedEl.getType() != "A")) {

            String message = "You cann't add element over other element.";
            String title = "Error of drawing";
            JOptionPane.showMessageDialog(null, message, title,
                    JOptionPane.WARNING_MESSAGE);
            return true;
        }

        return false;
    }
    
    protected boolean isFiltratedArc() {
        Arc addedArc = (Arc) data.getAddingModeElement();

        Element selectedEl = ElementFinder.findElement(mouseX,
                mouseY, data);

        if (addedArc.getXsequence().size() > 0) {
            if ((selectedEl != null)
                    && (selectedEl.getType() != "A")) {
                int x1 = addedArc.getXsequence().get(0);
                int y1 = addedArc.getYsequence().get(0);
                String s1 = ElementFinder.findElement(x1, y1, data)
                        .getType();
                String s2 = selectedEl.getType();

                if (s1 == s2) {
                    JOptionPane.showMessageDialog(null,
                            "You cann't link equal elements.",
                            "Error of drawing",
                            JOptionPane.WARNING_MESSAGE);
                    return true;
                }
            }
        } else {
            if ((selectedEl == null)
                    || (selectedEl.getType() == "A")) {
                return true;
            }
        }

        return false;
    }

    public Command filtrate(Command command) {
        Element addedElement = data.getAddingModeElement();
        if (addedElement != null) {

            if ((addedElement.getType() == "P")
                    || (addedElement.getType() == "T")) {
                if (isFiltratedPlaceTransition()) {
                    return null;
                }
            } else {
                if (addedElement.getType() == "A") {
                    if (isFiltratedArc()) {
                        return null;
                    }
                }
            }
        }
        return command;

    }
}
