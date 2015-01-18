/*
    Copyright (C)  2009  Sukharev Dmitriy, Dzyuban Yuriy, Vixen Tael.
    
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

/**
 * 
 */
package data.generators;

import java.util.ArrayList;
import java.util.Random;

/**
 * Linear congruent generator, it contains methods for generation random values. (???????)
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class LCGenerator implements Generator {
    private double prevValue;

    private double a;

    private double c;

    private double d;

    public LCGenerator() {
        a = Math.pow(5, 17);
        c = Math.pow(3, 3);
        d = Math.pow(2, 32);
        prevValue = (double) new Random().nextInt((int)d);
    }

    public LCGenerator(double a, double c, double d, double w0) {
        this.a = a;
        this.c = c;
        this.d = d;
        this.prevValue = w0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see lab1cm.Generator#generateList()
     */
    @Override
    public ArrayList<Double> generateList(int quantity) {
        ArrayList<Double> list = new ArrayList<Double>();
        for (int i = 0; i < quantity; i++) {
            prevValue = (a * prevValue + c) % d;
            list.add(prevValue);
        }
        
        for (int i=0 ; i < quantity; i++) {
            list.set(i, list.get(i) / d);
        }
        return list;
    }


    /*
     * (non-Javadoc)
     * 
     * @see lab1cm.Generator#generateValue()
     */
    @Override
    public Double generateValue() {        
        double res;
        res = (a * prevValue + c) % d;
        prevValue = res;
                
        return res / d;
    }
    
    public double getB() {
        return 1.0;
    }

}
