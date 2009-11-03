/**
 * This package contains Invokers, Commands and Stack of Commands
 * for Undo/Redo operations.
 */
package commands;

import view.ElementFinder;
import data.Arc;
import data.Data;
import data.Element;
import data.Place;
import data.Transition;

/**
 * Provides executing of Undo/Redo operations for adding of Element.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class AddElementInvoker {

    private int x;

    private int y;

    private Element addedElement;

    private Data data;

    /**
     * @param x
     * @param y
     * @param addedElement
     */
    public AddElementInvoker(int x, int y, Element addedElement, Data data) {
        this.x = x;
        this.y = y;
        this.addedElement = addedElement;
        this.data = data;
    }

    protected void addPlaceOrTransition() {
        addedElement.setX(x);
        addedElement.setY(y);
        int no = (addedElement.getType() == "P") ? Place.getCurIndex()
                : Transition.getCurIndex();

        addedElement.setNo(no);
        data.add(addedElement);

        data.setAddingModeElement(null);
        data.setChanged(true);
    }

    protected void deletePlaceOrTransition() {
        data.remove(addedElement);

        data.setAddingModeElement(null);
        data.setChanged(true);
    }

    protected void addFinishArcPoint(final Element toElement) {
        Arc addedArc = (Arc) addedElement;

        data.setAddingModeElement(null);

        int x1 = addedArc.getXsequence().get(0);
        int y1 = addedArc.getYsequence().get(0);
        String s1 = ElementFinder.findElement(x1, y1, data).getType(); // for
                                                                       // testing
        String s2 = toElement.getType(); //

        if (s1 != s2) { //
            addedArc.setTo(toElement.getNo());
            addedArc.setToType(toElement.getType());
            addedArc.AddConnectedPoint(toElement.getX(), toElement.getY());
            Element fromElem = ElementFinder.findElement(x1, y1, data);
            fromElem.getOutputArcs().add(addedArc);
            toElement.getInputArcs().add(addedArc);
            data.setChanged(true);
        } else { //
            throw new RuntimeException(); //
        } //
        // else {
        // JOptionPane.showMessageDialog(null,
        // "You cann't link equal elements.", "Error of drawing",
        // JOptionPane.WARNING_MESSAGE);
        // }
    }

    protected void deleteFinishArcPoint(final Element toElement) {
        Arc addedArc = (Arc) addedElement;

        data.setAddingModeElement(addedElement);

        int x1 = addedArc.getXsequence().get(0);
        int y1 = addedArc.getYsequence().get(0);
        String s1 = ElementFinder.findElement(x1, y1, data).getType();
        String s2 = toElement.getType();// ////////

        if (s1 != s2) {
            addedArc.setTo(-1);
            addedArc.setToType(null);
            addedArc.getXsequence().remove(addedArc.getXsequence().size() - 1);
            addedArc.getYsequence().remove(addedArc.getYsequence().size() - 1);

            Element fromElem = ElementFinder.findElement(x1, y1, data);
            fromElem.getOutputArcs().remove(addedArc);
            toElement.getInputArcs().remove(addedArc);
            data.setChanged(true);
        } else {
            throw new RuntimeException();
        }
    }

    protected void deleteLastArcPoint() {
        Arc addedArc = (Arc) addedElement;

        addedArc.getXsequence().remove(addedArc.getXsequence().size() - 1);
        addedArc.getYsequence().remove(addedArc.getYsequence().size() - 1);
    }

    protected void deleteFirstArcPoint() {
        Arc addedArc = (Arc) addedElement;

        addedArc.getXsequence().remove(addedArc.getXsequence().size() - 1);
        addedArc.getYsequence().remove(addedArc.getYsequence().size() - 1);
        data.setAddingModeElement(null);

    }

    public void addNewElement() {

        if ((addedElement.getType() == "P") || (addedElement.getType() == "T")) {

            addPlaceOrTransition();
        } else {
            if (addedElement.getType() == "A") {
                Arc addedArc = (Arc) addedElement;

                Element element = ElementFinder.findElement(x, y, data);

                if (addedArc.getXsequence().size() > 0) {
                    if ((element != null) && (element.getType() != "A")) {
                        // if the last point
                        addFinishArcPoint(element);
                    } else {
                        addedArc.AddConnectedPoint(x, y);
                    }
                } else {
                    if ((element != null) && (element.getType() != "A")) {
                        addedArc.AddConnectedPoint(element.getX(), element
                                .getY());
                        data.setAddingModeElement(addedElement);
                    }
                }
            }
        }
    }

    public void undoAddNewElement() {

        if ((addedElement.getType() == "P") || (addedElement.getType() == "T")) {

            deletePlaceOrTransition();
        } else {
            if (addedElement.getType() == "A") {

                Arc addedArc = (Arc) addedElement;
                Element element = ElementFinder.findElement(x, y, data);

                if (addedArc.getXsequence().size() > 1) {
                    if ((element != null) && (element.getType() != "A")) {
                        // if the last point
                        deleteFinishArcPoint(element);
                    } else {
                        deleteLastArcPoint();
                    }
                } else {
                    if ((element != null) && (element.getType() != "A")
                            && (addedArc.getXsequence().size() > 0)) {
                        deleteFirstArcPoint();
                    }
                }
            }
        }
    }
}
