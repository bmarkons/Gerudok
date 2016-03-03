package gerudok.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import gerudok.gui.MainFrameGerudok;
import gerudok.gui.windowParts.JTreeGerudok;
import gerudok.model.Page;
import gerudok.model.SlotGraphic;
import gerudok.view.PageView;
import gerudok.view.SlotView;

public class DeleteSlotGraphic implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// Dodavanje grafickog slota u selektovanu stranicu
		JTreeGerudok tree = MainFrameGerudok.getInstance().getTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();
		if (selectedComponent instanceof SlotGraphic) {
			SlotGraphic slot = (SlotGraphic) selectedComponent;
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
