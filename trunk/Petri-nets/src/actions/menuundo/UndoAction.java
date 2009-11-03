/**
 *
 */
package actions.menuundo;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import data.Data;

/**
 * Action, which is occurred when user clicks "Undo" component, it undo one
 * operation of CommandStack.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public final class UndoAction extends AbstractAction {

    /**
     *
     */
    private static final long serialVersionUID = 4469897476815578023L;

    private JFrame mainFrame;

    private Data data;

    /**
     * @param mainFrame
     */
    public UndoAction(Data data, JFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
        this.data = data;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (data.getCommandStack().getCurIndex() > -1) {
            data.getCommandStack().undoCur();
            mainFrame.repaint();
        }

    }

}
