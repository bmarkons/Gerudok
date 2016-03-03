package gerudok.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import gerudok.gui.MainFrameGerudok;
import gerudok.gui.windowParts.JTreeGerudok;
import gerudok.model.Page;
import gerudok.model.Slot;
import gerudok.model.SlotText;
import gerudok.view.PageView;
import gerudok.view.SlotView;

public class DeleteSlotText implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JTreeGerudok tree = MainFrameGerudok.getInstance().getTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();
		if (selectedComponent instanceof SlotText) {
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
