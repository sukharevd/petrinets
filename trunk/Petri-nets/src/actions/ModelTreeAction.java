/**
 * 
 */
package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import view.TreeWindowDrawer;
import data.Data;
import data.TableManagment;
import data.TreeConnection;
import data.TreeofPetriNet;

/**
 * @author Admin
 * 
 */
public class ModelTreeAction extends AbstractAction {

    /**
     * 
     */
    private static final long serialVersionUID = 7897141005109233931L;

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */

    private Data data;

    public ModelTreeAction(Data data) {
        this.data = data;
    }

    public void actionPerformed(ActionEvent e) {
        TableManagment myTable = new TableManagment(this.data);
        int[] typecrossing = new int[myTable.getAllT().size()];
        for (int i = 0; i < typecrossing.length; i++) {
            typecrossing[i] = 0;
            if (myTable.getAllT().get(i).getLyambda() == 0) {
                typecrossing[i] = 1;
            }
        }

        TreeofPetriNet mytree = new TreeofPetriNet(myTable.getAllP().size(),
                myTable.getAllT().size(), myTable.getMatrixDi(), myTable
                        .getMatrixDq(), myTable.getMarkirovka(), typecrossing);

        TreeConnection[] Z = mytree.WriteResult();
        TreeWindowDrawer twd = new TreeWindowDrawer(25, Z);
        twd.setVisible(true);
        mytree = null;
    }

}
