package data.generators;

import java.util.ArrayList;
// TODO: fix ??????
/**
 * Contains methods for transitions time generation when they works by Hyper
 * expositional (??????) low.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class HyperExpGenerator implements Generator {
    private Generator lcgGen;

    private double lyambda;

    private double g;

    private double b = 0.0;

    public HyperExpGenerator() {
        this.lyambda = 25.0;
        this.g = 2.0;
        this.lcgGen = new LCGenerator();
    }

    public HyperExpGenerator(double lyamda, double g) {
        this.lyambda = lyamda;
        this.g = g;
        this.lcgGen = new LCGenerator();
    }

    public HyperExpGenerator(double lyamda, double g, Generator lcgGen) {
        this.lyambda = lyamda;
        this.g = g;
        this.lcgGen = lcgGen;
    }

    @Override
    public ArrayList<Double> generateList(int quantity) {
        ArrayList<Double> list = new ArrayList<Double>();

        double phi = (g + 1 - Math.sqrt(g * g - 1.)) / (2. * (g + 2.));
        double alfa;
        for (int i = 0; i < quantity; i++) {
            if (lcgGen.generateValue() <= phi) {
                alfa = 2 * phi * lyambda;
            } else {
                alfa = 2 * (1 - phi) * lyambda;
            }

            list.add(-Math.log(lcgGen.generateValue()) / alfa);

            if (list.get(i) > b) {
                b = list.get(i);
            }
        }
        return list;
    }

    @Override
    public Double generateValue() {
        double res;

        double phi = (g + 1 - Math.sqrt(g * g - 1)) / (2 * (g + 2));
        double alfa;
        if (lcgGen.generateValue() <= phi) {
            alfa = 2 * phi * lyambda;
        } else {
            alfa = 2 * (1 - phi) * lyambda;
        }

        res = -Math.log(lcgGen.generateValue()) / alfa;

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
