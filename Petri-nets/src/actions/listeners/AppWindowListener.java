/**
 * 
 */
package actions.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import actions.menugeneral.ExitingAction;
import data.Data;

/**
 * This class listens the window closing.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class AppWindowListener extends WindowAdapter {
    private Data data;

    private JFrame mainFrame;

    /**
     * @param data
     * @param mainFrame
     */
    public AppWindowListener(Data data, JFrame mainFrame) {
        super();
        this.data = data;
        this.mainFrame = mainFrame;
    }

    public void windowClosing(final WindowEvent we) {
        ExitingAction.exit(data, mainFrame);
    }

}
