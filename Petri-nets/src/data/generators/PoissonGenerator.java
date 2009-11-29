package data.generators;

import java.util.ArrayList;

public class PoissonGenerator implements Generator {
    private ArrayList<Double> lkgValues;

    private ArrayList<Double> values;

    private int curIndex;

    private double lyamda;

    private double b = 0.;

    public PoissonGenerator() {
        this.lkgValues = null;
        this.lyamda = 2.0;
        this.curIndex = 0;
        this.values = new ArrayList<Double>();
    }

    public PoissonGenerator(ArrayList<Double> newvals, double lyamda) {
        this.lkgValues = newvals;
        this.lyamda = lyamda;
        this.curIndex = 0;
        this.values = new ArrayList<Double>();
    }

    @Override
    public ArrayList<Double> generateList(int quantity) {
        for (; curIndex < quantity; curIndex++) {
            values.add(curIndex, -Math.log(1.0 - lkgValues.get(curIndex))
                    / lyamda);
            if (values.get(curIndex) > b) {
                b = values.get(curIndex);
            }
        }

        return values;
    }

    @Override
    public Double generateValue() {
        values.add(curIndex, -Math.log(1.0 - lkgValues.get(curIndex)) / lyamda);
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
