/**
 * 
 */
package data.generators;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Admin
 * 
 */
public class LCGenerator implements Generator {
    private ArrayList<Double> values;

    private double a;

    private double c;

    private double d;

    private int curIndex;

    //private int maxCapacity = 5000;

    public LCGenerator() {
        a = Math.pow(5, 15);//214013;
        c = Math.pow(7, 16);//2531011;
        d = Math.pow(2, 32);
        values = new ArrayList<Double>(/*maxCapacity*/);
        values.add((double) new Random().nextInt((int)d));
        curIndex = 0;
    }

    public LCGenerator(double a, double c, double d, double w0) {
        this.a = a;
        this.c = c;
        this.d = d;
        values = new ArrayList<Double>(/*maxCapacity*/);
        values.add(w0);
        values.add(w0);
        curIndex = 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see lab1cm.Generator#generateList()
     */
//    @Override
//    public ArrayList<Double> generateList(int quantity) {
//        for (; curIndex < quantity - 1; curIndex++) {
//            values.add(((a * values.get(curIndex) + c) % d));
////            values.add((((a * values.get(curIndex) + c) / d)%1));
//        }
//               
//        for (int i=0 ; i < quantity; i++) {
//            values.set(i, values.get(i) / d);
//        }
//        return values;
//    }
    
//    @Override
//    //Если надо эррэйлист
//    public ArrayList<Double> generateList() {
//        //Настя: генерируем два новых значения на основе одного из старых
//    	
//    	//сместила влево предыдущие 2 значения. первое - потеряла, 
//    	//второе стало основой следующего значения 
//    	values.set(0, values.get(1));
//    	//на основе второго сгенерировала новое
//    	values.set(1, ((a * values.get(0) + c) % d));
//    	values.set(1, values.get(1) / d);
//    	//сместила влево новое
//    	values.set(0, values.get(1));
//    	//на основе оставшегося сгенерировала новое значение
//        values.set(1, ((a * values.get(0) + c) % d));
//        values.set(1, values.get(1) / d);
//        //фух. Итого - в эррейлисте 2 новых значения ЛКГ (почему два? - надо для ГиперЭкспон)
//        return values;
//    }


    /*
     * (non-Javadoc)
     * 
     * @see lab1cm.Generator#generateValue()
     */
    @Override
//    public Double generateValue() {
//        double res = ((a * values.get(curIndex) + c) % d);
//        values.add(res);
//        curIndex++;
//        res /= d;
//        
//        return res;
//    }
    
    //Настя: Если надо 1 значение
    public Double generateValue() {
        values.set(0, ((a * values.get(0) + c) % d));
        values.set(0, values.get(0) / d);
        double res = values.get(0);
        res /= d;       
        return res;
    }
    
    public double getB() {
        return 1.0;
    }

}
