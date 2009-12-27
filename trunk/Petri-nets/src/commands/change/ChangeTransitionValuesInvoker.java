/*
    Copyright (C)  2009  Sukharev Dmitriy, Dzyuban Yuriy, Voitova Anastasiia.
    
    This file is part of Petri nets Emulator.
    
    Petri nets Emulator is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    
    Petri nets Emulator is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    
    You should have received a copy of the GNU General Public License
    along with Petri nets Emulator. If not, see <http://www.gnu.org/licenses/>.
*/

/**
 * This package contains Invokers, Commands and Stack of Commands
 * for Undo/Redo operations.
 */
package commands.change;

import data.elements.Transition;

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
    
    private double r1;

    private double r2;

    public ChangeTransitionValuesInvoker(Transition t, double lyambda, double g, double r) {
        this.transition = t;
        this.g1 = t.getG();
        this.r1 = t.getR();
        this.lyambda1 = t.getLyambda();
        this.g2 = g;
        this.r2 = r;
        this.lyambda2 = lyambda;
    }

    public void changeTransitionValues() {
        transition.setG(g2);
        transition.setR(r2);
        transition.setLyambda(lyambda2);
    }

    public void undoChangeTransitionValues() {
        transition.setG(g1);
        transition.setR(r1);
        transition.setLyambda(lyambda1);
    }

}
