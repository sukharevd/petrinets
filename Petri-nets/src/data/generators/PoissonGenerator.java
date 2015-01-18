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

package data.generators;

import java.util.ArrayList;

/**
 * Contains methods for generation of transitions time when it works by Poisson
 * low.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class PoissonGenerator implements Generator {
    private Generator lcgGen;

    private double lyambda;

    private double b;

    public PoissonGenerator() {
        this.lyambda = 3.0;
        this.lcgGen = new LCGenerator();
        this.b = 0.0;
    }

    public PoissonGenerator(double lyambda) {
        this.lyambda = lyambda;
        this.lcgGen = new LCGenerator();
        this.b = 0.0;
    }

    public PoissonGenerator(double lyambda, LCGenerator lcgGen) {
        this.lyambda = lyambda;
        this.lcgGen = lcgGen;
        this.b = 0.0;
    }

    @Override
    public ArrayList<Double> generateList(int quantity) {
        ArrayList<Double> list = new ArrayList<Double>();
        double last;

        for (int i = 0; i < quantity; i++) {
            last = -Math.log(1.0 - lcgGen.generateValue()) / lyambda;
            list.add(last);
            if (last > b) {
                b = last;
            }
        }

        return list;
    }

    @Override
    public Double generateValue() {
        double res;
        res = -Math.log(1.0 - lcgGen.generateValue()) / lyambda;
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
