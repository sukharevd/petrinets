/**
 * 
 */
package actions.listeners;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import commands.Command;
import commands.CommandFilter;
import commands.add.AddElementCommand;
import commands.add.AddElementCommandFilter;
import commands.change.ChangePlaceValuesCommand;
import commands.change.ChangeTransitionValuesCommand;
import commands.move.MoveElementCommand;
import commands.move.MoveElementCommandFilter;

import data.Arc;
import data.Data;
import data.Element;
import data.Place;
import data.Transition;

import view.ElementDrawer;
import view.ElementFinder;
import view.FrameSettings;

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

    /**
     * @param data
     */
    public DrawerMouseListener(final Data data, JFrame mainFrame,
            ElementDrawer elementDrawer) {
        super();
        this.data = data;
        this.mainFrame = mainFrame;
        this.elementDrawer = elementDrawer;
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

    protected void changeValuesOfElementAt(final int x, final int y) {
        Element element = ElementFinder.findElement(x, y, data);

        if ((element != null) && (element.getType() != "A")) {
            
            // TODO: refactor with arrays:
            String title;
            String message;
            String initialSelectionValue;
            String resStr;
            if (element.getType() == "P") {
                int res;
                while (true) {
                    try {
                        title = "Changing place values";
                        message = "Number of token:";
                        initialSelectionValue = ((Integer) ((Place) element)
                                .getNumTokens()).toString();
                        resStr = (String) JOptionPane.showInputDialog(
                                mainFrame, message, title,
                                JOptionPane.PLAIN_MESSAGE, null, null,
                                initialSelectionValue);
                        if (resStr == null) {
                            break;
                        }
                        res = Integer.valueOf(resStr);
                        ChangePlaceValuesCommand command = new ChangePlaceValuesCommand(
                                (Place) element, res);
                        data.getCommandStack().add(command);
                        command.execute();
                        break;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(mainFrame,
                                "Wrong number. Repeat please.",
                                "Error of inputting",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else {
                if (element.getType() == "T") {
                    double lyambda;
                    double g;
                    while (true) {
                        try {
                            title = "Changing transition values";
                            message = "Lyambda:";
                            initialSelectionValue = ((Double) ((Transition) element)
                                    .getLyambda()).toString();
                            resStr = (String) JOptionPane.showInputDialog(
                                    mainFrame, message, title,
                                    JOptionPane.PLAIN_MESSAGE, null, null,
                                    initialSelectionValue);
                            if (resStr == null) {
                                break;
                            }
                            lyambda = Double.valueOf(resStr);

                            message = "g:";
                            initialSelectionValue = ((Double) ((Transition) element)
                                    .getG()).toString();
                            resStr = (String) JOptionPane.showInputDialog(
                                    mainFrame, message, title,
                                    JOptionPane.PLAIN_MESSAGE, null, null,
                                    initialSelectionValue);
                            if (resStr == null) {
                                break;
                            }
                            g = Double.valueOf(resStr);

                            ChangeTransitionValuesCommand command = new ChangeTransitionValuesCommand(
                                    (Transition) element, lyambda, g);
                            data.getCommandStack().add(command);
                            command.execute();
                            break;
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(mainFrame,
                                    "Wrong number. Repeat please.",
                                    "Error of inputting",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }

                }
            }
            mainFrame.repaint();

        }
    }

    protected void activateNewElementAddingTo(Element addedElement, int x, int y) {
        if (addedElement == null) {
            return;
        }

        Command addCommand;
        addCommand = new AddElementCommand(x, y, addedElement, data);

        CommandFilter filter = new AddElementCommandFilter(data, x, y);
        Command filtratedCommand = filter.filtrate(addCommand);

        if (filtratedCommand != null) {
            addCommand.execute();
            data.getCommandStack().add(addCommand);

            changeMaxDrawingAreaSize();
            mainFrame.repaint();
        }
    }

    protected void selectElementAt(final int x, final int y) {
        Element selElement = ElementFinder.findElement(x, y, data);

        if (selElement != null) {
            data.setActiveElement(selElement);
        } else {
            data.setActiveElement(null);
        }
        mainFrame.repaint();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {

        Element addedElement = data.getAddingModeElement();

        if (e.getButton() == MouseEvent.BUTTON2) {
            // Undo
            data.getCommandStack().undoCur();
            mainFrame.repaint();
        } else {
            if (addedElement != null) {
                // Add new element:
                activateNewElementAddingTo(addedElement, e.getX(), e.getY());
            } else {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // Select Element:
                    selectElementAt(e.getX(), e.getY());
                } else {
                    // Change values of Element:
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        changeValuesOfElementAt(e.getX(), e.getY());
                    }
                }
            }
        }
        elementDrawer.requestFocusInWindow();
    }

    protected void activateElementMovingTo(final Element selElement,
            final int x, final int y) {

        if (selElement == null) {
            return;
        }

        Command command;
        command = new MoveElementCommand(x, y, selElement);

        CommandFilter filter;
        filter = new MoveElementCommandFilter(data, x, y);

        if (filter.filtrate(command) != null) {
            command.execute();
            data.getCommandStack().add(command);

            changeMaxDrawingAreaSize();
            data.setChanged(true);
            mainFrame.repaint();
        }
    }

    protected void changeMaxDrawingAreaSize() {
        int maxX = 0;
        int maxY = 0;

        ArrayList<Element> elements = data.getElements();

        for (int i = 0; i < elements.size(); i++) {
            if (maxX < elements.get(i).getX()) {
                maxX = elements.get(i).getX();
            }
            if (maxY < elements.get(i).getY()) {
                maxY = elements.get(i).getY();
            }

            ArrayList<Arc> arcs = elements.get(i).getOutputArcs();
            for (int j = 0; j < arcs.size(); j++) {
                for (int k = 0; k < arcs.get(j).getXsequence().size(); k++) {

                    if (maxX < arcs.get(j).getXsequence().get(k)) {
                        maxX = arcs.get(j).getXsequence().get(k);
                    }
                    if (maxY < arcs.get(j).getYsequence().get(k)) {
                        maxY = arcs.get(j).getYsequence().get(k);
                    }
                }
            }

        }

        maxX += FrameSettings.elementHeight();
        maxY += FrameSettings.elementHeight();

        elementDrawer.setPreferredSize(new Dimension(maxX, maxY));
        elementDrawer.revalidate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        Element selElement = data.getActiveElement();

        activateElementMovingTo(selElement, e.getX(), e.getY());

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
