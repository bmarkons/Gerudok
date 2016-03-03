package gerudok.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import gerudok.gui.MainFrameGerudok;
import gerudok.gui.windowParts.JTreeGerudok;
import gerudok.model.Page;
import gerudok.model.SlotGraphic;
import gerudok.view.PageView;
import gerudok.view.SlotView;

public class NewSlotGraphic implements ActionListener {

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
			SlotView slotView = new SlotView(slot);
			slot.setSlotView(slotView);

			// Prikaz stranice u koji se dodaje prikaz slota
			PageView pageView = page.getPageView();
			pageView.addSlotView(slotView);
		}
	}
}
