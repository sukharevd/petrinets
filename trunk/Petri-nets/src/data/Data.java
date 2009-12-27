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

package data;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import commands.CommandStack;

import data.elements.Arc;
import data.elements.Element;
import data.elements.Place;
import data.elements.Transition;
import data.exceptions.ArcConnectionException;
import data.xmlparsing.ElementXmlParser;

/**
 * Main data storage of the application, It contains all visible Elements,
 * active Element, added Element, commandStack and flag of chaning.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class Data {

    /**
     * Indicator of table changes. It has true value if table was changed after
     * the last saving action, else it is false.
     */
    private boolean changed = false;

    /**
     * Elements of current Petri-net.
     */
    private ArrayList<Element> elements;

    /**
     * Element, which is added by user at the edit area.
     */
    private Element addingModeElement = null;
    
    /**
     * Element, which is selected by user at the edit area.
     */
    private Element activeElement = null;

    /**
     * Stack of the commands for realizing Undo/Redo operations.
     */
    private CommandStack commandStack = null;

    /**
     * Constructor of {@link Data}.
     * @param elements new elements
     */
    public Data(ArrayList<Element> elements) {
        this.elements = elements;
        this.setCommandStack(new CommandStack());
    }

    /**
     * @return the changed
     */
    public final boolean isChanged() {
        return changed;
    }

    /**
     * @param changed
     *            the changed to set
     */
    public final void setChanged(boolean changed) {
        this.changed = changed;
    }

    /**
     * @return the elements
     */
    public final ArrayList<Element> getElements() {
        return elements;
    }

    /**
     * @param elements
     *            the elements to set
     */
    public final void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    /**
     * @return the addingModeElement
     */
    public final Element getAddingModeElement() {
        return addingModeElement;
    }

    /**
     * @param addingModeElement
     *            the addingModeElement to set
     */
    public final void setAddingModeElement(Element addingModeElement) {
        this.addingModeElement = addingModeElement;
    }

    /**
     * @param activeElement
     *            the activeElement to set
     */
    public void setActiveElement(Element activeElement) {
        this.activeElement = activeElement;
    }

    /**
     * @return the activeElement
     */
    public Element getActiveElement() {
        return activeElement;
    }

    /**
     * @param commandStack
     *            the commandStack to set
     */
    public void setCommandStack(CommandStack commandStack) {
        this.commandStack = commandStack;
    }

    /**
     * @return the commandStack
     */
    public CommandStack getCommandStack() {
        return commandStack;
    }

    public void add(Element element) {
        elements.add(element);
    }

    public void remove(Element element) {
        elements.remove(element);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    public Object clone() {
        ArrayList<Element> e = new ArrayList<Element>();
        for (int i = 0; i < elements.size(); i++) {
            e.add((Element) elements.get(i).clone());
        }
        
        Data clone = new Data(e);
        clone.synchronizeInputAndOutputArcs();
        return clone;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object o) {
        boolean isEquals = true;

        Data oData = (Data) o;
        if (this.elements.size() != oData.getElements().size()) {
            isEquals = false;
        } else {
            for (int i = 0; i < this.getElements().size(); i++) {
                Element e1 = this.getElements().get(i);
                Element e2 = oData.getElements().get(i);
                if (this.getElements().get(i) instanceof Place) {
                    if (!this.<Place> areElementsEqual(e1, e2)) {
                        isEquals = false;
                    }
                } else {
                    if (this.getElements().get(i) instanceof Transition) {
                        if (!this.<Transition> areElementsEqual(e1, e2)) {
                            isEquals = false;
                        }
                    }
                }
            }
        }

        return isEquals;
    }

    // TODO: how to cast types safely?
    @SuppressWarnings("unchecked")
    protected <T extends Element> boolean areElementsEqual(Element e1, Element e2) {
        boolean isEquals = true;

        if (e1.getClass() != e2.getClass()) {
            isEquals = false;
        } else {
            T p1 = (T) e1;
            if (!p1.equals(e2)) {
                isEquals = false;
            }
        }

        return isEquals;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elements.size(); i++) {
            sb.append(elements.get(i));
            sb.append("\n");
        }
        return sb.toString();
    }

    public void save(String path) {
        String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<project>\n";
        String footer = "</project>";
        FileWriter fw = null;

        try {
            fw = new FileWriter(path);
            fw.write(header);
            fw.write(this.toString());
            fw.write(footer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                fw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        changed = false;
    }

    public void load(String path) {
        ElementXmlParser parser = new ElementXmlParser();
        setElements(parser.build(path).getElements());

        synchronizeInputAndOutputArcs();

        checkAllArcs();

        changed = false;
    }

    protected boolean checkAllArcs() {
        try {
            for (int i = 0; i < getElements().size(); i++) {
                for (int j = 0; j < getElements().get(i).getOutputArcs().size(); j++) {
                    checkArc(getElements().get(i).getOutputArcs().get(j));
                }
                for (int j = 0; j < getElements().get(i).getInputArcs().size(); j++) {
                    checkArc(getElements().get(i).getInputArcs().get(j));
                }
            }
            return true;
        } catch (ArcConnectionException e) {
            JOptionPane.showMessageDialog(null,
                    "At least one arc has incorrect connection.",
                    "Error of connection", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * @return true if place connect with transition (& not with nothing) and if
     *         arc connected with element with no == to.
     * @throws ArcConnectionException
     */
    protected boolean checkArc(final Arc arc) throws ArcConnectionException {
        int x = arc.getXsequence().get(0);
        int y = arc.getYsequence().get(0);
        int x2 = arc.getXsequence().get(arc.getXsequence().size() - 1);
        int y2 = arc.getYsequence().get(arc.getYsequence().size() - 1);
        String t = null;
        String t2 = null;
        // int no = -1;
        int no2 = -1;
        for (int i = 0; i < getElements().size(); i++) {
            int ex = getElements().get(i).getX();
            int ey = getElements().get(i).getY();
            if ((ex == x) && (ey == y)) {
                t = getElements().get(i).getType();
                // no = Main.getData().getElements().get(i).getNo();
            }

            if ((ex == x2) && (ey == y2)) {
                t2 = getElements().get(i).getType();
                no2 = getElements().get(i).getNo();
            }
        }

        if ((t == null) || (t2 == null)) {
            throw new ArcConnectionException();
        }

        if (t == t2) {
            throw new ArcConnectionException();
        }

        if (no2 != arc.getTo()) {
            throw new ArcConnectionException();
        }

        return true;
    }

    public final void synchronizeInputAndOutputArcs() {
        for (int k = 0; k < elements.size(); k++) {

            String type = elements.get(k).getType();
            for (int i = 0; i < elements.get(k).getOutputArcs().size(); i++) {
                int to = elements.get(k).getOutputArcs().get(i).getTo();

                try {
                    checkArc(elements.get(k).getOutputArcs().get(i));
                } catch (ArcConnectionException e) {
                    System.err.println("Wrong arc to" + to + " connection.");
                }

                for (int j = 0; j < getElements().size(); j++) {
                    Element el = getElements().get(j);
                    if ((el.getType() != type) && (el.getNo() == to)) {
                        el.getInputArcs().add(
                                elements.get(k).getOutputArcs().get(i));
                    }
                }
            }
        }
    }

    public ArrayList<Place> getPlaces() {
        ArrayList<Place> list = new ArrayList<Place>();
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i) instanceof Place) {
                list.add((Place) elements.get(i));
            }
        }
        this.<Place> sort(list);

        return list;
    }

    public ArrayList<Transition> getTransitions() {
        ArrayList<Transition> list = new ArrayList<Transition>();
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i) instanceof Transition) {
                list.add((Transition) elements.get(i));
            }
        }
        this.<Transition> sort(list);

        return list;
    }

    public Place getPlaceWithNo(int no) {
        Place place = null;
        for (int i = 0; i < elements.size(); i++) {
            if ((elements.get(i) instanceof Place)
                    && (elements.get(i).getNo() == no)) {
                place = (Place) elements.get(i);
            }
        }

        return place;
    }

    public Transition getTransitionWithNo(int no) {
        Transition transition = null;
        for (int i = 0; i < elements.size(); i++) {
            if ((elements.get(i) instanceof Transition)
                    && (elements.get(i).getNo() == no)) {
                transition = (Transition) elements.get(i);
            }
        }

        return transition;
    }

    protected <T extends Element> void sort(ArrayList<T> el) {
        boolean isSorted;
        T temp = null;
        do {
            isSorted = true;
            for (int i = 0; i < el.size() - 1; i++) {
                if (el.get(i).getNo() > el.get(i + 1).getNo()) {
                    temp = el.get(i);
                    el.set(i, el.get(i + 1));
                    el.set(i + 1, temp);
                    isSorted = false;
                }
            }
        } while (!isSorted);
    }
    
    public void changeDataMarking(final Marking marking) {
        ArrayList<Place> places;
        places = this.getPlaces();
        for (int i = 0; i < places.size(); i++) {
            int numTokens = marking.getAt(i);            
            places.get(i).setNumTokens(numTokens);
        }
    }
    
    public Marking getMarking() {
        ArrayList<Place> places = getPlaces();
        ArrayList<Integer> tokens = new ArrayList<Integer>(); 
        for (int i = 0; i < places.size(); i++) {
            tokens.add(places.get(i).getNumTokens());
        }
        // TODO: 0? Am I sure?
        Marking marking = new Marking(0, tokens);
        return marking;
    }    

    public <T extends Element> int getFirstFreeElementNo(ArrayList<T> elements) {
        int first = -1;

        this.<T> sort(elements);
        if ((elements.size() == 0) || (elements.get(0).getNo() != 1)) {
            first = 1;
        } else {
            for (int i = 0; i < elements.size() - 1; i++) {
                if (elements.get(i + 1).getNo() - elements.get(i).getNo() > 1) {
                    first = elements.get(i).getNo() + 1;
                }
            }
            if (first == -1) {
                first = elements.get(elements.size() - 1).getNo() + 1;
            }
        }
        return first;
    }
}
