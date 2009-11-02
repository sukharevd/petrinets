package actions.menu;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import data.Data;


/**
 * Action, which is occurred when user clicks "Exit" component,
 * it saves current chart if user wants and terminates the application.
 *
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 *
 */
public class ExitingAction extends AbstractAction {

    /**
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = -3159018596429482640L;
    
    private Data data;
    private JFrame mainFrame;
    
    
    /**
     * @param data
     * @param mainFrame
     */
    public ExitingAction(Data data, JFrame mainFrame) {
        super();
        this.data = data;
        this.mainFrame = mainFrame;
    }

    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(final ActionEvent arg0) {
        exit(data, mainFrame);
    }

    /**
     * Saves current chart if user wants and terminates the application.
     */
    public static void exit(Data data, JFrame mainFrame) {
        if (data.isChanged() == false) {
            System.exit(0);
        }

        int opt = JOptionPane.showConfirmDialog(mainFrame,
                "Do you want to save changes?", "Exit",
                JOptionPane.YES_NO_CANCEL_OPTION);

        if (opt == JOptionPane.OK_OPTION) {
            SavingAction.save(data, mainFrame);
            if (data.isChanged() == false) {
                System.exit(0);
            }

        }
        if (opt == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }


}
