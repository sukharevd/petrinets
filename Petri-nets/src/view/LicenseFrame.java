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
 * 
 */
package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Frame for displaying information about license of Petri net Emulator.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class LicenseFrame extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 126837745324198669L;

    private String license = "    Petri nets Emulator\n"
    + "    Copyright (C) 2009 Sukharev Dmitriy, Dzyuban Yuriy, Vixen Tael\n"
    + "\n"
    + "    This program is free software: you can redistribute it and/or modify\n"
    + "    it under the terms of the GNU General Public License as published by\n"
    + "    the Free Software Foundation, either version 3 of the License, or\n"
    + "    (at your option) any later version.\n"
    + "\n"
    + "    This program is distributed in the hope that it will be useful,\n"
    + "    but WITHOUT ANY WARRANTY; without even the implied warranty of\n"
    + "    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n"
    + "    GNU General Public License for more details.\n"
    + "\n"
    + "    You should have received a copy of the GNU General Public License\n"
    + "    along with this program.  If not, see <http://www.gnu.org/licenses/>.\n";

    /**
     * Start width of the frame.
     */
    private int fWidth = 600;

    /**
     * Start height of the frame.
     */
    private int fHeight = 350;

    public void createAndShowGUI() {
        setTitle("Petri nets Emulator license notice");
        setSize(fWidth, fHeight);
        setMinimumSize(new Dimension(fWidth, fHeight));
        setResizable(false);

        JTextArea text = new JTextArea(license);
        text.setFont(new Font("monospaced", 0, 12));
        text.setEditable(false);
        text.setBackground(this.getBackground());

        JScrollPane scroll = new JScrollPane(text);
        add(scroll, "North");
        
        JButton ok = new JButton();
        final JFrame curFrame = this;
        ok.setAction(new AbstractAction() {
            
            /**
             * 
             */
            private static final long serialVersionUID = 1L;
            
            

            @Override
            public void actionPerformed(ActionEvent e) {
                curFrame.dispose();
            }
        });
        ok.setText("Ok");
        //ok.setSize(300, 90);
        add(ok);
        
        setVisible(true);

    }
}
