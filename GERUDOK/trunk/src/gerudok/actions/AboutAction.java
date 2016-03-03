package gerudok.actions;

import gerudok.gui.MainFrameGerudok;
import gerudok.gui.dialogs.AboutDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Prikazivanje dialoga sa osnovnim podacima o autorima aplikacije
		// "Gerudok v1.1"
		AboutDialog dialog = new AboutDialog(MainFrameGerudok.getInstance(), "About team 1.1", true);
		dialog.setVisible(true);
	}

}
