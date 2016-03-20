package gerudok.actions;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.gui.MainFrameGerudok;
import gerudok.gui.dialogs.AboutDialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class AboutAction extends AbstractActionIcon {
	
	public AboutAction(Dimension d) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_A);
		putValue(SMALL_ICON, iconGetter("/toolbar/about.png",d));
		putValue(NAME, rb.getString("About"));
		putValue(SHORT_DESCRIPTION, rb.getString("AboutH"));

	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Prikazivanje dialoga sa osnovnim podacima o autorima aplikacije
		// "Gerudok v1.1"
		AboutDialog dialog = new AboutDialog(MainFrameGerudok.getInstance(), "About team 1.1", true);
		dialog.setVisible(true);
	}

}
