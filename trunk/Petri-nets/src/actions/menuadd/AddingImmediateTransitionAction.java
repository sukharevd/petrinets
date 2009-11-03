package actions.menuadd;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import data.Data;
import data.Element;
import data.Place;
import data.Transition;

/**
 * Action, which is occurred when user clicks "Add Immediate Transition"
 * component, it adds new Immediate Transition to the graph.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class AddingImmediateTransitionAction extends AbstractAction {

    /**
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = 7920607374315919224L;

    private Data data;

    /**
     * @param data
     */
    public AddingImmediateTransitionAction(Data data) {
        super();
        this.data = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Element addedEl = data.getAddingModeElement();
        if (addedEl != null) {
            if (addedEl.getType() == "P") {
                Place.setCurIndex(Place.getCurIndex() - 1);
            } else {
                if (addedEl.getType() == "T") {
                    Transition.setCurIndex(Transition.getCurIndex() - 1);
                }
            }
        }

        data.setAddingModeElement(new Transition(0.0, 0.0, null, -1, -1, -1));
    }

}
