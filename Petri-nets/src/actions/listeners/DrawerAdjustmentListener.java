package actions.listeners;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JFrame;

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
