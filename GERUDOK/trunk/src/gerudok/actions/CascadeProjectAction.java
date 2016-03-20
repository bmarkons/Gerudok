package gerudok.actions;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.gui.MainFrameGerudok;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class CascadeProjectAction extends AbstractActionIcon {
	
	public CascadeProjectAction() {
		
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, iconGetter("/toolbar/ch.png"));
		putValue(NAME, rb.getString("Cascade"));
		putValue(SHORT_DESCRIPTION, rb.getString("CascadeH"));

	}

	public void actionPerformed(ActionEvent e) {

		JInternalFrame[] projects = MainFrameGerudok.getInstance()
				.getWorkspaceView().getAllFrames();
		int xStart = 20, yStart = 20, x = xStart, y = yStart,
		// maxX = MainFrame.getInstance().getDesktopPane().getWidth(),
		maxY = MainFrameGerudok.getInstance().getWorkspaceView().getHeight();
		Dimension d = MainFrameGerudok.getInstance().getWorkspaceView().getSize();

		for (int i = 0; i < projects.length; i++) {
			JInternalFrame cascade = projects[i];
			cascade.setSize(d.width / 2, 2 * d.height / 3);
			cascade.setLocation(x, y);

			if (d.height / 2 + y + 20 < maxY) {
				y += 40;
				x += 40;
			} else {
				xStart += 40;
				y = yStart;
				x = xStart + 40;
			}

			try {
				cascade.setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		}
	}

}
