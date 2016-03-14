package gerudok.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import gerudok.gui.MainFrameGerudok;
import gerudok.gui.windowParts.JTreeGerudok;
import gerudok.model.Document;
import gerudok.model.Project;
import gerudok.view.DocumentView;
import gerudok.view.ProjectView;

@SuppressWarnings("serial")
public class DeleteDocumentAction extends AbstractActionIcon {
	
	public DeleteDocumentAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.SHIFT_MASK));
		putValue(SMALL_ICON, iconGetter("/toolbar/deldoc.png"));
		putValue(NAME, "Delete document");
		putValue(SHORT_DESCRIPTION, "Delete document (SHIFT+D)");

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Brisanje selektovanog dokumenta
		JTreeGerudok tree = MainFrameGerudok.getInstance().getTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();
		if (selectedComponent instanceof Document) {
			Document document = (Document) selectedComponent;
			Project parent = (Project) document.getParent();
			parent.deleteDocument(document);
			SwingUtilities.updateComponentTreeUI(tree);

			// Brisanje view-a izbrisanog dokumumenta iz odgovarajuceg prozora
			ProjectView projectView = parent.getProjectView();
			DocumentView documentView = document.getDocumentView();
			projectView.removeDocumentView(documentView);
		}
	}

}
