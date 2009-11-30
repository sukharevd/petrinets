/**
 * 
 */
package actions.menugeneral;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import view.Scalable;

/**
 * @author Admin
 * 
 */
public class ScalingPanelAction extends AbstractAction {

    /**
     * 
     */
    private static final long serialVersionUID = 6646941469680936637L;

    private Scalable scaledPanel;

    private boolean is_plus;

    public ScalingPanelAction(Scalable scaledPanel, boolean is_plus) {
        this.scaledPanel = scaledPanel;
        this.is_plus = is_plus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (is_plus) {
            scaledPanel.incScale();
        } else {
            scaledPanel.decScale();
        }
        // scaledPanel.repaint();
    }

}
