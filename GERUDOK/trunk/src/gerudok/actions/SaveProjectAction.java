package gerudok.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

import javax.swing.JTree;
import javax.swing.KeyStroke;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.actions.manager.ActionManager;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.Document;
import gerudok.model.Project;
import gerudok.xml.XmlManager;

@SuppressWarnings("serial")
public class SaveProjectAction extends AbstractActionIcon {

	public SaveProjectAction(Dimension d) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, iconGetter("/menu/save.png", d));
		putValue(NAME, rb.getString("Save"));
		putValue(SHORT_DESCRIPTION, rb.getString("SaveH"));
		setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTree tree = MainFrameGerudok.getInstance().getTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();

		if (selectedComponent instanceof Project) {
			Project project = (Project) selectedComponent;
			File projectFile = project.getProjectFile();

			// Ukoliko fajl nije prethodno sniman, poziva se Save As
			if (projectFile == null) {
				ActionManager.getInstance().getSaveas().actionPerformed(e);
				return;
			}
			if (!project.isProjectModified())
				return;

			// Zapisivanje dokumenata projekta u projektni folder
			for (Document doc : project.getDocuments()) {
				File documentFile = doc.getDocumentFile();

				// Ukoliko ne dokument nema svoj fajl, kreiranje jednog novog
				if (documentFile == null) {
					documentFile = new File(projectFile.getParent() + "\\" + doc.getName() + ".gdoc");
					doc.setDocumentFile(documentFile);
				}

				// Ukoliko je doslo do promene imena dokumenta,sledi promena
				// imena odgovarajuceg fajla
				if (!documentFile.getName().equals(doc.getName() + ".gdoc")) {
					try {
						Files.deleteIfExists(documentFile.toPath());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					doc.setDocumentFile(new File(projectFile.getParent() + "\\" + doc.getName() + ".gdoc"));
				}

				// Zapis dokumenta u fajl
				ObjectOutputStream os;
				try {
					os = new ObjectOutputStream(new FileOutputStream(doc.getDocumentFile()));
					os.writeObject(doc);

					os.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			// xml fajl sa strukturom unutar projekta
			XmlManager.createXmlFile(projectFile, project);

			project.setProjectModified(false);
		}
	}
}
