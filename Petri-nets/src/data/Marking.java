// TODO: test this code.
package data;

import java.util.ArrayList;

public class Marking {

    private ArrayList<Integer> list;

    // TODO: are they necessary?
    private ArrayList<Marking> childMarkings;

    /**
     * 
     * @param list
     */
    public Marking(ArrayList<Integer> list) {
        this.list = list;
    }

    /**
     * 
     * @param list
     * @param childMarkings
     */
    public Marking(ArrayList<Integer> list, ArrayList<Marking> childMarkings) {
        this.list = list;
        this.childMarkings = childMarkings;
    }

    /**
     * @return the list
     */
    public final ArrayList<Integer> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public final void setList(ArrayList<Integer> list) {
        this.list = list;
    }

    /**
     * @return the childMarkings
     */
    public final ArrayList<Marking> getChildMarkings() {
        return childMarkings;
    }

    /**
     * @param childMarkings the childMarkings to set
     */
    public final void setChildMarkings(ArrayList<Marking> childMarkings) {
        this.childMarkings = childMarkings;
    }

    public Integer[] getIntegerArray() {
        // TODO: check:
        return (Integer[])list.toArray();
    }

    /**
     * 
     * @param index
     */
    public Integer getAt(int index) {
        return list.get(index);
    }

    /**
     * 
     * @param index
     * @param value
     */
    public void setAt(int index, Integer value) {
        list.set(index, value);
    }

    public String toString() {
        StringBuilder strBuilder = new StringBuilder("");
        
        for (int i = 0; i < list.size(); i++) {
            strBuilder.append(list.get(i));
            strBuilder.append("\t");
        }
        
        return strBuilder.toString();
    }

}