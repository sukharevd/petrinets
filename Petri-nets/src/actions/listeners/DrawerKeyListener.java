/**
 * 
 */
package actions.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import data.Data;

/**
 * This class listens the keys press, if key is DEL it deletes active element.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public final class DrawerKeyListener implements KeyListener {

    private ListenersInvoker invoker;

    /**
     * @param data
     */
    public DrawerKeyListener(final Data data, final JFrame mainFrame) {
        super();
        invoker = new ListenersInvoker(data, mainFrame, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_DELETE) {
            invoker.activateSelectElementDeleting();
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
