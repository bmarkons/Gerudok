package gerudok.actions;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.gui.dialogs.SlotGraphicDialog.GraphicSlotToolbar;
import gerudok.view.SlotGraphicView;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class PasteAction extends AbstractActionIcon {

	public PasteAction(Dimension d) {
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, iconGetter("/toolbar/about.png", d));
		putValue(NAME, "Paste");
		putValue(SHORT_DESCRIPTION, "Paste");

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Component[] c = ((GraphicSlotToolbar) ((JComponent) e.getSource())
				.getParent()).getParent().getComponents();

		SlotGraphicView sgv = null;

		for (int i = 0; i < c.length; i++) {
			if (c[i] instanceof SlotGraphicView)
				sgv = (SlotGraphicView) c[i];
		}

		if (sgv != null) {
			sgv.paste();
		}
	}

}
