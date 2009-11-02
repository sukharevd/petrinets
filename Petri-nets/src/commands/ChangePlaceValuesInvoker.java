/**
 * This package contains Invokers, Commands and Stack of Commands
 * for Undo/Redo operations.
 */
package commands;

import data.Place;

/**
 * Provides executing of Undo/Redo operations for changing of Place.
 *
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class ChangePlaceValuesInvoker {
    private Place place;

    private int v1;

    private int v2;

    /**
     * @param place
     * @param numToken
     * @param data
     */
    public ChangePlaceValuesInvoker(Place place, int numToken) {
        super();
        this.place = place;
        this.v1 = place.getNumTokens();
        this.v2 = numToken;
    }

    public void changePlaceValues() {
        place.setNumTokens(v2);
    }

    public void undoChangePlaceValues() {
        place.setNumTokens(v1);
    }

}
