package jfk.simplyinteractive.components;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JPanel;

import jfk.simplyinteractive.interfaces.ComponentBaseIF;

public class InteractivePanel<T extends ComponentBaseIF> extends JPanel {
	private static final long serialVersionUID = 1L;
	public ArrayList<T> elements = new ArrayList<T>();
	private T selectedItem = null;
	private Point lastMousePosition = new Point();
	private int mouseState;

	public InteractivePanel() {

		MouseAdapter adapter = new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {

				lastMousePosition = arg0.getPoint();
				setSelectedItem(findElementAt(arg0.getPoint()));
				if (getSelectedItem() != null) {
					selectItemAndMoveToTop(getSelectedItem());
					repaint();
				}
			}

			@Override
			public void mouseDragged(MouseEvent arg0) {
				int mouseMovementX = arg0.getX() - lastMousePosition.x;
				int mouseMovementY = arg0.getY() - lastMousePosition.y;
				if (getSelectedItem() != null) {
					getSelectedItem().getPosition().translate(mouseMovementX, mouseMovementY);
					repaint();
				}
				lastMousePosition = arg0.getPoint();
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				lastMousePosition = arg0.getPoint();
				T componentUnderCursor = findElementAt(lastMousePosition);

				if (componentUnderCursor != null) {
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				} else {
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				repaint();
			}
		};
		addMouseMotionListener(adapter);
		addMouseListener(adapter);
	}

	public void paint(Graphics g) {
		Graphics2D graphics2d = (Graphics2D) getGraphics();
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.fillRect(0, 0, getWidth(), getHeight());
		for (T element : elements) {
			element.paint(g);
		}
	}

	public void deleteSelectedItem() {
		if (getSelectedItem() != null) {
			elements.remove(getSelectedItem());
		}
		repaint();
	}

	private T findElementAt(Point point) {
		for (int i = elements.size() -1; i >= 0 ; i--) {
			T inspectedElement = elements.get(i);
			if(inspectedElement.contains(lastMousePosition)) {			
				return inspectedElement;
			}
		}
		return null;
	}

	private void selectItemAndMoveToTop(T item) {
		elements.remove(item);
		elements.add(item);
		setSelectedItem(item);
	}

	public void addComponent(T element) {
		elements.add(element);
		repaint();
	}

	public void removeComponent(T element) {
		elements.remove(element);
		repaint();
	}

	public T getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(T selectedItem) {
		if(this.getSelectedItem() != null) {
			this.getSelectedItem().setSelected(false);
		}
		this.selectedItem = selectedItem;
		if(getSelectedItem() != null) {
			getSelectedItem().setSelected(true);
		}
		repaint();
	}

	public int getMouseState() {
		return mouseState;
	}

	public void setMouseState(int mouseState) {
		this.mouseState = mouseState;
	}
}
