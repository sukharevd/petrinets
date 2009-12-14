/**
 * 
 */
package view.tabgraphs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

import view.Scalable;
import data.Data;
import data.TableManagment;
import data.TreeConnection;
import data.TreeofPetriNet;

/**
 * @author <a href="mailto:jacky@gmail.com">Dzyuban Yuriy</a>
 * 
 */
public class MarkovGraphDrawer extends JPanel implements Scalable {

    /**
     * 
     */
    private static final long serialVersionUID = 7897141005109233931L;

    // private TreeConnection[] Z;

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */

    private Data data;

    public MarkovGraphDrawer(Data data) {
        this.data = data;

        
    }

    int WIDTH = 550;

    int HEIGTH = 350;

    int CenterX = WIDTH / 2;

    int CenterY = HEIGTH / 2;

    int Dist = WIDTH / 4;

    int d = 25;

    int r = d / 2;

    int L = d / 3;

    int LA = 20;
    double scale = 1.0;

    // int N;
    static TreeConnection[] Z;

    public void paint(Graphics g) {
    	LA=(int)(LA);
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

        Z=mytree.WriteResult(0);
        
        
        int i, count, j;
        int Angle = 0;
        int dAngle;
        double DegToRad = Math.PI / 180;
        double RadToDeg = 180.0 / Math.PI;

        super.paint(g);
        count =TreeofPetriNet.RepeatCount;
        dAngle = 360 / count;
        for (i = 0; i < count; i++) {
            g.setColor(Color.red);
            g.fillOval((int)(CenterX*scale) + (int) (Dist*scale * Math.cos(Angle * DegToRad)),
                    (int)(CenterY*scale) + (int) (Dist*scale * Math.sin(Angle * DegToRad)), (int)(d*scale),(int) (d*scale));
            g.setColor(Color.white);
            g.drawString(Integer.toString(i), (int) (CenterX*scale)
                    + (int) (Dist*scale * Math.cos(Angle * DegToRad)) + (int)(r*scale - 5*scale),
                    (int)(CenterY*scale) + (int) (Dist*scale * Math.sin(Angle * DegToRad)) + (int)(r*scale));
            Angle = Angle + dAngle;
        }

        int A1, A2, dx1, dy1, dx2, dy2, s;
        int x1, x2, y1, y2, cx, cy, lx1, ly1, lx2, ly2;
        double k;
        for (i = 0; i < count; i++) {
            for (j = 0; j < Z[i].getColVhod(); j++) {
                A2 = dAngle * Z[i].getElementVuhod(j); // konec
                A1 = dAngle * Z[i].getElementVhod(j); // na4alo

                dx1 = (int) (r*scale * Math.cos(A1 * DegToRad));
                dy1 = (int) (r*scale * Math.sin(A1 * DegToRad));
                dx2 = (int) (r*scale * Math.cos(A2 * DegToRad));
                dy2 = (int) (r*scale * Math.sin(A2 * DegToRad));
                x1 = (int)(CenterX*scale) + (int) (Dist*scale * Math.cos(A1 * DegToRad)) + (int)(r*scale) - dx1;
                y1 = (int)(CenterY*scale) + (int) (Dist*scale * Math.sin(A1 * DegToRad)) + (int)(r*scale) - dy1;
                x2 = (int)(CenterX*scale) + (int) (Dist*scale * Math.cos(A2 * DegToRad)) + (int)(r*scale) - dx2;
                y2 = (int)(CenterY*scale) + (int) (Dist*scale * Math.sin(A2 * DegToRad)) + (int)(r*scale) - dy2;
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
                    lx1 = s * ((int) (L*scale * Math.cos((Angle + LA) * DegToRad)));
                    ly1 = s * ((int) (L*scale * Math.sin((Angle + LA) * DegToRad)));
                    lx2 = s * ((int) (L*scale* Math.cos((Angle - LA) * DegToRad)));
                    ly2 = s * ((int) (L*scale* Math.sin((Angle - LA) * DegToRad)));
                    Polygon P = new Polygon();
                    P.addPoint(cx, +cy);
                    P.addPoint(+cx + lx1, +cy + ly1);
                    P.addPoint(+cx + lx2, +cy + ly2);

                    g.setColor(Color.black);
                    g.fillPolygon(P);

                } else {
                    g.setColor(Color.blue);
                    g.drawArc(x2 + (int)(r*scale) + dx2, y2 - (int)(d*scale / 4), (int)(d*scale / 2), (int)(d*scale / 2), 0, 360);
                    g.setColor(Color.black);
                    g.fillOval(x2 + (int)(r*scale) + dx2 - 3, y2 - 3, 6, 6);
                }
            }
        }
        this.setPreferredSize(new Dimension((int)(WIDTH*scale),(int)(HEIGTH*scale)));
        this.revalidate();
    }

	@Override
	public void decScale() {
		scale=scale-0.1;
		this.repaint();
		
	}

	@Override
	public void incScale() {
		scale=scale+0.1;
		this.repaint();
	}

}
