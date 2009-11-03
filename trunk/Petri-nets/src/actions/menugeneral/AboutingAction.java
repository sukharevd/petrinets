package actions.menugeneral;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Action, which is occurred when user clicks "About..." component, it shows
 * message with information about the application and author of application.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class AboutingAction extends AbstractAction implements Cloneable {

    /**
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = 5745683949359609280L;

    private JFrame mainFrame;

    /**
     * @param mainFrame
     */
    public AboutingAction(final JFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
    }

    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(final ActionEvent e) {
        JOptionPane.showMessageDialog(mainFrame, "Petri nets builder\n"
                + "Copyright (C) 2009,\n" + "Sukharev [Ecuna] Dmitriy\n"
                + "Dzyuban [Jacky] Yuriy\n" + "Voitova [HdF] Anastasiia,\n"
                + "Kushtch Kristina", "About", JOptionPane.INFORMATION_MESSAGE);
        // TODO: change names.
    }

}
