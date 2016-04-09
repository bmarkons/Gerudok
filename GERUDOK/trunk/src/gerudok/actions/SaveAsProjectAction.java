package gerudok.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JTree;
import javax.swing.KeyStroke;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.Document;
import gerudok.model.Project;
import gerudok.xml.XmlManager;

@SuppressWarnings("serial")
public class SaveAsProjectAction extends AbstractActionIcon {

	public SaveAsProjectAction(Dimension d) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, iconGetter("/toolbar/save.png", d));
		putValue(NAME, rb.getString("SaveAs"));
		putValue(SHORT_DESCRIPTION, rb.getString("SaveAsH"));
		setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		// jfc.setFileFilter(new GerudokFileFilter());

		File workspaceFolder = null;
		JTree tree = MainFrameGerudok.getInstance().getTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();

		if (selectedComponent instanceof Project) {
			jfc.setDialogTitle("Select folder to save project");
			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			Project project = (Project) selectedComponent;

			// Biranje fajla u koji se snima projekat
			int choosedOption = jfc.showSaveDialog(MainFrameGerudok.getInstance());
			if (choosedOption != JFileChooser.APPROVE_OPTION) {
				return;
			}
			workspaceFolder = jfc.getSelectedFile();
			File projectFolder = new File(workspaceFolder.getAbsolutePath() + "\\" + project.getName());
			projectFolder.mkdir();
			File projectFile = new File(projectFolder + "\\" + ".PROJECT.gproj");

			project.setProjectModified(false);

			// Snimanje projekta u izabrani fajl.
			for (Document doc : project.getDocuments()) {
				ObjectOutputStream os;
				try {
					File documentFile = new File(projectFolder + "\\" + doc.getName() + ".gdoc");
					doc.setDocumentFile(documentFile);
					os = new ObjectOutputStream(new FileOutputStream(documentFile));
					os.writeObject(doc);

					os.close();

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

			// xml fajl sa strukturom unutar projekta
			XmlManager.createXmlFile(projectFile, project);
			
			project.setProjectFile(projectFile);
		}
	}

}
