package data.generators;

import java.util.ArrayList;

public class ErlangGenerator implements Generator {
    private ArrayList<Double> lkgValues;

    private ArrayList<Double> values;

    private int curIndex;

    private double lyamda;

    private int k;

    private double b = 0.0;
    
    private Generator kGen;

    public ErlangGenerator() {
        this.lyamda = 89.0;
        this.k = 7;
        this.curIndex = 0;
        this.values = new ArrayList<Double>();

        double a = Math.pow(5, 17);
        double c = Math.pow(3, 3);
        double d = Math.pow(2, 32);
        double w0 = 428.0;// (double) new Random().nextInt((int)d);
        //int quantity = 10000; // TODO: 3000?

        this.kGen = new LCGenerator(a, c, d, w0);
        //int lkgQuantity = (quantity + 1) * k;

        //this.lkgValues = kGen.generateList(lkgQuantity);
        //this.lkgValues = kGen.generateList();

    }

    public ErlangGenerator(ArrayList<Double> srcvals, double lyamda, int k) {
        this.lkgValues = srcvals;
        this.lyamda = lyamda;
        this.k = k;
        this.values = new ArrayList<Double>();
    }

//    @Override
//    public ArrayList<Double> generateList(int quantity) {
//        double sum;
//        for (; curIndex < quantity; curIndex++) {
//            sum = 0.0;
//            for (int j = curIndex * k + 1; j < (curIndex + 1) * k + 1; j++) {
//                sum += Math.log(lkgValues.get(j));
//            }
//            values.add(curIndex, sum / (-lyamda * k));
//            if (values.get(curIndex) > b) {
//                b = values.get(curIndex);
//            }
//        }
//
//        return values;
//    }
    
//    @Override
//    public ArrayList<Double> generateList() {
//        double sum = 0.0;
//        for (int j = 0; j < k; j++) {
//            sum += Math.log(lkgValues.get(j));
//        }
//        values.set(0, sum / (-lyamda * k));
//        if (values.get(0) > b) {
//            b = values.get(0);
//        }
//
//        return values;
//    }

//    @Override
//    public Double generateValue() {
//        double sum = 0.0;
//        for (int j = curIndex * k + 1; j < (curIndex + 1) * k + 1; j++) {
//            sum += Math.log(lkgValues.get(j));
//        }
//        values.add(curIndex, sum / (-lyamda * k));
//        if (values.get(curIndex) > b) {
//            b = values.get(curIndex);
//        }
//
//        double res = values.get(curIndex);
//        curIndex++;
//
//        return res;
//    }
    
    @Override
    public Double generateValue() {
        double sum = 0.0;
        for (int j = 0; j < k; j++) {
            sum += Math.log(this.kGen.generateValue());
        }
        values.add(0, sum / (-lyamda * k));
        if (values.get(0) > b) {
            b = values.get(0);
        }

        double res = values.get(0);
        return res;
    }

    public double getB() {
        return b;
    }
}
