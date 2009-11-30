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
    private ErlangGenerator erlang;
    //...
    
    public GeneratorsPool() {
        lcg = new LCGenerator();
        erlang = new ErlangGenerator();
    }
    
    public Generator chooseGenerator(final Double g) {
        Generator generator;
        if (g == 1.0) {
            generator = lcg;
        } else {
            generator = erlang;
        }
        
        return generator;
    }
    
    public Generator getLCG() {
        return lcg;
    }
}
