/**
 * 
 */
package actions.listeners;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import data.Data;
import data.Element;

import view.ElementFinder;
import view.tabdrawer.ElementDrawer;

/**
 * This class listens the mouse click, it performs adding, deleting, replacing
 * Elements of Petri-net.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class DrawerMouseListener implements MouseListener, MouseMotionListener {

    private Data data;

    private JFrame mainFrame;

    private ElementDrawer elementDrawer;

    private ListenersInvoker invoker;

    /**
     * @param data
     */
    public DrawerMouseListener(final Data data, JFrame mainFrame,
            ElementDrawer elementDrawer) {
        super();
        this.data = data;
        this.mainFrame = mainFrame;
        this.elementDrawer = elementDrawer;
        this.invoker = new ListenersInvoker(data, mainFrame, elementDrawer);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON2) {
            // Undo
            data.getCommandStack().undoCur();
            mainFrame.repaint();
        } else {
            Element addedElement = data.getAddingModeElement();
            if (addedElement != null) {
                invoker.activateNewElementAddingTo(e.getX(), e.getY());
            } else {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    invoker.selectElementAt(e.getX(), e.getY());
                } else {
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        invoker.changeValuesOfElementAt(e.getX(), e.getY());
                    }
                }
            }
        }
        elementDrawer.requestFocusInWindow();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {

        invoker.activateElementMovingTo(e.getX(), e.getY());

        elementDrawer.requestFocusInWindow();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (ElementFinder.findElement(e.getX(), e.getY(), data) != null) {
            elementDrawer.setCursor(Cursor
                    .getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        } else {
            elementDrawer.setCursor(Cursor.getDefaultCursor());
        }
    }

}
