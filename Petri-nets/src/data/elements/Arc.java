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

        if (xsequence.size() != ysequence.size()) {
            System.err.println("Error: Arc X.size not equal Y.size.");
        }
        for (int i = 0; i < xsequence.size(); i++) {
            xst.set(i, xsequence.get(i) + 2);
            yst.set(i, ysequence.get(i) + 2);
        }

        return new Arc(xst, yst, to, toType);
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