package data.elements;

import java.util.ArrayList;

import data.generators.Generator;

/**
 * Contains features of the transition of petri-net, Provides methods for
 * setting and getting this features.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class Transition extends Element {
    private static int curIndex = 0;

    private double lyambda;

    private double g;
    
    private double r;

    // TODO: somevar
    // TODO: getters/setters
    // private double somevar; 
    
    private Generator law;

    // TODO: somevar
    public Transition(double lyambda, double g, double r, /*double somevar,*/ Generator law, int no, int x,
            int y) {
        setNo(no);
        setX(x);
        setY(y);
        setType("T");
        setInputArcs(new ArrayList<Arc>());
        setOutputArcs(new ArrayList<Arc>());

        this.lyambda = lyambda;
        this.g = g;
        this.r = r;
        // TODO: somevar
        // this.somevar = somevar;
        this.law = law;

        curIndex++;
    }

    /**
     * @return the lyambda
     */
    public final double getLyambda() {
        return lyambda;
    }

    /**
     * @param lyambda
     *            the lyambda to set
     */
    public final void setLyambda(double lyambda) {
        this.lyambda = lyambda;
    }

    /**
     * @return the g
     */
    public final double getG() {
        return g;
    }

    /**
     * @param g
     *            the g to set
     */
    public final void setG(double g) {
        this.g = g;
    }

    /**
     * @return the r
     */
    public final double getR() {
        return r;
    }

    /**
     * @param r the r to set
     */
    public final void setR(double r) {
        this.r = r;
    }

    /**
     * @return the law
     */
    public final Generator getLaw() {
        return law;
    }

    /**
     * @param law
     *            the law to set
     */
    public final void setLaw(Generator law) {
        this.law = law;
    }

    // public void draw() {
    // throw new UnsupportedOperationException();
    // }

    /**
     * @return the curIndex
     */
    public static int getCurIndex() {
        return curIndex;
    }

    /**
     * @param curIndex
     *            the curIndex to set
     */
    public static void setCurIndex(int curIndex) {
        Transition.curIndex = curIndex;
    }

    public Object clone() {
        return new Transition(lyambda, g, r, law, getNo(), getX() + 2,
                getY() + 2);
    }
    
    public String getTitle() {
        String transType;
        
        // if transition is instant it is marked as "M"
        if (this.lyambda == 0) {
            transType = "M";
        } else {
            transType = "T";
        }
        
        return transType + getNo();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("    <Transition no=\"");
        sb.append(getNo());
        sb.append("\" x=\"");
        sb.append(getX());
        sb.append("\" y=\"");
        sb.append(getY());
        sb.append("\" lyambda=\"");
        sb.append(lyambda);
        sb.append("\" g=\"");
        sb.append(g);
        sb.append("\" r=\"");
        sb.append(r);
        // TODO: somevar
        // TODO: low
        // sb.append("\" somevar=\"");
        // sb.append(somevar);
        sb.append("\" >\n");

        for (int i = 0; i < getOutputArcs().size(); i++) {
            sb.append(getOutputArcs().get(i).toString());
            sb.append("\n");
        }

        sb.append("    </Transition>\n");

        return sb.toString();
    }
}