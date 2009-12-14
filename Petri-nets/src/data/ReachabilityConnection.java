package data;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import data.modeling.MarkType;
import data.modeling.TransitionsTable;


public class ReachabilityConnection {
	/**
	 * @return the totalwidth
	 */
	public int getTotalwidth() {
		return totalwidth;
	}
	/**
	 * @param totalwidth the totalwidth to set
	 */
	public void setTotalwidth(int totalwidth) {
		this.totalwidth = totalwidth;
	}
	/**
	 * @return the totalheight
	 */
	public int getTotalheight() {
		return totalheight;
	}
	/**
	 * @param totalheight the totalheight to set
	 */
	public void setTotalheight(int totalheight) {
		this.totalheight = totalheight;
	}
	private int height=60;
	private int width=70;
	private int totalwidth;
	private int totalheight=0;
	private int r=20;
	private int[] partYaruswidth;
	private int[] x;
	private int[] y;
	private MarkType[] type;
	private TreeConnection[] z;
	private double scale;
	Color gr1 = Color.white;
	Color gr2 = Color.black;
	/**
	 * @return the scale
	 */
	public double getScale() {
		return scale;
	}
	/**
	 * @param scale the scale to set
	 */
	public void setScale(double scale) {
		this.scale = scale;
	}
	public ReachabilityConnection(TreeConnection[] z, TransitionsTable CrossTable){
		this.z = new TreeConnection[z.length];
		this.z=z;
		int curHeight=0;
		x=new int[CrossTable.count()];
		y=new int[CrossTable.count()];
		type=new MarkType[CrossTable.count()];
		for (int i = 0; i < CrossTable.count(); i++) {
			if (CrossTable.getAt(i).getLevel()>curHeight)
				curHeight=CrossTable.getAt(i).getLevel();
		}
		curHeight=curHeight+2;
		totalheight= height*curHeight;
		partYaruswidth = new int[curHeight-1];
		for (int i = 0; i < CrossTable.count(); i++) {
			partYaruswidth[CrossTable.getAt(i).getLevel()]=partYaruswidth[CrossTable.getAt(i).getLevel()]+1;
		}
		int maxyarus=0;
		for (int i = 0; i <partYaruswidth.length ; i++) {
			if (partYaruswidth[i]>maxyarus)
				maxyarus=partYaruswidth[i];
		}
		totalwidth= width*(maxyarus+1);
		for (int i = 0; i < partYaruswidth.length; i++) {
			partYaruswidth[i]= (int) (totalwidth/(partYaruswidth[i]+1));
		}
		int[] curPos=new int [partYaruswidth.length];
		for (int i = 0; i < CrossTable.count(); i++) {
		        type[i]=CrossTable.getAt(i).getMarkType();
		        curPos[CrossTable.getAt(i).getLevel()]=curPos[CrossTable.getAt(i).getLevel()]+1;
		        x[i]=partYaruswidth[CrossTable.getAt(i).getLevel()]*curPos[CrossTable.getAt(i).getLevel()];
		        y[i]=height*(CrossTable.getAt(i).getLevel()+1);
		}
		
	}
	public void draw(Graphics g){
		r=(int)(r*scale);
		for (int i = 0; i <x.length ; i++) {
			int fromX = (int) (x[z[i].getElementVuhod(0)]*scale);
			int fromY=(int) (y[z[i].getElementVuhod(0)]*scale);
			int toX=(int) (x[z[i].getElementVhod(0)]*scale);
			int toY=(int) (y[z[i].getElementVhod(0)]*scale);
			g.drawLine(fromX, fromY, toX, toY);
		}
		for (int i = 0; i <x.length ; i++) {
			//green
			if (type[i] == MarkType.ROOT) {
				gr1 = new Color(97,254,137);
				gr2 = new Color(76,159,98); 
			}
			//yellow
			if (type[i] == MarkType.INTERNAL) {
				gr1 = new Color(252,255,161);
				gr2 = new Color(223,220,0);
			} 
			//orange
			if (type[i] == MarkType.REPEATED) {
				gr1 = new Color(255,238,144);
				gr2 = new Color(255,147,99);
			}
			//red
			if (type[i] == MarkType.DEADLOCK) {
				gr1 = new Color(246,107,107);
				gr2 = new Color(199,72,72);
			}
			int posX=(int)(x[z[i].getElementVhod(0)]*scale);
			int posY=(int)(y[z[i].getElementVhod(0)]*scale);
			Graphics2D g2 = (Graphics2D) g;
			GradientPaint gradient = new GradientPaint(posX-r, posY-r,gr1, 
					posX+r, posY+r,gr2);
			g2.setPaint(gradient);
			g.fillOval(posX-r, posY-r, 2*r, 2*r);
			g.setColor(Color.BLACK);
			g.drawOval(posX-r, posY-r, 2*r, 2*r);
			g.drawString(Integer.toString(z[i].getNameVhod()), posX-5,posY );
			
			
		}
	}
	

}
