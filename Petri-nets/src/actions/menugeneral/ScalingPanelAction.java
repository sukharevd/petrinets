/**
 * 
 */
package actions.menugeneral;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import view.Scalable;

/**
 * Action, which is occurred when user clicks "Scale Plus" or "Scale Minus"
 * components, it changes scale-variable of Scaleable objects.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * 
 */
public class ScalingPanelAction extends AbstractAction {

    /**
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = 6646941469680936637L;

    private Scalable scaledPanel;

    private boolean isPlus;

    public ScalingPanelAction(Scalable scaledPanel, boolean isPlus) {
        this.scaledPanel = scaledPanel;
        this.isPlus = isPlus;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isPlus) {
            scaledPanel.incScale();
        } else {
            scaledPanel.decScale();
        }
        // scaledPanel.repaint();
    }

}
