/*
    Copyright (C)  2009  Sukharev Dmitriy, Dzyuban Yuriy, Vixen Tael.
    
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

package view.elementpainters;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import view.FrameSettings;
import data.elements.Place;

/**
 * Points place at drawing area.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class PlacePainter implements Painter {

    private Place place;

    private Color color;
    
    private Color gradcolor1;
    
    private Color gradcolor2;
    

    public PlacePainter(final Place place, final Color color, final Color gradcolor1,
    		final Color gradcolor2) {
        this.place = place;
        this.color = color;
        this.gradcolor1 = gradcolor1;
        this.gradcolor2 = gradcolor2;
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
        //gradcolor1 = new Color(255,255,153);
        //gradcolor2 = new Color(255,78,0);
        GradientPaint gradient = new GradientPaint(x,y,gradcolor1,
        		x+width,y+height,gradcolor2);
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
