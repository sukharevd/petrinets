/**
 * This package contains Invokers, Commands and Stack of Commands
 * for Undo/Redo operations.
 */
package commands;

import data.Transition;

/**
 * Provides executing of Undo/Redo operations for changing of Transition.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class ChangeTransitionValuesInvoker {

    private Transition transition;

    private double lyambda1;

    private double lyambda2;

    private double g1;

    private double g2;

    public ChangeTransitionValuesInvoker(Transition t, double lyambda, double g) {
        this.transition = t;
        this.g1 = t.getG();
        this.lyambda1 = t.getLyambda();
        this.g2 = g;
        this.lyambda2 = lyambda;
    }

    public void changeTransitionValues() {
        transition.setG(g2);
        transition.setLyambda(lyambda2);
    }

    public void undoChangeTransitionValues() {
        transition.setG(g1);
        transition.setLyambda(lyambda1);
    }

}
