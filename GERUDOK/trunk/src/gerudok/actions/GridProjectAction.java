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

	public GridProjectAction(Dimension d) {
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, iconGetter("/toolbar/grid.png", d));
		putValue(NAME, rb.getString("Grid"));
		putValue(SHORT_DESCRIPTION, rb.getString("GridH"));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JDesktopPane desktop = MainFrameGerudok.getInstance().getWorkspaceView();
		JInternalFrame[] frames = MainFrameGerudok.getInstance()
				.getWorkspaceView().getAllFrames();
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

		int last_x = 0;
		int last_y = 0;
		
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
				last_x = x;
				x += w;
				last_y = y;
			}
			y += h; // start the next row
			x = 0;
		}
		/*
		if (desktop.getAllFrames().length != rows*cols) {
			JInternalFrame f = frames[desktop.getAllFrames().length - 1];
			desktop.getDesktopManager().resizeFrame(f, last_x, last_y, 2*w, h);
		}
		*/
	}

}
