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

/**
 * 
 */
package view;

import data.Data;
import data.elements.Arc;
import data.elements.Element;
import data.elements.Place;
import data.elements.Transition;

/**
 * Finds elements of drawing area by coordinates.
 * 
 * @author <a href="mailto:ydzyuban@gmail.com">Dzyuban Yuriy</a>
 * 
 */
public class ElementFinder {

    public static Element findElement(final int pixX, final int pixY,
            final Data data) {
        int transitionWidth = FrameSettings.transitionWidth();
        int elementHeight = FrameSettings.elementHeight();
        int arcFindingDistance = FrameSettings.arcFindingDistance();

        Element res;
        for (int i = 0; i < data.getElements().size(); i++) {
            if (data.getElements().get(i).getType() == "P") {
                Place current = (Place) data.getElements().get(i);
                int y = current.getY();
                int x = current.getX();
                if (Math
                        .sqrt((x - pixX) * (x - pixX) + (y - pixY) * (y - pixY)) < elementHeight / 2) {
                    res = data.getElements().get(i);
                    return res;
                }
            } else if (data.getElements().get(i).getType() == "T") {
                Transition current = (Transition) data.getElements().get(i);
                int y = current.getY();
                int x = current.getX();
                if ((Math.abs(x - pixX) < (transitionWidth / 2))
                        && (Math.abs(y - pixY) < (elementHeight / 2))) {
                    res = data.getElements().get(i);
                    return res;
                }
            }
        }

        for (int i = 0; i < data.getElements().size(); i++) {
            Element el = data.getElements().get(i);
            for (int j = 0; j < el.getOutputArcs().size(); j++) {
                Arc arc = el.getOutputArcs().get(j);
                for (int k = 0; k < arc.getXsequence().size() - 1; k++) {
                    int x0 = arc.getXsequence().get(k);
                    int x1 = arc.getXsequence().get(k + 1);
                    int y0 = arc.getYsequence().get(k);
                    int y1 = arc.getYsequence().get(k + 1);

                    int maxx = (x1 > x0) ? (x1) : (x0);
                    int minx = (x1 < x0) ? (x1) : (x0);
                    int maxy = (y1 > y0) ? (y1) : (y0);
                    int miny = (y1 < y0) ? (y1) : (y0);
                    if ((pixX < maxx + arcFindingDistance)
                            && (pixX > minx - arcFindingDistance)
                            && (pixY < maxy + arcFindingDistance)
                            && (pixY > miny - arcFindingDistance)) {
                        int d = (int) (Math.abs((y0 - y1) * pixX + (x1 - x0)
                                * pixY + (x0 * y1 - x1 * y0)) / Math.sqrt(Math
                                .pow((double) x1 - x0, 2)
                                + Math.pow((double) y1 - y0, 2)));
                        if (d < arcFindingDistance) {
                            return arc;
                        }
                    }
                }
            }
        }
        return null;
    }

    // public final Element findElement(final int pixX, final int pixY) {
    // Element res = null;
    // for (int i = 0; i < Main.getData().getElements().size(); i++) {
    // if (Main.getData().getElements().get(i).getType() == "P") {
    // Place current = (Place) Main.getData().getElements().get(i);
    // int y = current.getY();
    // int x = current.getX();
    // if (Math
    // .sqrt((x - pixX) * (x - pixX) + (y - pixY) * (y - pixY)) < elementHeight
    // / 2) {
    // res = Main.getData().getElements().get(i);
    // return res;
    // }
    // } else if (Main.getData().getElements().get(i).getType() == "T") {
    // Transition current = (Transition) Main.getData().getElements()
    // .get(i);
    // int y = current.getY();
    // int x = current.getX();
    // if ((Math.abs(x - pixX) < (transitionWidth / 2))
    // && (Math.abs(y - pixY) < (elementHeight / 2))) {
    // res = Main.getData().getElements().get(i);
    // return res;
    // }
    // }
    // }
    // for (int i = 0; i < Main.getData().getElements().size(); i++) {
    // if (Main.getData().getElements().get(i).getOutputArcs() != null) {
    // for (int k = 0; k <
    // Main.getData().getElements().get(i).getOutputArcs().size(); k++) {
    // Arc current = Main.getData().getElements().get(i).getOutputArcs().get(k);
    // for (int j = 0; j < current.getXsequence().size() - 1; j++) {
    // int curX = current.getXsequence().get(j);
    // int curY = current.getYsequence().get(j);
    //                           
    // int curX1 = current.getXsequence().get(j + 1);
    // int curY1 = current.getYsequence().get(j + 1);
    // if ((curX == curX1)&&((pixY>curY ||(pixY>curY1))&&((pixY<curY1)||
    // (pixY<curY)))) {
    // if (Math.abs(pixX - curX) < 2) {
    // res = current;
    // return res;
    // }
    // }
    // if ((curY == curY1)&&((pixX>curX ||(pixX>curX1))&&((pixX<curX1)||
    // (pixX<curX)))) {
    // if (Math.abs(pixY - curY) < 2) {
    // res = current;
    // return res;
    // }
    // }
    // if((curY != curY1) &&(curX !=curX1) &&((pixX>curX
    // ||(pixX>curX1))&&((pixX<curX1)|| (pixX<curX)))&&((pixY>curY
    // ||(pixY>curY1))&&((pixY<curY1)|| (pixY<curY)))){
    // double a=(1/ (double)(curX1-curX));
    // double b=(-1/ (double)(curY1-curY));
    // double c= -curX/ (double)(curX1-curX)+curY/ (double)(curY1-curY);
    // if ((Math.abs(a*pixX+b*pixY+c)/Math.sqrt(a*a+b*b))<2){
    // res = current;
    // return res;
    // }
    //
    // }
    // }
    // }
    // }
    // }
    // return res;
    // }
}
