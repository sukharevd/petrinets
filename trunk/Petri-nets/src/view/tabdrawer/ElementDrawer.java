package view.tabdrawer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.FrameSettings;
import view.elementpainters.ArcPainter;
import view.elementpainters.PlacePainter;
import view.elementpainters.TransitionPainter;

import actions.listeners.DrawerKeyListener;
import actions.listeners.DrawerMouseListener;
import data.Data;
import data.elements.Arc;
import data.elements.Element;
import data.elements.Place;
import data.elements.Transition;

/**
 * Drawing area and set of the method for painting at it.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class ElementDrawer extends JPanel {

    /**
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = 7300244067147210807L;

    private Data data;


    public ElementDrawer(final Data data, final JFrame mainFrame) {
        super();
        this.data = data;
        DrawerMouseListener dml = new DrawerMouseListener(data, mainFrame, this);
        addMouseListener(dml);
        addMouseMotionListener(dml);
        addKeyListener(new DrawerKeyListener(data, mainFrame));
        setFocusable(true);
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(final Data data) {
        this.data = data;
    }

    protected Color chooseElementColor(Element element) {
        if (element == data.getActiveElement()) {
            return FrameSettings.activeElementsColor();
        } else {
            if ((element.getType() == "A")
                    && (((Arc) element).getToType() == "P")) {
                return FrameSettings.inputArcsColor();
            }
            return FrameSettings.elementsColor();
        }
    }
    
    protected Color chooseGradientColor1(Element element) {
        if (element == data.getActiveElement()) {
            return FrameSettings.actgradcolor1();
        } else {
            if ((element.getType() == "A")
                    && (((Arc) element).getToType() == "P")) {
                return FrameSettings.inputArcsColor();
            }
            return FrameSettings.stdgradcolor1();
        }
    }

    protected Color chooseGradientColor2(Element element) {
        if (element == data.getActiveElement()) {
            return FrameSettings.actgradcolor2();
        } else {
            if ((element.getType() == "A")
                    && (((Arc) element).getToType() == "P")) {
                return FrameSettings.inputArcsColor();
            }
            return FrameSettings.stdgradcolor2();
        }
    }
    
    protected void paintGrid(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Color c = FrameSettings.gridColor();
        g2.setColor(c);
        for (int i = 0; i < this.getWidth(); i += FrameSettings.elementHeight()) {
            for (int j = 0; j < this.getHeight(); j += FrameSettings
                    .elementHeight()) {
                g2.drawRect(i, j, 1, 1);
            }
        }
    }

    protected void paintAddedArc(Graphics g) {

        Color color = FrameSettings.addedArcColor();
        ArcPainter pointCommand = new ArcPainter((Arc) data
                .getAddingModeElement(), "P", color);
        pointCommand.paint(g);
    }

    public void paint(Graphics g) {

        if (data == null) {
            return;
        }
        paintGrid(g);

        ArrayList<Element> elements = data.getElements();
        Color color;
        if ((data.getAddingModeElement() != null)
                && (data.getAddingModeElement().getType() == "A")) {
            paintAddedArc(g);
        }

        if (elements.size() != 0) {
            // draw arcs
            for (int i = 0; i < elements.size(); i++) {
                Arc arc;
                for (int j = 0; j < elements.get(i).getOutputArcs().size(); j++) {
                    arc = elements.get(i).getOutputArcs().get(j);
                    color = chooseElementColor(arc);
                    ArcPainter pointCommand = new ArcPainter(arc, elements.get(
                            i).getType(), color);
                    pointCommand.paint(g);
                }
            }

            // draw places, transitions
            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i).getType() == "P") {
                    Place p = (Place) elements.get(i);
                    color = chooseElementColor(p);
                    Color grclr1 = chooseGradientColor1(p);
                    Color grclr2 = chooseGradientColor2(p);
                    PlacePainter pointCommand = new PlacePainter(p, color, 
                    		grclr1, grclr2);
                    pointCommand.paint(g);
                } else {
                    if (elements.get(i).getType() == "T") {
                        Transition t = (Transition) elements.get(i);
                        color = chooseElementColor(t);
                        Color grclr1 = chooseGradientColor1(t);
                        Color grclr2 = chooseGradientColor2(t);
                        TransitionPainter pointCommand = new TransitionPainter(
                                t, color, grclr1, grclr2);
                        pointCommand.paint(g);
                    }
                }

            }
        }

    }

}