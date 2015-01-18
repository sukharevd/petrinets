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

import java.util.HashMap;
import java.util.Map;

/**
 * Pool of all generators of application, it contains maps for Poisson, Erlang,
 * HyperExp and Uniform generators.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class GeneratorsPool {
    private LCGenerator lcg;

    private Map<Double, PoissonGenerator> poisson;

    private Map<Double, HyperExpGenerator> hyper;

    private Map<Double, UniformGenerator> uniform;

    private Map<Double, ErlangGenerator> erlang;

    public GeneratorsPool() {
        lcg = new LCGenerator();
        poisson = new HashMap<Double, PoissonGenerator>();
        hyper = new HashMap<Double, HyperExpGenerator>();
        uniform = new HashMap<Double, UniformGenerator>();
        erlang = new HashMap<Double, ErlangGenerator>();
    }

    public Generator chooseGenerator(final Double g, final Double lyambda) {
        Generator generator = null;
        if (g == 1.0) {
            // generator = poisson;
            if (!poisson.containsKey(lyambda)) {
                poisson.put(lyambda, new PoissonGenerator(lyambda));
            }
            generator = poisson.get(lyambda);
        } else {
            if ((g >= 0.33) && (g <= 0.34)) {
                if (!uniform.containsKey(lyambda)) {
                    uniform.put(lyambda, new UniformGenerator(lyambda));
                }
                generator = uniform.get(lyambda);
            } else {
                if (g > 1.0) {
                    if (!hyper.containsKey(lyambda)) {
                        hyper.put(lyambda, new HyperExpGenerator(lyambda, g));
                    }
                    generator = hyper.get(lyambda);
                } else {
                    if (g < 1.0) {
                        if (!erlang.containsKey(lyambda)) {
                            erlang.put(lyambda, new ErlangGenerator(lyambda,
                                    1.0 / g));
                        }
                        generator = erlang.get(lyambda);
                    }
                }
            }
        }

        return generator;
    }

    public Generator getLCG() {
        return lcg;
    }
}
