/**
 * This package contains Invokers, Commands and Stack of Commands
 * for Undo/Redo operations.
 */
package commands.delete;

import data.Data;
import data.elements.Arc;
import data.elements.Element;

/**
 * Provides executing of Undo/Redo operations for deleting of Elements.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class DeleteElementInvoker {

    private Element deletedElement;

    private Data data;

    public DeleteElementInvoker(final Element deletedElement, final Data data) {
        this.deletedElement = deletedElement;
        this.data = data;
    }

    protected void deleteOneArc() {
        for (int i = 0; i < data.getElements().size(); i++) {
            data.getElements().get(i).getInputArcs().remove(deletedElement);
            data.getElements().get(i).getOutputArcs().remove(deletedElement);
        }
    }

    protected void recoverOneArc() {
        Arc delArc = (Arc) deletedElement;
        Element el;
        for (int i = 0; i < data.getElements().size(); i++) {
            el = data.getElements().get(i);

            // Recovering output arcs:
            if ((el.getX() == delArc.getXsequence().get(0))
                    && (el.getY() == delArc.getYsequence().get(0))) {
                el.getOutputArcs().add(delArc);
            }

            // Recovering input arcs:
            int seqLength = delArc.getXsequence().size();
            if ((el.getX() == delArc.getXsequence().get(seqLength - 1))
                    && (el.getY() == delArc.getYsequence().get(seqLength - 1))) {
                el.getInputArcs().add(delArc);
            }
        }
    }

    protected void deleteElementWithArcs() {
        for (int i = 0; i < deletedElement.getInputArcs().size(); i++) {
            for (int j = 0; j < data.getElements().size(); j++) {
                data.getElements().get(j).getOutputArcs().remove(
                        deletedElement.getInputArcs().get(i));
            }
        }
        data.getElements().remove(deletedElement);
    }

    protected void recoverElementWithArcs() {
        data.getElements().add(deletedElement);

        Element el;
        Arc arc;
        for (int i = 0; i < deletedElement.getInputArcs().size(); i++) {
            for (int j = 0; j < data.getElements().size(); j++) {
                arc = deletedElement.getInputArcs().get(i);
                el = data.getElements().get(j);

                if ((el.getX() == arc.getXsequence().get(0))
                        && (el.getY() == arc.getYsequence().get(0))) {
                    el.getOutputArcs().add(arc);
                }
            }
        }
    }

    public void deleteElement() {
        if (deletedElement instanceof Arc) {
            deleteOneArc();
        } else {
            deleteElementWithArcs();
        }
        data.setActiveElement(null);
    }

    public void undoDeleteElement() {
        data.setActiveElement(deletedElement);
        if (deletedElement instanceof Arc) {
            recoverOneArc();
        } else {
            recoverElementWithArcs();
        }
    }

}
