package gerudok.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import gerudok.gui.MainFrameGerudok;
import gerudok.gui.windowParts.JTreeGerudok;
import gerudok.model.Document;
import gerudok.model.Project;
import gerudok.view.DocumentView;

public class NewDocumentAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// Dodavanje novog dokumenta u selektovani projekat
		JTreeGerudok tree = MainFrameGerudok.getInstance().getTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();
		TreePath path = tree.getSelectionPath();
		if (selectedComponent instanceof Project) {
			tree.expandPath(path);
			Project project = (Project) selectedComponent;
			Document document = new Document(project);
			project.addDocument(document);
			SwingUtilities.updateComponentTreeUI(tree);

			// Kreiranje prikaza dokumenta
			DocumentView view = new DocumentView(document.getName());

			// Medjusovno uvezivanje dodatog dokumenta i njegovog view-a
			document.setDocumentView(view);
			view.setDocument(document);

			// dodavanje DocumentView-a u odgovarajuci interni prozor
			project.getProjectView().addDocumentView(view);
		}
	}
}
