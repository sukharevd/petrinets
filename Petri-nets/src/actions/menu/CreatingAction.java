package actions.menu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import data.Data;
import data.Place;
import data.Transition;

/**
 * Action, which is occurred when user clicks "New" component, it saves current
 * chart (if user wants it), and creates new empty chart, which has only one new
 * empty row in the table.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class CreatingAction extends AbstractAction {

    /**
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = 8032711014811260318L;

    private Data data;

    private JFrame mainFrame;

    /**
     * @param data
     */
    public CreatingAction(Data data, final JFrame parentFrame) {
        super();
        this.data = data;
        this.mainFrame = parentFrame;
    }

    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(ActionEvent arg0) {

        if (data.isChanged() == true) {

            int opt = JOptionPane.showConfirmDialog(mainFrame,
                    "Do you want to save changes?", "Create",
                    JOptionPane.YES_NO_CANCEL_OPTION);

            if (opt == JOptionPane.OK_OPTION) {
                SavingAction.save(data, mainFrame);
                if (data.isChanged() == true) {
                    return;
                }
            }
            if (opt == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }

        Place.setCurIndex(0);
        Transition.setCurIndex(0);

        data.getElements().clear();
        data.setChanged(false);

        data.setActiveElement(null);
        data.setAddingModeElement(null);
        // Main.getAppFrame().getElementDrawer().repaint();
        mainFrame.repaint();
    }

}
