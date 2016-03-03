package gerudok.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import gerudok.gui.MainFrameGerudok;
import gerudok.gui.windowParts.JTreeGerudok;
import gerudok.model.Document;
import gerudok.model.Page;
import gerudok.view.DocumentView;

public class DeletePageAction implements ActionListener {

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
