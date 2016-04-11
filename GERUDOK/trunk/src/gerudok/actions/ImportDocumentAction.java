package gerudok.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import gerudok.actions.manager.AbstractActionIcon;

public class ImportDocumentAction extends AbstractActionIcon {
	private static final long serialVersionUID = 8208866974431745427L;

	public ImportDocumentAction(Dimension d) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_I);
		putValue(SMALL_ICON, iconGetter("/toolbar/importdoc.png", d));
		putValue(NAME, rb.getString("Import"));
		putValue(SHORT_DESCRIPTION, rb.getString("ImportH"));

	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
