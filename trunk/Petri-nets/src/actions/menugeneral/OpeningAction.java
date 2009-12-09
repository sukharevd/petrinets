package actions.menugeneral;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import data.Data;
import data.elements.Place;
import data.elements.Transition;
import data.modeling.EmulationManager;


/**
 * Action, which is occurred when user clicks "Open..." component, it saves
 * current chart (if user wants it), and opens a specified legacy chart from
 * xml-file.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class OpeningAction extends AbstractAction {

    /**
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = 5745683949359609280L;

    private Data data;

    private JFrame mainFrame;
    
    private EmulationManager emulator;

    /**
     * @param data
     */
    public OpeningAction(final Data data, final EmulationManager emulator, final JFrame mainFrame) {
        super();
        this.data = data;
        this.emulator = emulator;
        this.mainFrame = mainFrame;
    }

    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(final ActionEvent arg0) {
        if (data.isChanged() == true) {

            int opt = JOptionPane.showConfirmDialog(mainFrame,
                    "Do you want to save changes?", "Save",
                    JOptionPane.YES_NO_CANCEL_OPTION);

            if (opt == JOptionPane.OK_OPTION) {
                SavingAction.save(data, mainFrame);
                if (data.isChanged() == true) {
                    return;
                }
                JOptionPane.showMessageDialog(mainFrame,
                        "Current chart was saved successfully.\n"
                                + "Now choose file you want to open.", "Open",
                        JOptionPane.YES_NO_CANCEL_OPTION);

            }
            if (opt == JOptionPane.CANCEL_OPTION) {
                return;
            }

        }

        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(final File f) {
                return f.getName().toLowerCase().endsWith(".xml")
                        || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "XML-files (*.xml)";
            }
        });
        fc.setCurrentDirectory(new File("."));

        // In response to a button click:
        int returnVal = fc.showOpenDialog(mainFrame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String path = fc.getSelectedFile().getAbsolutePath();

            // if name have no ".xml", we must concat it.
            if (!path.endsWith(".xml")) {
                path += ".xml";
            }

            Place.setCurIndex(0);
            Transition.setCurIndex(0);

            data.load(path);
            data.setActiveElement(null);
            data.setAddingModeElement(null);
            emulator.setData(data); // TODO: clone!!!!!!!!!11
            // mainFrame.getElementDrawer().repaint();
            mainFrame.repaint();

            data.getCommandStack().getCommandList().clear();
            data.getCommandStack().setCurIndex(-1); // no the commands

        }

    }

}
