package actions.menuadd;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import data.Data;
import data.elements.Place;

/**
 * Contains features of the transition of petri-net, Provides methods for
 * setting and getting this features.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class AddingPlaceAction extends AbstractAction {

    /**
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = -3838500188334807889L;

    private Data data;

    /**
     * @param data
     */
    public AddingPlaceAction(Data data) {
        super();
        this.data = data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        data.setAddingModeElement(new Place(0, -1, -1, -1));
    }

}
