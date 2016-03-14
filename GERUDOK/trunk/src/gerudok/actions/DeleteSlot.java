package gerudok.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import gerudok.gui.MainFrameGerudok;
import gerudok.gui.windowParts.JTreeGerudok;
import gerudok.model.Page;
import gerudok.model.Slot;
import gerudok.view.PageView;
import gerudok.view.SlotView;

@SuppressWarnings("serial")
public class DeleteSlot extends AbstractActionIcon {
	
	public DeleteSlot() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.SHIFT_MASK));
		putValue(SMALL_ICON, iconGetter("/toolbar/delslot.png"));
		putValue(NAME, "Delete slot");
		putValue(SHORT_DESCRIPTION, "Delete slot (SHIFT+S)");

	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JTreeGerudok tree = MainFrameGerudok.getInstance().getTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();
		if (selectedComponent instanceof Slot) {
			Slot slot = (Slot) selectedComponent;
			Page parent = (Page) slot.getParent();
			parent.deleteSlot(slot);
			SwingUtilities.updateComponentTreeUI(tree);

			// Brisanje SlotView-a iz odgovarajuceg PageView-a
			PageView pageView = parent.getPageView();
			SlotView slotView = slot.getSlotView();
			pageView.removeSlotView(slotView);
		}
	}
}
