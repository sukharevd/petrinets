package actions.listeners;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JFrame;

/**
 * This class listens the scroll motions, it performs repainting of a scrolled
 * component.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class DrawerAdjustmentListener implements AdjustmentListener {

    private JFrame frame;

    public DrawerAdjustmentListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        frame.repaint();
    }
}
