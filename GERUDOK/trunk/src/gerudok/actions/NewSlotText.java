package gerudok.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.Page;
import gerudok.model.SlotText;
import gerudok.view.PageView;
import gerudok.view.SlotTextView;

@SuppressWarnings("serial")
public class NewSlotText extends AbstractActionIcon {
	
	public NewSlotText() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_T);
		putValue(SMALL_ICON, iconGetter("/toolbar/newtxt.png"));
		putValue(NAME, "New text slot");
		putValue(SHORT_DESCRIPTION, "New text slot (CTRL+T)");

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Dodavanje novog tekstualnog slota u selektovanu stranicu
		JTree tree = MainFrameGerudok.getInstance().getTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();
		TreePath path = tree.getSelectionPath();
		if (selectedComponent instanceof Page) {
			tree.expandPath(path);
			Page page = (Page) selectedComponent;
			SlotText slot = new SlotText(page);
			page.addSlot(slot);
			SwingUtilities.updateComponentTreeUI(tree);

			// Pravi novi SlotView i uvezuje se sa njim
			SlotTextView slotView = new SlotTextView(slot);
			slot.setSlotView(slotView);

			// Prikaz stranice u koji se dodaje prikaz slota
			PageView pageView = page.getPageView();
			pageView.addSlotView(slotView);
		}
	}
}
