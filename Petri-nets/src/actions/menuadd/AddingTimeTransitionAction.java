package actions.menuadd;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import data.Data;
import data.elements.Transition;

/**
 * Contains features of the transition of petri-net, Provides methods for
 * setting and getting this features.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class AddingTimeTransitionAction extends AbstractAction {

    /**
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = 7058590731835321668L;

    private Data data;

    /**
     * @param data
     */
    public AddingTimeTransitionAction(Data data) {
        super();
        this.data = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Transition newTran = new Transition(1.0, 0.0, 0.0, null, -1, -1, -1);
        data.setAddingModeElement(newTran);
    }

}
