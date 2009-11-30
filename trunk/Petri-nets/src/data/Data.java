package data;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import commands.CommandStack;
import data.elements.Arc;
import data.elements.Element;
import data.xmlparsing.ElementXmlParser;

import exceptions.arcConnectionException;

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

    private ArrayList<Element> elements;

    private Element addingModeElement = null;

    private Element activeElement = null;

    private CommandStack commandStack = null;

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

    // public void draw() {
    // throw new UnsupportedOperationException();
    // }

    public Object clone() {
        ArrayList<Element> e = new ArrayList<Element>();
        for (int i = 0; i < elements.size(); i++) {
            e.add((Element) elements.get(i).clone());
        }
        return new Data(e);
    }

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
        } catch (arcConnectionException e) {
            JOptionPane.showMessageDialog(null,
                    "At least one arc has incorrect connection.",
                    "Error of connection", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * @return true if place connect with transition (& not with nothing) and if
     *         arc connected with element with no == to.
     * @throws arcConnectionException
     */
    protected boolean checkArc(final Arc arc) throws arcConnectionException {
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
            throw new arcConnectionException();
        }

        if (t == t2) {
            throw new arcConnectionException();
        }

        if (no2 != arc.getTo()) {
            throw new arcConnectionException();
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
                } catch (arcConnectionException e) {
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

    @SuppressWarnings("unchecked")
    public final <T extends Element> ArrayList<T> getTypedElements() {
        ArrayList<T> list = new ArrayList<T>();
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getType() == "P") {
                list.add((T) elements.get(i));
            }
        }

        this.<T> sort(list);

        return list;
    }

    @SuppressWarnings("unchecked")
    public final <T extends Element> T getElementWithNo(int no) {
        T place = null;
        for (int i = 0; i < elements.size(); i++) {
            if ((elements.get(i).getType() == "P")
                    && (elements.get(i).getNo() == no)) {
                place = (T) elements.get(i);
            }
        }

        return place;
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
}
