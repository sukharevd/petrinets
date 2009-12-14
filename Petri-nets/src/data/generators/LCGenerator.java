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
