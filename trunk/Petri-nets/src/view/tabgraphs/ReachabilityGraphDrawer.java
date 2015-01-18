/*
    Copyright (C)  2009  Sukharev Dmitriy, Dzyuban Yuriy, Vixen Tael.
    
    This file is part of Petri nets Emulator.
    
    Petri nets Emulator is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    
    Petri nets Emulator is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    
    You should have received a copy of the GNU General Public License
    along with Petri nets Emulator. If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * 
 */
package view.tabgraphs;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import view.Scalable;
import data.Data;
import data.TableManagment;
import data.modeling.TreeConnection;
import data.modeling.TreeofPetriNet;

/**
 * Draws reachability graph on a tab
 * @author <a href="mailto:jacky@gmail.com">Dzyuban Yuriy</a>
 * 
 */
public class ReachabilityGraphDrawer extends JPanel implements Scalable {

	/**
     * Class for drawing reachability graph on a tab.
     * Needs copy ReachabilityConnection to draw graph correctly.
     */
	private static final long serialVersionUID = 7897141005109233931L;

	private Data data;
	private ReachabilityConnection rc;
	private TreeConnection[] Z;
	private double scale = 1;

	public ReachabilityGraphDrawer(Data data) {
		this.data = data;
	}
	/*
	 * Increase Scale for 10 percent
	 */
	public final void incScale() {
		scale = scale + 0.1;
		repaint();
	}
	/*
	 * Decrease Scale for 10 percent
	 */
	public final void decScale() {
		// throw new UnsupportedOperationException();
		scale = scale - 0.1;
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
						.getMatrixDq(), myTable.getMarkirovka(), typecrossing,
				data);
		Z = mytree.WriteResult(1);
		super.paint(g);
		rc = new ReachabilityConnection(Z, mytree.getTransTable());
		rc.setScale(scale);
		rc.draw(g);
		this.setPreferredSize(new Dimension((int) (rc.getTotalwidth() * scale),
				(int) (rc.getTotalheight() * scale)));
		this.revalidate();
	}

}
