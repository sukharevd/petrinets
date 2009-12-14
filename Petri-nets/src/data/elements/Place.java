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

    private int numTokens;

    public Place(int numTokens, int no, int x, int y) {
        setNo(no);
        setX(x);
        setY(y);
        setType("P");
        setInputArcs(new ArrayList<Arc>());
        setOutputArcs(new ArrayList<Arc>());

        this.numTokens = numTokens;
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

    public Object clone() {
        Place place = new Place(numTokens, getNo(), getX(), getY());

        ArrayList<Arc> outputArcs = new ArrayList<Arc>();
//        ArrayList<Arc> inputArcs = new ArrayList<Arc>();
        for (int i = 0; i < this.getOutputArcs().size(); i++) {
            outputArcs.add((Arc) this.getOutputArcs().get(i).clone());
        }
//        for (int i = 0; i < this.getInputArcs().size(); i++) {
//            inputArcs.add((Arc) this.getInputArcs().get(i).clone());
//        }

        place.setOutputArcs(outputArcs);
//        place.setInputArcs(inputArcs);
// TODO: delete comments.
        return place;
    }

    public boolean equals(final Object o) {
        Place place;
        try {
            place = (Place) o;
        } catch (ClassCastException e) {
            return false;
        }

        boolean isEquals = true;
        int oNo = place.getNo();
        int oX = place.getX();
        int oY = place.getY();
        int oNumTokens = place.getNumTokens();
        if ((this.getNo() != oNo) || (this.getX() != oX) || (this.getY() != oY)
                || (this.getNumTokens() != oNumTokens)) {
            isEquals = false;
        }

        if (!isArcsEqual(place)) {
            isEquals = false;
        }

        return isEquals;
    }

    protected boolean isArcsEqual(final Place place) {
        boolean isEqual = true;

        int length = this.getOutputArcs().size();
        if (length != place.getOutputArcs().size()) {
            isEqual = false;
        } else {
            for (int i = 0; i < length; i++) {
                if (!this.getOutputArcs().get(i).equals(
                        place.getOutputArcs().get(i))) {
                    isEqual = false;
                }
            }
        }
        return isEqual;
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