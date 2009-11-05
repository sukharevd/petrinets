package view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import data.Place;

/**
 * Points place at drawing area.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class PlacePainter implements Painter {

    private Place place;

    private Color color;

    public PlacePainter(final Place place, final Color color) {
        this.place = place;
        this.color = color;
    }

    @Override
    public void paint(Graphics g) {

        int r = FrameSettings.elementHeight() / 2;
        int offset = FrameSettings.placeTokensTextOffset();

        int x = place.getX() - r;
        int y = place.getY() - r;
        int width = 2 * r;
        int height = 2 * r;

        Graphics2D g2 = (Graphics2D) g;
        //g2.setColor(g2.getBackground());
        Color lightyellow = new Color(255,255,153);
        Color lightred = new Color(255,78,0);
        GradientPaint gradient = new GradientPaint(x,y,lightyellow,
        		x+width,y+height,lightred);
        g2.setPaint(gradient);
        g2.fillOval(x, y, width, height);

        g2.setColor(color);
        g2.drawOval(x, y, width, height);

        Integer numTokens = place.getNumTokens();
        if (numTokens != 0) {
            g2.drawString(numTokens.toString(), place.getX() - offset, place
                    .getY()
                    + offset);
        }

        String title = place.getTitle();
        g2.setColor(Color.gray);
        g2.drawString(title, place.getX(), place.getY() + r + 10);

    }

}
