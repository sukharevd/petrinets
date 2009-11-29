package view.elementpainters;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import view.FrameSettings;

import data.elements.Transition;

/**
 * Points transition at the drawing area.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class TransitionPainter implements Painter {

    private Transition transition;

    private Color maincolor;
    private Color grcolor1;
    private Color grcolor2;
    
    //private Color momentarygrcolor1 = new Color(8,64,149);
    private Color momentarygrcolor1 = Color.gray;
    private Color momentarygrcolor2 = Color.black;

    public TransitionPainter(final Transition transition,  final Color maincolor,
    		final Color grcolor1, 
    		final Color grcolor2) {
        this.transition = transition;
        this.grcolor1 = grcolor1;
        this.grcolor2 = grcolor2;
        this.maincolor = maincolor;
    }

    @Override
    public void paint(Graphics g) {

        int width = FrameSettings.transitionWidth();
        int height = FrameSettings.elementHeight();

        int x = transition.getX() - width / 2;
        int y = transition.getY() - height / 2;

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(g2.getBackground());
        GradientPaint gradient = new GradientPaint(x,y,grcolor1,
        		x+width,y+height,grcolor2);
        g2.setPaint(gradient);
        g2.fillRect(x, y, width, height);
        
        g.setColor(maincolor);
        if (transition.getLyambda() == 0) {
            GradientPaint gradient2 = new GradientPaint(x,y,momentarygrcolor1,
            		x+width,y+height,momentarygrcolor2);
            g2.setPaint(gradient2);
            g2.fillRect(x, y, width, height);
            g.setColor(maincolor);
        	g2.drawRect(x, y, width, height);
        } else {
            g2.drawRect(x, y, width, height);
        }

        String title = transition.getTitle();
        g2.setColor(Color.gray);
        g2.drawString(title, transition.getX(), transition.getY() + height / 2
                + 10);

        // Integer numTokens = transition.getNumTokens();
        // g2.drawString(numTokens.toString(), transition.getX() - offset,
        // transition.getY() + offset);

    }

}
