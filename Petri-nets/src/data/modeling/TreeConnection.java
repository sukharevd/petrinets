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

package data.modeling;

import java.util.Vector;

/**
 * Class shows what position is connected to another one and how.
 * 
 * @author Jacky
 */
public class TreeConnection {
    /**
     * Holds real numbers of entrances to positions
     */
    Vector<String> vhod = new Vector<String>();

    /**
     * Holds real numbers of exits of positions
     */
    Vector<String> vuhod = new Vector<String>();

    /**
     * Holds from to (-1) or (1)
     */
    Vector<String> napryam = new Vector<String>();

    /**
     * Holds numbers of entrances of positions (for destination)
     */
    Vector<String> nameVhod = new Vector<String>();

    /**
     * Holds real numbers of exits of positions (for destination)
     */
    Vector<String> nameVihod = new Vector<String>();

    /**
     * 
     * @return name of entrance of positions
     */
    public int getNameVhod() {
        return Integer.parseInt((String) nameVhod.get(0));
    }

    /**
     * @param nameVhod
     *            sets the name of entrance
     */
    public void setNameVhod(Vector<String> nameVhod) {
        this.nameVhod = nameVhod;
    }

    /**
     * 
     * @return name of exits of positions
     */
    public int getNameVuhod() {
        return Integer.parseInt((String) nameVihod.get(0));
    }

    /**
     * @param nameVihod
     *            sets the name of exit
     */
    public void setNameVuhod(Vector<String> nameVihod) {
        this.nameVihod = nameVihod;
    }

    /**
     * @param id
     *            number of entrance
     */
    public void addNameVhod(int id) {
        nameVhod.addElement(Integer.toString(id));
    }

    /**
     * @param id
     *            number of exit
     */
    public void addNameVuhod(int id) {
        nameVihod.addElement(Integer.toString(id));
    }

    /**
     * @param id
     *            number of real entrance
     */
    public void addVhod(int id) {
        vhod.addElement(Integer.toString(id));
    }

    /**
     * @param id
     *            number of real exit
     */
    public void addVuhod(int id) {
        vuhod.addElement(Integer.toString(id));
    }

    /**
     * 
     * @return number of current entrances of positions
     */
    public int getColVhod() {
        return vhod.size();
    }

    /**
     * 
     * @param i
     *            number
     * @return number of entrance of positions
     */
    public int getElementVhod(int i) {
        return Integer.parseInt((String) vhod.elementAt(i));
    }

    /**
     * 
     * @param i
     *            number
     * @return number of exits of positions
     */
    public int getElementVuhod(int i) {
        return Integer.parseInt((String) vuhod.elementAt(i));
    }

    /**
     * @param id
     *            (-1) or (1) from to.
     */
    public void addNapryam(int id) {
        napryam.addElement(Integer.toString(id));
    }

    /**
     * @param i
     * @return direction
     */
    public int getNapryam(int i) {
        return Integer.parseInt((String) napryam.elementAt(i));
    }
}