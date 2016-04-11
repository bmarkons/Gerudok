package gerudok.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import gerudok.actions.manager.AbstractActionIcon;

@SuppressWarnings("serial")
public class CutAction extends AbstractActionIcon{

	public CutAction(Dimension d) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, iconGetter("/toolbar/about.png",d));
		putValue(NAME, "Cut");
		putValue(SHORT_DESCRIPTION, "Cut");

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	
}
