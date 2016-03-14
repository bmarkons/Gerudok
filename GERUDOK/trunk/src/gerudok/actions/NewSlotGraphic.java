package gerudok.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import gerudok.gui.MainFrameGerudok;
import gerudok.gui.windowParts.JTreeGerudok;
import gerudok.model.Page;
import gerudok.model.SlotGraphic;
import gerudok.view.GraphicSlotView;
import gerudok.view.PageView;

@SuppressWarnings("serial")
public class NewSlotGraphic extends AbstractActionIcon {
	
	public NewSlotGraphic() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_G);
		putValue(SMALL_ICON, iconGetter("/toolbar/newgslot.png"));
		putValue(NAME, "New graphic slot");
		putValue(SHORT_DESCRIPTION, "New graphic slot (CTRL+G)");

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Dodavanje novog grafickog slota u selektovanu stranicu
		JTreeGerudok tree = MainFrameGerudok.getInstance().getTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();
		TreePath path = tree.getSelectionPath();
		if (selectedComponent instanceof Page) {
			tree.expandPath(path);
			Page page = (Page) selectedComponent;
			SlotGraphic slot = new SlotGraphic(page);
			page.addSlot(slot);
			SwingUtilities.updateComponentTreeUI(tree);

			// Pravi novi SlotView i uvezuje se sa njim
			GraphicSlotView slotView = new GraphicSlotView(slot);
			slot.setSlotView(slotView);

			// Prikaz stranice u koji se dodaje prikaz slota
			PageView pageView = page.getPageView();
			pageView.addSlotView(slotView);
		}
	}
}
