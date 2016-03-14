package gerudok.actions;

import gerudok.gui.MainFrameGerudok;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class TileVerticallyAction extends AbstractActionIcon {
	
	public TileVerticallyAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, iconGetter("/toolbar/tv.png"));
		putValue(NAME, "Tile vertically");
		putValue(SHORT_DESCRIPTION, "Tile vertically (ALT+V)");

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JDesktopPane desktop = MainFrameGerudok.getInstance().getDesktopPane();
		JInternalFrame[] frames = MainFrameGerudok.getInstance().getDesktopPane().getAllFrames();

		int count = frames.length;
		if (count == 0)
			return;

		Dimension size = desktop.getSize();
		int h = size.height / frames.length;
		int w = size.width;
		int x = 0;
		int y = 0;

		for (int i = 0; i < frames.length; i++) {
			for (int j = 0; j < count && ((i * count) + j < count); j++) {
				JInternalFrame f = frames[(i * h) + j];

				if (!f.isClosed() && f.isIcon()) {
					try {
						f.setIcon(false);
					} catch (PropertyVetoException ignored) {
					}
				}
				desktop.getDesktopManager().resizeFrame(f, x, y, w, h);
				y += h;
			}
			x += h; // start the next column
			y = 0;
		}

	}

}