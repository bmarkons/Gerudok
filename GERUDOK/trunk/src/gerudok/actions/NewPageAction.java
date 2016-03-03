package gerudok.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import gerudok.gui.MainFrameGerudok;
import gerudok.gui.windowParts.JTreeGerudok;
import gerudok.model.Document;
import gerudok.model.Page;
import gerudok.view.DocumentView;
import gerudok.view.PageView;

public class NewPageAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// Dodavanje nove stranice u selektovani dokument
		JTreeGerudok tree = MainFrameGerudok.getInstance().getTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();
		TreePath path = tree.getSelectionPath();
		if (selectedComponent instanceof Document) {
			tree.expandPath(path);
			Document document = (Document) selectedComponent;
			Page page = new Page(document);
			document.addPage(page);
			SwingUtilities.updateComponentTreeUI(tree);

			PageView view = new PageView(page.getName());

			page.setPageView(view);
			view.setPage(page);

			DocumentView docView = document.getDocumentView();
			docView.addPageView(view);

		}
	}
}
