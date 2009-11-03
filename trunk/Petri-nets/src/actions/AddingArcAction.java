package actions;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import data.Arc;
import data.Data;
import data.Element;
import data.Place;
import data.Transition;

/**
 * Action, which is occurred when user clicks "Add Arc" component, it adds new
 * arc to the graph.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class AddingArcAction extends AbstractAction {

    /**
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = 1311208811561183876L;

    private Data data;

    public AddingArcAction(final Data data) {
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

        data.setAddingModeElement(new Arc(new ArrayList<Integer>(),
                new ArrayList<Integer>(), -1, null));

        // String imageName = "arc24";
        //        
        // String imgLocationPng = "res/" + imageName + ".png";
        // URL imageURL = AppFrame.class.getResource(imgLocationPng);
        //
        // if (imageURL != null) { // image found
        // button.setIcon(new ImageIcon(imageURL, "Add New Arc"));
        // }

    }

}
