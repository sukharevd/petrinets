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
