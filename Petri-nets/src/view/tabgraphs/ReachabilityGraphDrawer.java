/**
 * 
 */
package view.tabgraphs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

import data.Data;
import data.TableManagment;
import data.TreeConnection;
import data.TreeofPetriNet;

/**
 * @author <a href="mailto:jacky@gmail.com">Dzyuban Yuriy</a>
 * 
 */
public class ReachabilityGraphDrawer extends JPanel {

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

    public ReachabilityGraphDrawer(Data data) {
        this.data = data;    
    }

    int WIDTH = 550;

    int HEIGTH = 550;

    int CenterX = WIDTH / 2;

    int CenterY = HEIGTH / 2;

    int Dist = WIDTH / 3;

    int d = 40;

    int r = d / 2;

    int L = d / 3;

    int LA = 20;

    TreeConnection[] Z;
    TreeConnection[] ZZ;
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
                        .getMatrixDq(), myTable.getMarkirovka(), typecrossing);
        Z = mytree.WriteResult(1);
        
        
        int i, count;
        int Angle = 0;
        int dAngle;
        double DegToRad = Math.PI / 180;
        double RadToDeg = 180.0 / Math.PI;

        super.paint(g);
        count =TreeofPetriNet.Number-1;
        dAngle = 360 / count;
        for (i = 0; i < count; i++) {
        	Color color1 = new Color(255,153,0);
        	g.setColor(color1);
            
            g.fillOval(CenterX + (int) (Dist * Math.cos(Angle * DegToRad)),
                    CenterY + (int) (Dist * Math.sin(Angle * DegToRad)), d, d);
            g.setColor(Color.white);
            g.drawString(Integer.toString(Z[i].getNameVhod()), CenterX
                    + (int) (Dist * Math.cos(Angle * DegToRad)) + r - 5,
                    CenterY + (int) (Dist * Math.sin(Angle * DegToRad)) + r);
            Angle = Angle + dAngle;
        }

        int A1, A2, dx1, dy1, dx2, dy2, s;
        int x1, x2, y1, y2, cx, cy, lx1, ly1, lx2, ly2;
        double k;
        for (i = 0; i < count; i++) {
                A2 = dAngle * Z[i].getElementVuhod(0); // konec
                A1 = dAngle * Z[i].getElementVhod(0); // na4alo

                dx1 = (int) (r * Math.cos(A1 * DegToRad));
                dy1 = (int) (r * Math.sin(A1 * DegToRad));
                dx2 = (int) (r * Math.cos(A2 * DegToRad));
                dy2 = (int) (r * Math.sin(A2 * DegToRad));
                x1 = CenterX + (int) (Dist * Math.cos(A1 * DegToRad)) + r - dx1;
                y1 = CenterY + (int) (Dist * Math.sin(A1 * DegToRad)) + r - dy1;
                x2 = CenterX + (int) (Dist * Math.cos(A2 * DegToRad)) + r - dx2;
                y2 = CenterY + (int) (Dist * Math.sin(A2 * DegToRad)) + r - dy2;
                if (A1 != A2) {
                    g.setColor(Color.blue);
                    g.drawLine(x2, y2, x1, y1);
                    k = (double) ((double) ((y1 - y2)) / (double) (x1 - x2));
                    s = 1;
                    if (x1 - x2 >= 0)
                        s = -1;
                    cx = (x1 + x2) / 2;
                    cy = (y1 + y2) / 2;
                    Angle = (int) (Math.atan(k) * RadToDeg);
                    lx1 = s * ((int) (L * Math.cos((Angle + LA) * DegToRad)));
                    ly1 = s * ((int) (L * Math.sin((Angle + LA) * DegToRad)));
                    lx2 = s * ((int) (L * Math.cos((Angle - LA) * DegToRad)));
                    ly2 = s * ((int) (L * Math.sin((Angle - LA) * DegToRad)));
                    Polygon P = new Polygon();
                    P.addPoint(cx, +cy);
                    P.addPoint(+cx + lx1, +cy + ly1);
                    P.addPoint(+cx + lx2, +cy + ly2);

                    g.setColor(Color.black);
                    g.fillPolygon(P);

                } else {
                    g.setColor(Color.blue);
                    g.drawArc(x2 + r + dx2, y2 - d / 4, d / 2, d / 2, 0, 360);
                    g.setColor(Color.black);
                    g.fillOval(x2 + r + dx2 - 3, y2 - 3, 6, 6);
                }
            }
        }

}
