package data.generators;

import java.util.ArrayList;

public class HyperExpGenerator implements Generator {
    private ArrayList<Double> lkgValues;

    private ArrayList<Double> values;

    private int curIndex;

    private double lyambda;

    private int g;

    private double b = 0.0;

    public HyperExpGenerator() {
        this.lkgValues = null;
        this.lyambda = 2.0;
        this.g = 2;

        this.curIndex = 0;
        this.values = new ArrayList<Double>();
    }

    public HyperExpGenerator(ArrayList<Double> srcvals, double lyambda, int g) {
        this.lkgValues = srcvals;
        this.lyambda = lyambda;
        this.g = g;

        this.curIndex = 0;
        this.values = new ArrayList<Double>();
    }

    @Override
    public ArrayList<Double> generateList(int quantity) {
        double alfa;
        double phi = (g + 1 - Math.sqrt(g * g - 1.)) / (2. * (g + 2.));

        for (int j = 0; curIndex < quantity; curIndex++, j += 2) { // TODO: it
            // is bad((
            if (lkgValues.get(j) <= phi)
                alfa = 2 * phi * lyambda;
            else
                alfa = 2 * (1 - phi) * lyambda;

            values.add(-Math.log(lkgValues.get(j + 1)) / alfa);

            if (values.get(curIndex) > b) {
                b = values.get(curIndex);
            }
        }

        return values;
    }

    @Override
    public Double generateValue() {
        double alfa;
        double phi = (g + 1 - Math.sqrt(g * g - 1.)) / (2. * (g + 2.));

        if (lkgValues.get(curIndex * 2) <= phi) {
            alfa = 2 * phi * lyambda;
        } else {
            alfa = 2 * (1 - phi) * lyambda;
        }

        values.add(-Math.log(lkgValues.get(curIndex * 2 + 1)) / alfa);

        if (values.get(curIndex) > b) {
            b = values.get(curIndex);
        }

        Double res = values.get(curIndex);
        curIndex++;

        return res;
    }

    public double getB() {
        return b;
    }
}
