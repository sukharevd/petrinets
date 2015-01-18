/*
    Copyright (C)  2009  Sukharev Dmitriy, Dzyuban Yuriy, Vixen Tael.
    
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
 * This package contains listeners for mouse, keyboard, scroll motion and
 * window, actions for menu and toolbar buttons.
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

    /**
     * Asks about an {@link Integer} value by message box.
     * 
     * @param mainFrame
     *            frame of application
     * @param title
     *            title of message
     * @param message
     *            description message
     * @param initialSelectionValue
     *            initial value
     * @param selections
     *            selections values
     * @return inputed {@link Integer} value.
     */
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

    /**
     * Asks about an {@link Double} value by message box.
     * 
     * @param mainFrame
     *            frame of application
     * @param title
     *            title of message
     * @param message
     *            description message
     * @param initialSelectionValue
     *            initial value
     * @param selections
     *            selections values
     * @return inputed {@link Double} value.
     */
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
