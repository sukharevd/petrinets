package data.generators;

import java.util.ArrayList;

/**
 * Contains methods for generation of transitions time when it works.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public interface Generator {
    public Double generateValue();

    public ArrayList<Double> generateList(int quantity);

    public double getB();
}