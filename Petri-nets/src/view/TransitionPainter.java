package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import data.Transition;

/**
 * Points transition at the drawing area.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class TransitionPainter implements Painter {

    private Transition transition;
    private Color color;
    
    public TransitionPainter (final Transition transition, final Color color) {
        this.transition = transition;
        this.color = color;
    }
    
    @Override
    public void paint(Graphics g) {
                       
        int width = FrameSettings.transitionWidth();
        int height = FrameSettings.elementHeight();
        
        int x = transition.getX() - width / 2;
        int y = transition.getY() -  height / 2;
        
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setColor(g2.getBackground());
        g2.fillRect(x, y, width, height);

        g2.setColor(color);
        if (transition.getLyambda() == 0) {
            g2.fillRect(x, y, width, height);
        } else {
            g2.drawRect(x, y, width, height);
        }
        
        String title = transition.getTitle();
        g2.setColor(Color.gray);
        g2.drawString(title, transition.getX(), transition.getY() + height / 2 + 10);
        
        
        
//        Integer numTokens = transition.getNumTokens();
//        g2.drawString(numTokens.toString(), transition.getX() - offset, transition.getY() + offset);
        
    }

}
