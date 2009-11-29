package data.generators;

import java.util.ArrayList;

//Ravnomernyi
public class UniformGenerator implements Generator {
    private ArrayList<Double> lkgValues;

    private ArrayList<Double> values;

    private int curIndex;

    private double lyamda;

    private double b = 0.0;

    public UniformGenerator() {
        this.lkgValues = null;
        this.lyamda = 2.0;

        this.curIndex = 0;
        this.values = new ArrayList<Double>();
    }

    public UniformGenerator(ArrayList<Double> newvals, double lyamda) {
        this.lkgValues = newvals;
        this.lyamda = lyamda;

        this.curIndex = 0;
        this.values = new ArrayList<Double>();
    }

    @Override
    public ArrayList<Double> generateList(int quantity) {
        for (int curIndex = 0; curIndex < quantity; curIndex++) {
            values.add(curIndex, (1.0 / lyamda) * lkgValues.get(curIndex) * 2);
            if (values.get(curIndex) > b) {
                b = values.get(curIndex);
            }
        }
        return values;
    }

    @Override
    public Double generateValue() {

        values.add(curIndex, (1.0 / lyamda) * lkgValues.get(curIndex) * 2);
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
