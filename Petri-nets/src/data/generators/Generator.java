package data.generators;

import java.util.ArrayList;

/**
 * Contains method of generation of time transition if it works.
 *
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 *
 */
public interface Generator {
    public Double generateValue();

    public ArrayList<Double> generateList(int quantity);
}