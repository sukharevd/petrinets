package data.elements;

import java.util.ArrayList;

/**
 * Contains features of the place of petri-net, Provides methods for setting and
 * getting this features.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class Place extends Element {

    private static int curIndex = 0;

    private int numTokens;

    public Place(int numTokens, int no, int x, int y) {
        setNo(no);
        setX(x);
        setY(y);
        setType("P");
        setInputArcs(new ArrayList<Arc>());
        setOutputArcs(new ArrayList<Arc>());

        this.numTokens = numTokens;
        curIndex++;

    }

    /**
     * @return the numTokens
     */
    public final int getNumTokens() {
        return numTokens;
    }

    /**
     * @param numTokens
     *            the numTokens to set
     */
    public final void setNumTokens(int numTokens) {
        this.numTokens = numTokens;
    }

    public static void setCurIndex(int curIndex) {
        Place.curIndex = curIndex;
    }

    public static int getCurIndex() {
        return Place.curIndex;
    }

    // public void draw() {
    // throw new UnsupportedOperationException();
    // }

    public java.lang.Object clone() {
        // TODO: +2???
        return new Place(numTokens, getNo(), getX() + 2, getY() + 2);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("    <Place no=\"");
        sb.append(getNo());
        sb.append("\" x=\"");
        sb.append(getX());
        sb.append("\" y=\"");
        sb.append(getY());
        sb.append("\" numTokens=\"");
        sb.append(numTokens);
        sb.append("\" >\n");

        for (int i = 0; i < getOutputArcs().size(); i++) {
            sb.append(getOutputArcs().get(i).toString());
            sb.append("\n");
        }

        sb.append("    </Place>\n");

        return sb.toString();
    }
}