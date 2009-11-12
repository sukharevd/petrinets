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
import data.Arc;
import data.Data;
import data.Element;
import data.Place;
import data.Transition;

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

    private Color elementsColor = Color.black;
    
    //yellow
    private Color stdgradcolor1 = new Color(255,255,153);
    //green
    //---------private Color stdgradcolor1 = new Color(204,255,204);
    //violet green
    //private Color stdgradcolor1 = new Color(204,204,255);
    //dima also violet green
    //private Color stdgradcolor1 = new Color(200,221,242);
    
    //darkorange
    //private Color stdgradcolor2 = new Color(255,78,0);
    //lightorange
    private Color stdgradcolor2 = new Color(255,162,0);
    //blue
    //private Color stdgradcolor2 = new Color(51,204,255);
    //jabablue
    //--------private Color stdgradcolor2 = new Color(153,204,204);
    //jabablue2
    //private Color stdgradcolor2 = new Color(153,204,204);
    //blue
    //private Color stdgradcolor2 = new Color(102,204,255);
    
    //lightblue
    //-------private Color actgradcolor1 = new Color(131,219,255);

    //orange
    private Color actgradcolor1 = new Color(255,153,0);
    //violet
    //-------private Color actgradcolor2 = new Color(87,55,204);
    //darkred
    private Color actgradcolor2 = new Color(255,78,0);
    
    //lightblue
    //--------private Color activeElementsColor = new Color(0,246,255);
    //red
    private Color activeElementsColor = new Color(204,0,255);

    //buryuzovyy
    //private Color inputArcsColor = Color.getHSBColor(0.5f, 1f, 0.6f);
    //orange
    //private Color inputArcsColor = new Color(255,102,0);
    //yellow
    private Color inputArcsColor = new Color(255,162,0);
    //green
    //--------private Color inputArcsColor = new Color(102,204,0);
    
    private Color gridColor = Color.getHSBColor(0f, 0f, 0.85f);

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
            return activeElementsColor;
        } else {
            if ((element.getType() == "A")
                    && (((Arc) element).getToType() == "P")) {
                return inputArcsColor;
            }
            return elementsColor;
        }
    }
    
    protected Color chooseGradientColor1(Element element) {
        if (element == data.getActiveElement()) {
            return actgradcolor1;
        } else {
            if ((element.getType() == "A")
                    && (((Arc) element).getToType() == "P")) {
                return inputArcsColor;
            }
            return stdgradcolor1;
        }
    }

    protected Color chooseGradientColor2(Element element) {
        if (element == data.getActiveElement()) {
            return actgradcolor2;
        } else {
            if ((element.getType() == "A")
                    && (((Arc) element).getToType() == "P")) {
                return inputArcsColor;
            }
            return stdgradcolor2;
        }
    }
    
    protected void paintGrid(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Color c = gridColor;
        g2.setColor(c);
        for (int i = 0; i < this.getWidth(); i += FrameSettings.elementHeight()) {
            for (int j = 0; j < this.getHeight(); j += FrameSettings
                    .elementHeight()) {
                g2.drawRect(i, j, 1, 1);
            }
        }
    }

    protected void paintAddedArc(Graphics g) {

        Color color = gridColor;
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