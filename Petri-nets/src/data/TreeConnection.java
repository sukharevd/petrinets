package data;

import java.util.Vector;

public class TreeConnection {

    Vector<String> vhod = new Vector<String>();

    Vector<String> vuhod = new Vector<String>();

    Vector<String> napryam = new Vector<String>();

    public void addVhod(int id) {
        vhod.addElement(Integer.toString(id));
    }

    public void addVuhod(int id) {
        vuhod.addElement(Integer.toString(id));
    }

    public int getColVhod() {
        return vhod.size();
    }

    public int getElementVhod(int i) {
        return Integer.parseInt((String) vhod.elementAt(i));
    }

    public int getElementVuhod(int i) {
        return Integer.parseInt((String) vuhod.elementAt(i));
    }

    public void addNapryam(int id) {
        napryam.addElement(Integer.toString(id));
    }

    public int getNapryam(int i) {
        return Integer.parseInt((String) napryam.elementAt(i));
    }
}
