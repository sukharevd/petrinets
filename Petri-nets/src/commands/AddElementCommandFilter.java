/**
 * This package contains Invokers, Commands and Stack of Commands
 * for Undo/Redo operations.
 */
package commands;

import javax.swing.JOptionPane;

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
        super();
        this.data = data;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    // protected Command filtrate
    public Command filtrate(Command command) {
        Element addedElement = data.getAddingModeElement();
        if (addedElement != null) {
            if ((addedElement.getType() == "P")
                    || (addedElement.getType() == "T")) {
                Element selectedEl = ElementFinder.findElement(mouseX, mouseY,
                        data);
                if ((selectedEl != null) && (selectedEl.getType() != "A")) {
                    JOptionPane.showMessageDialog(null,
                            "You cann't add element over other element.",
                            "Error of drawing", JOptionPane.WARNING_MESSAGE);
                    return null;
                }
                // TODO: wrong place/transition adding filter;
            } else {
                if (addedElement.getType() == "A") {
                    Arc addedArc = (Arc) addedElement;

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
                                return null;
                            }
                        }
                    } else {
                        if ((selectedEl == null)
                                || (selectedEl.getType() == "A")) {
                            return null;
                        }
                    }
                }
            }
        }
        return command;

    }
}
