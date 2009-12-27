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

package data.elements;

import java.util.ArrayList;

import data.xmlparsing.XmlCompatible;

/**
 * Abstract class for all Elements of petri-net, It provides all base and
 * general abilities and features for all type of Elements.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public abstract class Element implements Cloneable, XmlCompatible {
    private int no;

    private int x;

    private int y;

    private ArrayList<Arc> inputArcs;

    private ArrayList<Arc> outputArcs;

    private String type;

    // private BuiltElement parent;

    public Element() {
        inputArcs = null;
        outputArcs = null;
    }

    /**
     * @return the no
     */
    public final int getNo() {
        return no;
    }

    /**
     * @param no
     *            the no to set
     */
    public final void setNo(int no) {
        this.no = no;
    }

    /**
     * @return the x
     */
    public final int getX() {
        return x;
    }

    /**
     * @param x
     *            the x to set
     */
    public final void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public final int getY() {
        return y;
    }

    /**
     * @param y
     *            the y to set
     */
    public final void setY(int y) {
        this.y = y;
    }

    /**
     * @return the inputArcs
     */
    public final ArrayList<Arc> getInputArcs() {
        return inputArcs;
    }

    /**
     * @param inputArcs
     *            the inputArcs to set
     */
    public final void setInputArcs(final ArrayList<Arc> inputArcs) {
        this.inputArcs = inputArcs;
    }

    /**
     * @return the outputArcs
     */
    public final ArrayList<Arc> getOutputArcs() {
        return outputArcs;
    }

    /**
     * @param outputArcs
     *            the outputArcs to set
     */
    public final void setOutputArcs(final ArrayList<Arc> outputArcs) {
        this.outputArcs = outputArcs;
    }

    // public final void synchronizeInputAndOutputArcs() {
    // String type = this.type;
    // for (int i = 0; i < outputArcs.size(); i++) {
    // int to = outputArcs.get(i).getTo();
    //            
    // // try {
    // // checkArc(outputArcs.get(i));
    // // } catch (arcConnectionException e) {
    // // System.err.println("Wrong arc to" + to + " connection.");
    // // }
    //            
    // for (int j = 0; j < Main.getData().getElements().size(); j++) {
    // Element el = Main.getData().getElements().get(j);
    // if ((el.getType() != type) && (el.getNo() == to)) {
    // el.getInputArcs().add(outputArcs.get(i));
    // }
    // }
    // }
    // }

    /**
     * @return the type
     */
    public final String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public final void setType(String type) {
        this.type = type;
    }

    public final void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String getTitle() {
        return type + no;
    }

    // public abstract void draw();

    public abstract Object clone();

    public String toXml() {
        return this.toString();
    }

    public abstract String toString();

}