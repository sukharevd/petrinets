// TODO: test this code.
package data;

import java.util.ArrayList;

public class Marking {
    
    private int no;

    private ArrayList<Integer> list;

    /**
     * 
     * @param list
     */
    public Marking(int no, ArrayList<Integer> list) {
        this.no = no;
        this.list = list;
    }
    
    /**
     * 
     * @param array
     */
    public Marking(int no, int[] array) {
        this.no = no;
        this.list = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {
            this.list.add(array[i]);
        }
    }

    /**
     * @return the no
     */
    public final int getNo() {
        return no;
    }

    /**
     * @param no the no to set
     */
    public final void setNo(int no) {
        this.no = no;
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
    
    public boolean equals(Marking m) {
        boolean is_equals = false;
        int sum = 0;
        
        if (this.list.size() == m.list.size()) {
            for (int i = 0; i < this.list.size(); i++) {
                sum +=  Math.abs(this.list.get(i) - m.list.get(i));
            }
        }
        
        if (sum == 0) {
            is_equals = true;
        }
        
        return is_equals;
    }

    public String toString() {
        if (list.size() == 0) {
            return "";
        }
        
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("M");
        strBuilder.append(no);
        strBuilder.append("(");
        
        strBuilder.append(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            strBuilder.append(",");
            strBuilder.append(list.get(i));
        }
        strBuilder.append(")");
        return strBuilder.toString();
    }

}