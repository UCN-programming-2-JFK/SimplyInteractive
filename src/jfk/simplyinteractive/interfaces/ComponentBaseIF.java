package jfk.simplyinteractive.interfaces;

import java.awt.Graphics;

public interface ComponentBaseIF extends SizedIF, SelectableIF, PositionedIF, ContainerIF {
	public abstract void paint(Graphics g);
}