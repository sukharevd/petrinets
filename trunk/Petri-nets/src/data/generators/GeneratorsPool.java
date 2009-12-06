/**
 * 
 */
package data.generators;

/**
 * @author Admin
 *
 */
public class GeneratorsPool {
    private LCGenerator lcg;
    private PoissonGenerator poisson;
    private HyperExpGenerator hyper;
    private UniformGenerator uniform;
    private ErlangGenerator erlang;
    
    public GeneratorsPool() {
        lcg = new LCGenerator();
        poisson = new PoissonGenerator();
        hyper = new HyperExpGenerator();
        uniform = new UniformGenerator();
        erlang = new ErlangGenerator();        
    }
    
    public Generator chooseGenerator(final Double g) {
        Generator generator = null;
        if (g == 1.0) {
            generator = poisson;
        } else {
            if ((g >= 0.33)&&(g <= 0.34)) {
                generator = uniform;
            } else {
                if (g > 1.0) {
                    generator = hyper;
                } else {
                    if (g < 1.0) {
                        generator = erlang;
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
