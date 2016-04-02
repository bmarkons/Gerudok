package gerudok.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.tree.TreePath;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.Document;
import gerudok.model.Page;

@SuppressWarnings("serial")
public class NewPageAction extends AbstractActionIcon {
	
	public NewPageAction(Dimension d) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_P);
		putValue(SMALL_ICON, iconGetter("/toolbar/addpage.png", d));
		putValue(NAME, rb.getString("NewPage"));
		putValue(SHORT_DESCRIPTION, rb.getString("NewPageH"));

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Dodavanje nove stranice u selektovani dokument
		JTree tree = MainFrameGerudok.getInstance().getTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();
		TreePath path = tree.getSelectionPath();
		if (selectedComponent instanceof Document) {
			tree.expandPath(path);
			Document document = (Document) selectedComponent;
			Page page = new Page(document);
			document.addPage(page);
		}
	}
}
