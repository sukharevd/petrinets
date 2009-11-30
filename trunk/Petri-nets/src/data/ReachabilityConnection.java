package data;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

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
	private String[] type;
	private TreeConnection[] z;
	private double scale;
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
	public ReachabilityConnection(TreeConnection[] z, ArrayList<CrossingRow> CrossTable){
		this.z = new TreeConnection[z.length];
		this.z=z;
		int curHeight=0;
		x=new int[CrossTable.size()];
		y=new int[CrossTable.size()];
		type=new String[CrossTable.size()];
		for (int i = 0; i < CrossTable.size(); i++) {
			if (CrossTable.get(i).getTier()>curHeight)
				curHeight=CrossTable.get(i).getTier();
		}
		curHeight=curHeight+2;
		totalheight= height*curHeight;
		partYaruswidth = new int[curHeight-1];
		for (int i = 0; i < CrossTable.size(); i++) {
			partYaruswidth[CrossTable.get(i).getTier()]=partYaruswidth[CrossTable.get(i).getTier()]+1;
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
		for (int i = 0; i < CrossTable.size(); i++) {
		        type[i]=CrossTable.get(i).getTypeMark();
		        curPos[CrossTable.get(i).getTier()]=curPos[CrossTable.get(i).getTier()]+1;
		        x[i]=partYaruswidth[CrossTable.get(i).getTier()]*curPos[CrossTable.get(i).getTier()];
		        y[i]=height*(CrossTable.get(i).getTier()+1);
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
			if (type[i] =="Root")
				g.setColor(Color.GREEN);
			if (type[i] =="Vnutrenya")
				g.setColor(Color.YELLOW);
			if (type[i] =="Povtorilas`")
				g.setColor(Color.PINK);
			if (type[i] =="Typikovaja")
				g.setColor(Color.RED);
			int posX=(int)(x[z[i].getElementVhod(0)]*scale);
			int posY=(int)(y[z[i].getElementVhod(0)]*scale);
			g.fillOval(posX-r, posY-r, 2*r, 2*r);
			g.setColor(Color.BLACK);
			g.drawString(Integer.toString(z[i].getNameVhod()), posX,posY );
			
			
		}
	}
	

}
