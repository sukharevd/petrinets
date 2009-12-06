package actions.menugeneral;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import data.Data;
import data.modeling.EmulationManager;

/**
 * Action, which is occurred when user clicks "Save as..." component, it saves
 * the current chart as xml-file to a disk.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class SavingAction extends AbstractAction {

    /**
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = 8048312361973227507L;

    private Data data = null;
    
    private EmulationManager emulator;

    private JFrame mainFrame = null;

    /**
     * @param data
     * @param mainFrame
     */
    public SavingAction(Data data, EmulationManager emulator, JFrame mainFrame) {
        super();
        this.data = data;
        this.emulator = emulator;
        this.mainFrame = mainFrame;
    }

    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(final ActionEvent arg0) {
        save(data, mainFrame);
        resetEmulator();
    }

    /**
     * Saves the current chart as xml-file.
     */
    public static void save(Data data, JFrame mainFrame) {
        if (data.getElements().size() == 0) {
            JOptionPane.showMessageDialog(mainFrame,
                    "You can not save the chart without elements.", "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return;
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
        int returnVal = fc.showSaveDialog(mainFrame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            String path = fc.getSelectedFile().getAbsolutePath();

            // if name have no ".xml", we must add it.
            if (!path.endsWith(".xml")) {
                path += ".xml";
            }

            data.save(path);
        }
    }
    
    protected void resetEmulator() {
        emulator.setData(data);
    }

}
