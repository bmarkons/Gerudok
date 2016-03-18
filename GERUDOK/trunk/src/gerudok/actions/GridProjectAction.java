package gerudok.actions;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.gui.MainFrameGerudok;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class GridProjectAction extends AbstractActionIcon {

	public GridProjectAction() {
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, iconGetter("/toolbar/grid.png"));
		putValue(NAME, "Grid layout");
		putValue(SHORT_DESCRIPTION, "Grid layout (ALT+G)");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JDesktopPane desktop = MainFrameGerudok.getInstance().getDesktopPane();
		JInternalFrame[] frames = MainFrameGerudok.getInstance()
				.getDesktopPane().getAllFrames();
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
