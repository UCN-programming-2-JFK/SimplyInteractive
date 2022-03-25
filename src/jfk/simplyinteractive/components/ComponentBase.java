package jfk.simplyinteractive.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import jfk.simplyinteractive.interfaces.ComponentBaseIF;

public class ComponentBase implements ComponentBaseIF {

	private int width, height;
	private boolean isSelected;
	private Point position; 
	
	public ComponentBase(int width, int height, Point position) {
		this.width = width;
		this.height = height;
		this.position = position;
	}

	public void setWidth(int width) {this.width = width;}
	public int getWidth() {return this.width;}

	public void setHeight(int height) {this.height = height;}
	public int getHeight() {return height;}

	public int getLeft() {return getPosition().x - getWidth()/2;}
	public int getTop() {return getPosition().y - getHeight()/2;}
	
	public boolean isSelected() {return isSelected;}
	public void setSelected(boolean isSelected) {this.isSelected = isSelected;}

	public void setPosition(Point position) {this.position = position;}
	public Point getPosition() {return position;}

	public boolean contains(Point point) {
		return getLeft() <= point.getX() && point.getX() <= getLeft() + getWidth() 
		&& getTop() <= point.getY() && point.getY() <= getTop() + getHeight(); 
	}
	
	public void paint(Graphics g) {
		Graphics2D graphics2d = (Graphics2D)g;
		graphics2d.setStroke(new BasicStroke(2));
		
		g.setColor(Color.WHITE);
		g.fillRect(getLeft(), getTop(), getWidth(), getHeight());
		if(isSelected()) {g.setColor(Color.red);}
		else {g.setColor(Color.black);}
			
		g.drawRect(getLeft(), getTop(), getWidth(), getHeight());
	}
}