package gerudok.actions;

import gerudok.gui.MainFrameGerudok;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class TileHorizontallyAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JDesktopPane desktop = MainFrameGerudok.getInstance().getDesktopPane();
		JInternalFrame[] frames = MainFrameGerudok.getInstance().getDesktopPane().getAllFrames();

		int count = frames.length;
		if (count == 0)
			return;

		Dimension size = desktop.getSize();
		int h = size.height;
		int w = size.width / frames.length;
		int x = 0;
		int y = 0;

		for (int i = 0; i < frames.length; i++) {
			for (int j = 0; j < count && ((i * count) + j < count); j++) {
				JInternalFrame f = frames[(i * w) + j];

				if (!f.isClosed() && f.isIcon()) {
					try {
						f.setIcon(false);
					} catch (PropertyVetoException ignored) {
					}
				}
				desktop.getDesktopManager().resizeFrame(f, x, y, w, h);
				x += w;
			}
			y += w; // start the next row
			x = 0;
		}

	}

}
