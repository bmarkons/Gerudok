package gerudok.actions;

import gerudok.gui.MainFrameGerudok;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class GridProjectAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JDesktopPane desktop = MainFrameGerudok.getInstance().getDesktopPane();
		JInternalFrame[] frames = MainFrameGerudok.getInstance().getDesktopPane().getAllFrames();
		int count = frames.length;
		if (count == 0)
			return;

		int sqrt = (int) Math.sqrt(count);
		int rows = sqrt;
		int cols = sqrt;
		if (rows * cols < count) {
			cols++;
			if (rows * cols < count) {
				rows++;
			}
		}
		Dimension size = desktop.getSize();
		int w = size.width / cols;
		int h = size.height / rows;
		int x = 0;
		int y = 0;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols && ((i * cols) + j < count); j++) {
				JInternalFrame f = frames[(i * cols) + j];

				if (!f.isClosed() && f.isIcon()) {
					try {
						f.setIcon(false);
					} catch (PropertyVetoException ignored) {
					}
				}

				desktop.getDesktopManager().resizeFrame(f, x, y, w, h);
				x += w;
			}
			y += h; // start the next row
			x = 0;
		}
	}

}
