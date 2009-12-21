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

    public static Color elementsColor() {
        return Color.black;
    }

    public static Color stdgradcolor1() {
        return new Color(255, 255, 153);
    }

    public static Color stdgradcolor2() {
        return new Color(255, 162, 0);
    }

    public static Color actgradcolor1() {
        return new Color(255, 153, 0);
    }

    public static Color actgradcolor2() {
        return new Color(255, 78, 0);
    }

    public static Color activeElementsColor() {
        return new Color(204, 0, 255);
    }

    public static Color inputArcsColor() {
        return new Color(255, 162, 0);
    }

    public static Color gridColor() {
        return Color.getHSBColor(0f, 0f, 0.85f);
    }

    public static Color addedArcColor() {
        return Color.getHSBColor(0f, 0f, 0.85f);
    }

    public static Color arcArrowColor() {
        return new Color(154, 9, 9); // greeny
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

    public static int arcFindingDistance() {
        return 3;
    }
}
