package gerudok.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.KeyStroke;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.gui.MainFrameGerudok;

@SuppressWarnings("serial")
public class QuitAction extends AbstractActionIcon {

	public QuitAction(Dimension d) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		//putValue(SMALL_ICON, iconGetter("power.png"));
		putValue(NAME, rb.getString("Quit"));
		putValue(SHORT_DESCRIPTION, rb.getString("QuitH"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrameGerudok.getInstance().dispatchEvent(new WindowEvent(MainFrameGerudok.getInstance(), WindowEvent.WINDOW_CLOSING));
	}

}
