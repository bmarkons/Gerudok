package gerudok.actions;

import gerudok.gui.MainFrameGerudok;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;

public class CascadeProjectAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		JInternalFrame[] projects = MainFrameGerudok.getInstance().getDesktopPane().getAllFrames();
		int xStart = 20, yStart = 20, x = xStart, y = yStart,
				//maxX = MainFrame.getInstance().getDesktopPane().getWidth(),
				maxY = MainFrameGerudok.getInstance().getDesktopPane().getHeight();
		Dimension d = MainFrameGerudok.getInstance().getDesktopPane().getSize();

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
