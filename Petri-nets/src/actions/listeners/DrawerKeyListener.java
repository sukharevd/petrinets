/**
 * 
 */
package actions.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import commands.delete.DeleteElementCommand;

import data.Data;
import data.Element;

/**
 * This class listens the keys press, if key is DEL it deletes active element.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public final class DrawerKeyListener implements KeyListener {

    private Data data;

    private JFrame mainFrame;

    /**
     * @param data
     */
    public DrawerKeyListener(final Data data, final JFrame mainFrame) {
        super();
        this.data = data;
        this.mainFrame = mainFrame;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println("delete");
        if (e.getKeyCode() == KeyEvent.VK_DELETE) {

            Element activeElement = data.getActiveElement();

            if (activeElement != null) {

                DeleteElementCommand command = new DeleteElementCommand(
                        activeElement, data);
                data.getCommandStack().add(command);
                command.execute();
                mainFrame.repaint();
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

}
