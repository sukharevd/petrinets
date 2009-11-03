/**
 * 
 */
package actions.menuundo;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import data.Data;

/**
 * Action, which is occurred when user clicks "Redo" component, it re-do one
 * operation of CommandStack.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public final class RedoAction extends AbstractAction {

    /**
     * 
     */
    private static final long serialVersionUID = -1308047552187361675L;

    private JFrame mainFrame;

    private Data data;

    /**
     * @param mainFrame
     */
    public RedoAction(Data data, JFrame mainFrame) {
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
        if (data.getCommandStack().getCurIndex() < data.getCommandStack()
                .getCommandList().size()) {
            data.getCommandStack().redoCur();
            mainFrame.repaint();
        }

    }

}
