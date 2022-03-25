package jfk.simplyinteractive.components;

import javax.swing.JFrame;
import jfk.simplyinteractive.interfaces.ComponentBaseIF;

public class InteractiveWindow<T extends ComponentBaseIF> extends JFrame {

	private static final long serialVersionUID = 1L;

	private InteractivePanel<ComponentBase> editorPanel = new InteractivePanel<ComponentBase>();

	public InteractiveWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 800);
		getContentPane().add(editorPanel);
		setVisible(true);
	}

	public InteractivePanel<ComponentBase> getInteractivePanel() {
		return editorPanel;
	}

}