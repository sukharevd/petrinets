/**
 * 
 */
package view.tabgraphs;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import view.Scalable;
import data.Data;
import data.ReachabilityConnection;
import data.TableManagment;
import data.TreeConnection;
import data.TreeofPetriNet;

/**
 * @author <a href="mailto:jacky@gmail.com">Dzyuban Yuriy</a>
 * 
 */
public class ReachabilityGraphDrawer extends JPanel implements Scalable {

    /**
     * 
     */
    private static final long serialVersionUID = 7897141005109233931L;

    private Data data;
    private ReachabilityConnection rc;
    private TreeConnection[] Z;
    private double scale=1;

    public ReachabilityGraphDrawer(Data data) {
        this.data = data; 
    }

    public final void incScale() {
        scale=scale+0.1;
        repaint();
    }

    public final void decScale() {
        //throw new UnsupportedOperationException();
    	scale= scale-0.1;
    	repaint();
    }

    /**
     * Build tree of destination
     */
    public void paint(Graphics g) {
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
                        .getMatrixDq(), myTable.getMarkirovka(), typecrossing, data);
        Z = mytree.WriteResult(1);
        super.paint(g);
        rc = new ReachabilityConnection(Z,mytree.getTransTable());
        rc.setScale(scale);
        rc.draw(g);
        this.setPreferredSize(new Dimension((int)(rc.getTotalwidth()*scale),(int)(rc.getTotalheight()*scale)));
        this.revalidate();
        }

}
