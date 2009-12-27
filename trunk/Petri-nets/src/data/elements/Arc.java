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

/**
 * Contains features of the arc of petri-net, Provides methods for setting and
 * getting this features.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class Arc extends Element {

    private ArrayList<Integer> xsequence;

    private ArrayList<Integer> ysequence;

    // TODO: are to, from necessary? toElement?
    // private Element fromZ;
    private int to;

    private String toType;

    public Arc(ArrayList<Integer> xseq, ArrayList<Integer> yseq, int to,
            String toType) {
        setNo(0);
        setType("A");

        this.xsequence = xseq;
        this.ysequence = yseq;
        this.to = to;
        this.toType = toType;

        setX(0);
        setY(0);
    }

    // public Arc(ArrayList<Integer> xseq, ArrayList<Integer> yseq, int to) {
    // setNo(0);
    // setType("A");
    //
    // this.xsequence = xseq;
    // this.ysequence = yseq;
    // this.to = to;
    //
    // setX(0);
    // setY(0);
    // }

    /**
     * @return the xsequence
     */
    public final ArrayList<Integer> getXsequence() {
        return xsequence;
    }

    /**
     * @param xsequence
     *            the xsequence to set
     */
    public final void setXsequence(ArrayList<Integer> xsequence) {
        this.xsequence = xsequence;
    }

    /**
     * @return the ysequence
     */
    public final ArrayList<Integer> getYsequence() {
        return ysequence;
    }

    /**
     * @param ysequence
     *            the ysequence to set
     */
    public final void setYsequence(ArrayList<Integer> ysequence) {
        this.ysequence = ysequence;
    }

    /**
     * @return the to
     */
    public final int getTo() {
        return to;
    }

    /**
     * @param to
     *            the to to set
     */
    public final void setTo(int to) {
        this.to = to;
    }

    /**
     * @return the toType
     */
    public final String getToType() {
        return toType;
    }

    /**
     * @param toType
     *            the toType to set
     */
    public final void setToType(String toType) {
        this.toType = toType;
    }

    public final void AddConnectedPoint(final int x, final int y) {
        xsequence.add(x);
        ysequence.add(y);
    }

    public final void ChangeConnectedPoint(final int index, final int x,
            final int y) {
        xsequence.set(index, x);
        ysequence.set(index, y);
    }

    // public void draw() {
    // throw new UnsupportedOperationException();
    // }

    public Object clone() {
        ArrayList<Integer> xst = new ArrayList<Integer>();
        ArrayList<Integer> yst = new ArrayList<Integer>();
        String toType = new String(this.toType);

        // TODO: exception:
        if (xsequence.size() != ysequence.size()) {
            System.err.println("Error: Arc X.size not equal Y.size.");
        }
        for (int i = 0; i < xsequence.size(); i++) {
            xst.add(i, xsequence.get(i));
            yst.add(i, ysequence.get(i));
        }

        return new Arc(xst, yst, to, toType);
    }

    public boolean equals(final Object o) {
        // TODO: Not good-looking code. (see also Place and Transition #equals(...))
        Arc oArc;
        try {
            oArc = (Arc) o;
        } catch (ClassCastException e) {
            return false;
        }

        boolean isEquals = true;
        int oTo = oArc.getTo();

        String oToType = oArc.getToType();
        if ((oToType == null) ^ (this.toType == null)) {
            isEquals = false;
        } else {
            if ((oToType != null) && (this.toType != null)) {
                if ((to != oTo) || (!oToType.equals(toType))) {
                    isEquals = false;
                }
            }
        }

        if (!isXYSequencesEquals(oArc)) {
            isEquals = false;
        }

        return isEquals;
    }

    protected boolean isXYSequencesEquals(final Arc oArc) {
        boolean isEquals = true;

        ArrayList<Integer> xst = oArc.getXsequence();
        ArrayList<Integer> yst = oArc.getYsequence();
        // TODO: exception:
        if (xst.size() != yst.size()) {
            System.err.println("Error: Arc X.size not equal Y.size.");
        }

        if (xst.size() == xsequence.size()) {
            for (int i = 0; i < xst.size(); i++) {
                if ((!xst.get(i).equals(xsequence.get(i))) || (!yst.get(i).equals(ysequence.get(i)))) {
                    isEquals = false;
//                    break;
                }
            }
        } else {
            isEquals = false;
        }

        return isEquals;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("        <Arc to=\"");
        sb.append(to);
        sb.append("\"");

        for (int i = 0; i < xsequence.size(); i++) {
            sb.append(" x");
            sb.append(i + 1);
            sb.append("=\"");
            sb.append(xsequence.get(i));
            sb.append("\"");
            sb.append(" y");
            sb.append(i + 1);
            sb.append("=\"");
            sb.append(ysequence.get(i));
            sb.append("\"");
        }

        sb.append(" />");
        return sb.toString();
    }
}