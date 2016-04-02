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
import gerudok.model.Project;

@SuppressWarnings("serial")
public class NewDocumentAction extends AbstractActionIcon {

	public NewDocumentAction(Dimension d) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_D);
		putValue(SMALL_ICON, iconGetter("/toolbar/adddoc.png", d));
		putValue(NAME, rb.getString("NewDocument"));
		putValue(SHORT_DESCRIPTION, rb.getString("NewDocumentH"));

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Dodavanje novog dokumenta u selektovani projekat
		JTree tree = MainFrameGerudok.getInstance().getTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();
		TreePath path = tree.getSelectionPath();
		if (selectedComponent instanceof Project) {
			tree.expandPath(path);
			Project project = (Project) selectedComponent;
			Document document = new Document(project);
			project.addDocument(document);
		}
	}
}
