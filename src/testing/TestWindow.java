package testing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import jfk.simplyinteractive.components.ComponentBase;
import jfk.simplyinteractive.components.InteractiveWindow;

public class TestWindow extends InteractiveWindow<ComponentBase> {

	private static final long serialVersionUID = 1L;

		public TestWindow() {
			
		getInteractivePanel().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3) {
					getInteractivePanel().addComponent(new ComponentBase(100,50, e.getPoint()));
				}
				super.mousePressed(e);
			}
		});
		}
}