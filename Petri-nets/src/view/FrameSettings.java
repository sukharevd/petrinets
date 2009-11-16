/**
 * 
 */
package view;

import java.awt.Color;

/**
 * Utility class for painting, Contains features of view.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class FrameSettings {
    // TODO: organize all these:
    
    public static Color elementsColor() {
        return Color.black;
    }
    
    //yellow
    //public Color stdgradcolor1() {return new Color(255,255,153);
    //khameleon
    public static Color stdgradcolor1() {
        return new Color(78,255,102);
    }
    //green
    //public Color stdgradcolor1() {return new Color(204,255,204);
    //violet green
    //public Color stdgradcolor1() {return new Color(204,204,255);
    //dima also violet green
    //public Color stdgradcolor1() {return new Color(200,221,242);
    
    //darkorange
    //public Color stdgradcolor2() {return new Color(255,78,0);
    //lightorange
    //public Color stdgradcolor2() {return new Color(255,162,0);
    //blue
    //public Color stdgradcolor2() {return new Color(51,204,255);
    //jabablue
    //--------public Color stdgradcolor2() {return new Color(153,204,204);
    //jabablue2
    //public Color stdgradcolor2() {return new Color(153,204,204);
    //blue
    public static Color stdgradcolor2() {
        return new Color(102,204,255);
    }
    
    //lightblue
    public static Color actgradcolor1() {
        return new Color(131,219,255);
    }

    //orange
//    public static Color actgradcolor1() {
//        return new Color(255,153,0);
//    }
    //violet
    //-------public Color actgradcolor2() {return new Color(87,55,204);
    //darkred
    public static Color actgradcolor2() {
        return new Color(255,78,0);
    }
    
    //lightblue
    public static Color activeElementsColor() {
        return new Color(0,246,255);
    }
    //red
    //public Color activeElementsColor() {return new Color(204,0,255);

    //buryuzovyy
    //public Color inputArcsColor() {return Color.getHSBColor(0.5f, 1f, 0.6f);
    //orange
    //public Color inputArcsColor() {return new Color(255,102,0);
    //yellow
    //public Color inputArcsColor() {return new Color(255,162,0);
    //green
    public static Color inputArcsColor() {
        return new Color(102,204,0);
    }
    
    public static Color gridColor() {
        return Color.getHSBColor(0f, 0f, 0.85f);
    }
    
    public static Color addedArcColor() {
        return Color.getHSBColor(0f, 0f, 0.85f);
    }
    
    public static Color arcArrowColor() {
        //return new Color(154,9,9); //darkred
        return new Color(0,204,153); //greeny
    }

    public static int elementHeight() {
        return 32;
    }

    public static int transitionWidth() {
        return 10;
    }

    public static int placeTokensTextOffset() {
        return 3;
    }

    // public static int arcWidth() {return 2;
    public static int arcFindingDistance() {
        return 3;
    }
}
