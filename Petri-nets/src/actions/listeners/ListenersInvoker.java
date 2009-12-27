/*
    Copyright (C)  2009  Sukharev Dmitriy, Dzyuban Yuriy, Voitova Anastasiia.
    
    This file is part of Petri nets Emulator.
    
    Petri nets Emulator is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    
    Petri nets Emulator is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    
    You should have received a copy of the GNU General Public License
    along with Petri nets Emulator. If not, see <http://www.gnu.org/licenses/>.
*/

/**
 * 
 */
package actions.listeners;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import actions.Questioner;

import view.ElementFinder;
import view.FrameSettings;
import view.tabdrawer.ElementDrawer;

import commands.Command;
import commands.CommandFilter;
import commands.add.AddElementCommand;
import commands.add.AddElementCommandFilter;
import commands.change.ChangePlaceValuesCommand;
import commands.change.ChangeTransitionValuesCommand;
import commands.delete.DeleteElementCommand;
import commands.move.MoveElementCommand;
import commands.move.MoveElementCommandFilter;

import data.Data;
import data.elements.Arc;
import data.elements.Element;
import data.elements.Place;
import data.elements.Transition;

/**
 * This class invokes acts which are used by listeners, includes methods for
 * adding, deleting, moving and changing values of Elements.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class ListenersInvoker {
    private Data data;

    private JFrame mainFrame;

    private ElementDrawer elementDrawer;

    /**
     * @param data
     */
    public ListenersInvoker(final Data data, JFrame mainFrame,
            ElementDrawer elementDrawer) {
        super();
        this.data = data;
        this.mainFrame = mainFrame;
        this.elementDrawer = elementDrawer;
    }

    public void changeValuesOfElementAt(final int x, final int y) {
        Element element = ElementFinder.findElement(x, y, data);

        if ((element != null) && (!(element instanceof Arc))) {

            // TODO: refactor with arrays:
            String title;
            // String message;
            // String initialSelectionValue;
            // String resStr;
            if (element instanceof Place) {
                Integer res;
                while (true) {
                    try {
                        title = "Changing place values";
                        String message = "Number of token:";
                        String initialSelectionValue = ((Integer) ((Place) element)
                                .getNumTokens()).toString();
                        
                        res = Questioner.askInt(mainFrame, title, message,
                                initialSelectionValue, null);
                        if (res == null) {
                            break;
                        }
                        
                        ChangePlaceValuesCommand command = new ChangePlaceValuesCommand(
                                (Place) element, res);
                        data.getCommandStack().add(command);
                        command.execute();
                        data.setChanged(true);
                        break;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(mainFrame,
                                "Wrong number. Repeat please.",
                                "Error of inputting",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else {
                if (element instanceof Transition) {
                    Double lyambda;
                    Double g;
                    Double r;
                    while (true) {
                        try {
                            title = "Changing transition values";
                            String message = "Lyambda:";
                            String initialSelectionValue = ((Double) ((Transition) element)
                                    .getLyambda()).toString();
                            lyambda = Questioner.askDouble(mainFrame, title,
                                    message, initialSelectionValue, null);
                            if (lyambda == null) {
                                break;
                            }
                            
                            message = "g:";
                            initialSelectionValue = ((Double) ((Transition) element)
                                    .getG()).toString();
                            g = Questioner.askDouble(mainFrame, title,
                                    message, initialSelectionValue, null);
                            if (g == null) {
                                break;
                            }

                            message = "r:";
                            initialSelectionValue = ((Double) ((Transition) element)
                                    .getR()).toString();
                            r = Questioner.askDouble(mainFrame, title,
                                    message, initialSelectionValue, null);
                            if (r == null) {
                                break;
                            }

                            ChangeTransitionValuesCommand command = new ChangeTransitionValuesCommand(
                                    (Transition) element, lyambda, g, r);
                            data.getCommandStack().add(command);
                            command.execute();
                            data.setChanged(true);
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

    public void activateNewElementAddingTo(int x, int y) {
        Element addedElement = data.getAddingModeElement();

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

    public void selectElementAt(final int x, final int y) {
        Element selElement = ElementFinder.findElement(x, y, data);

        if (selElement != null) {
            data.setActiveElement(selElement);
        } else {
            data.setActiveElement(null);
        }
        mainFrame.repaint();
    }

    public void activateElementMovingTo(final int x, final int y) {

        Element selElement = data.getActiveElement();

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

    public void activateSelectedElementDeleting() {
        Element selElement = data.getActiveElement();

        if (selElement != null) {

            DeleteElementCommand command = new DeleteElementCommand(selElement,
                    data);
            data.getCommandStack().add(command);
            command.execute();
            data.setChanged(true);
            mainFrame.repaint();
        }
    }
}