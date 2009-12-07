package data.generators;

import java.util.ArrayList;

public class PoissonGenerator implements Generator {
    private ArrayList<Double> lkgValues;

    private ArrayList<Double> values;

    private int curIndex;

    private double lyamda;

    private double b = 0.0;
    private Generator kGen;

    public PoissonGenerator() {
        this.lkgValues = null;
        this.lyamda = 3.0;
        this.curIndex = 0;
        this.values = new ArrayList<Double>();
        
        double a = Math.pow(5, 17);
        double c = Math.pow(3, 3);
        double d = Math.pow(2, 32);
        double w0 = 428.0;
        //int quantity = 10000; // TODO: 3000?
        this.kGen = new LCGenerator(a, c, d, w0);
        //this.lkgValues = kGen.generateList(quantity);

        //this.lkgValues = kGen.generateList();
    }

    public PoissonGenerator(ArrayList<Double> newvals, double lyamda) {
        this.lkgValues = newvals;
        this.lyamda = lyamda;
        this.curIndex = 0;
        this.values = new ArrayList<Double>();
    }

//    @Override
//    public ArrayList<Double> generateList(int quantity) {
//        for (; curIndex < quantity; curIndex++) {
//            values.add(curIndex, -Math.log(1.0 - lkgValues.get(curIndex))
//                    / lyamda);
//            if (values.get(curIndex) > b) {
//                b = values.get(curIndex);
//            }
//        }
//
//        return values;
//    }

//    @Override
//    public ArrayList<Double> generateList() {
//    	values.add(0, -Math.log(1.0 - lkgValues.get(0))
//                / lyamda);
//        if (values.get(0) > b) {
//            b = values.get(0);
//        }
//
//        return values;
//    }
    
//    @Override
//    public Double generateValue() {
//        values.add(curIndex, -Math.log(1.0 - lkgValues.get(curIndex)) / lyamda);
//        if (values.get(curIndex) > b) {
//            b = values.get(curIndex);
//        }
//
//        Double res = values.get(curIndex);
//        curIndex++;
//
//        return res;
//    }
    
    @Override
    public Double generateValue() {
    	values.add(0, -Math.log(1.0 - this.kGen.generateValue())
                / lyamda);
        if (values.get(0) > b) {
            b = values.get(0);
        }

        Double res = values.get(0);
        return res;
    }

    public double getB() {
        return b;
    }
}
