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
