package data.generators;

import java.util.ArrayList;

//Ravnomernyi
public class UniformGenerator implements Generator {
    private ArrayList<Double> lkgValues;

    private ArrayList<Double> values;

    private int curIndex;

    private double lyamda;

    private double b = 0.0;
    private Generator kGen;

    public UniformGenerator() {
        this.lyamda = 2.0;

        this.curIndex = 0;
        this.values = new ArrayList<Double>();
        
        double a = Math.pow(5, 17);
        double c = Math.pow(3, 3);
        double d = Math.pow(2, 32);
        double w0 = 428.0;
        //int quantity = 10000;
        
        kGen = new LCGenerator(a, c, d, w0);
        //this.lkgValues = kGen.generateList(quantity);
        //this.lkgValues = kGen.generateList();
        
    }

    public UniformGenerator(ArrayList<Double> newvals, double lyamda) {
        this.lkgValues = newvals;
        this.lyamda = lyamda;

        this.curIndex = 0;
        this.values = new ArrayList<Double>();
    }

//    @Override
//    public ArrayList<Double> generateList(int quantity) {
//        for (int curIndex = 0; curIndex < quantity; curIndex++) {
//            values.add(curIndex, (1.0 / lyamda) * lkgValues.get(curIndex) * 2);
//            if (values.get(curIndex) > b) {
//                b = values.get(curIndex);
//            }
//        }
//        return values;
//    }
//
//    @Override
//    public ArrayList<Double> generateList() {
//        values.add(0, (1.0 / lyamda) * lkgValues.get(0) * 2);
//        if (values.get(0) > b) {
//            b = values.get(0);
//        }
//        return values;
//    }
    
//    @Override
//    public Double generateValue() {
//
//        values.add(curIndex, (1.0 / lyamda) * lkgValues.get(curIndex) * 2);
//        if (values.get(curIndex) > b) {
//            b = values.get(curIndex);
//        }
//
//        Double res = values.get(curIndex);
//        curIndex++;
//        return res;
//    }

    @Override
    public Double generateValue() {

        values.add(0, (1.0 / lyamda) * this.kGen.generateValue() * 2);
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
