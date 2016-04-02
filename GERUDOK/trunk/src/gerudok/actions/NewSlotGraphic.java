package gerudok.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.tree.TreePath;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.Page;
import gerudok.model.SlotGraphic;


@SuppressWarnings("serial")
public class NewSlotGraphic extends AbstractActionIcon {
	
	public NewSlotGraphic(Dimension d) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_G);
		putValue(SMALL_ICON, iconGetter("/toolbar/newgslot.png", d));
		putValue(NAME, rb.getString("NewGSlot"));
		putValue(SHORT_DESCRIPTION, rb.getString("NewGSlotH"));

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Dodavanje novog grafickog slota u selektovanu stranicu
		JTree tree = MainFrameGerudok.getInstance().getTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();
		TreePath path = tree.getSelectionPath();
		if (selectedComponent instanceof Page) {
			tree.expandPath(path);
			Page page = (Page) selectedComponent;
			SlotGraphic slot = new SlotGraphic(page);
			page.addSlot(slot);
		}
	}
}
