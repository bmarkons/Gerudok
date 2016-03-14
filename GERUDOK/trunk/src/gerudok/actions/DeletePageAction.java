package gerudok.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import gerudok.gui.MainFrameGerudok;
import gerudok.gui.windowParts.JTreeGerudok;
import gerudok.model.Document;
import gerudok.model.Page;
import gerudok.view.DocumentView;

@SuppressWarnings("serial")
public class DeletePageAction extends AbstractActionIcon {
	
	public DeletePageAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.SHIFT_MASK));
		putValue(SMALL_ICON, iconGetter("/toolbar/delpage.png"));
		putValue(NAME, "Delete page");
		putValue(SHORT_DESCRIPTION, "Delete page (SHIFT+P)");

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Brisanje selektovane stranice
		JTreeGerudok tree = MainFrameGerudok.getInstance().getTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();
		if (selectedComponent instanceof Page) {
			Page page = (Page) selectedComponent;
			Document parent = (Document) page.getParent();
			parent.deletePage(page);
			SwingUtilities.updateComponentTreeUI(tree);

			DocumentView docView = parent.getDocumentView();
			docView.removePageView(page.getPageView());
		}
	}
}
