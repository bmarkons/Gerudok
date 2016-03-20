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
public class TileHorizontallyAction extends AbstractActionIcon {
	
	public TileHorizontallyAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, iconGetter("/toolbar/th.png"));
		putValue(NAME, rb.getString("TH"));
		putValue(SHORT_DESCRIPTION, rb.getString("THH"));

	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JDesktopPane desktop = MainFrameGerudok.getInstance().getWorkspaceView();
		JInternalFrame[] frames = MainFrameGerudok.getInstance().getWorkspaceView().getAllFrames();

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
