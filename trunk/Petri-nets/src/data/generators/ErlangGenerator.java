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

package data.generators;

import java.util.ArrayList;

/**
 * Contains methods for transitions time generation when they works by Erlang
 * low.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class ErlangGenerator implements Generator {

    private Generator lcgGen;

    private double lyambda;

    private double k;

    private double b = 0.0;

    public ErlangGenerator() {
        this.lyambda = 89.0;
        this.k = 2.0;
        this.lcgGen = new LCGenerator();
    }

    public ErlangGenerator(double lyamda, double k) {
        this.lyambda = lyamda;
        this.k = k;
        this.lcgGen = new LCGenerator();
    }
    
    public ErlangGenerator(double lyamda, double k, Generator lcgGen) {
        this.lyambda = lyamda;
        this.k = k;
        this.lcgGen = lcgGen;
    }

    @Override
    public ArrayList<Double> generateList(int quantity) {
        ArrayList<Double> list = new ArrayList<Double>();
        double sum;

        for (int i = 0; i < quantity; i++) {
            sum = 0.0;
            for (int j = 1; j < k + 1; j++) {
                sum += Math.log(lcgGen.generateValue());
            }
            list.add(i, sum / (-lyambda * k));
            if (list.get(i) > b) {
                b = list.get(i);
            }
        }

        return list;
    }

    @Override
    public Double generateValue() {
        double sum = 0.0;

        for (int j = 1; j < k + 1; j++) {
            sum += Math.log(lcgGen.generateValue());
        }
        double res = sum / (-lyambda * k);
        if (res > b) {
            b = res;
        }

        return res;
    }

    @Override
    public double getB() {
        return b;
    }
}
