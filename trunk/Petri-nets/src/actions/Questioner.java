/**
 * 
 */
package actions;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Questioner, which asks user about some parameters by massage with text field.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class Questioner {
    public static Integer askInt(JFrame mainFrame, String title,
            String message, String initialSelectionValue, Object[] selections) {
        Integer number = null;

        while (true) {
            String resStr = (String) JOptionPane.showInputDialog(mainFrame,
                    message, title, JOptionPane.PLAIN_MESSAGE, null, null,
                    initialSelectionValue);
            if (resStr == null) {
                break;
            }

            try {
                number = Integer.valueOf(resStr);
                break;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainFrame,
                        "Wrong number. Repeat please.", "Error of inputting",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        return number;
    }

    public static Double askDouble(JFrame mainFrame, String title,
            String message, String initialSelectionValue, Object[] selections) {
        Double number = null;

        while (true) {
            String resStr = (String) JOptionPane.showInputDialog(mainFrame,
                    message, title, JOptionPane.PLAIN_MESSAGE, null, null,
                    initialSelectionValue);
            if (resStr == null) {
                break;
            }

            try {
                number = Double.valueOf(resStr);
                break;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(mainFrame,
                        "Wrong number. Repeat please.", "Error of inputting",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        return number;
    }
}
