package view.elementpainters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import view.FrameSettings;

import data.Arc;

/**
 * Points arc at drawing area.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class ArcPainter implements Painter {

    private Arc arc;

    private String fromType;

    private Color color;
    
        
    public ArcPainter(final Arc arc, final String fromType, final Color color) {
        this.arc = arc;
        this.fromType = fromType;
        this.color = color;
    }

    @Override
    public void paint(Graphics g) {

        // private int width = ElementDrawer.arcWidth;
        // private double arrowDegree = 0.2;
        int r = FrameSettings.elementHeight() / 2;
        int transWidth = FrameSettings.transitionWidth();
        Color arcArrowColor = FrameSettings.arcArrowColor();

        ArrayList<Integer> xseq = arc.getXsequence();
        ArrayList<Integer> yseq = arc.getYsequence();

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);

        int x1;
        int x2;
        int y1;
        int y2;
        for (int i = 0; i < xseq.size() - 1; i++) {
            x1 = xseq.get(i);
            x2 = xseq.get(i + 1);
            y1 = yseq.get(i);
            y2 = yseq.get(i + 1);
            // for (int j = -width / 2; j < width / 2; j++) {
            g2.drawLine(x1, y1, x2, y2);
            // }
        }

        if (arc.getToType() != null) {
            int size = xseq.size();
            x1 = xseq.get(size - 2);
            x2 = xseq.get(size - 1);
            y1 = yseq.get(size - 2);
            y2 = yseq.get(size - 1);
            double k;
            double fi;
            if (x2 != x1) {
                k = (double) (y2 - y1) / (double) (x2 - x1);
                fi = Math.atan(k);
                if (x2 > x1) {
                    fi += Math.PI;
                }

            } else {
                fi = Math.PI / 2;
                if (y2 > y1) {
                    fi += Math.PI;
                }
            }

            // double fi1 = fi + arrowDegree;
            // double fi2 = fi - arrowDegree;

            double xi = 0;
            if (fromType == "T") {
                xi = x2 + r * Math.cos(fi);
            } else {
                if (fromType == "P") {
                    if (x2 < x1) {
                        xi = x2 + transWidth / 2;
                    } else {
                        xi = x2 - transWidth / 2;
                    }
                }
            }
            double yi = y2 + r * Math.sin(fi);
            // System.out.println(", xi = " + xi + " yi = " + yi);
            g2.setColor(arcArrowColor);
            g2.fillOval((int) xi - 5, (int) yi - 5, 10, 10);
        }

    }

}
