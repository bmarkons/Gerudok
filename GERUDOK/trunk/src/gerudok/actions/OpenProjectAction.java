package gerudok.actions;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.Document;
import gerudok.model.Project;
import gerudok.model.Workspace;
import gerudok.xml.ParseResult;
import gerudok.xml.XmlManager;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.tree.TreePath;

@SuppressWarnings("serial")
public class OpenProjectAction extends AbstractActionIcon {

	public OpenProjectAction(Dimension d) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_P);
		putValue(SMALL_ICON, iconGetter("/toolbar/openproj.png", d));
		putValue(NAME, rb.getString("Open"));
		putValue(SHORT_DESCRIPTION, rb.getString("OpenH"));

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Select project folder to open");//rb
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int choosen = jfc.showOpenDialog(MainFrameGerudok.getInstance());
		if (choosen != JFileChooser.APPROVE_OPTION) {
			return;
		}

		File projectFolder = jfc.getSelectedFile();

		File projectFile = null;
		for (File file : projectFolder.listFiles()) {
			if (file.getName().endsWith(".gproj")) {
				projectFile = file;
				break;
			}
		}
		if (projectFile == null) {
			JOptionPane.showMessageDialog(MainFrameGerudok.getInstance(), "Selected folder is not Gerudok project.",
					"Open project error", JOptionPane.ERROR_MESSAGE);//rb
			return;
		}

		ParseResult parseResult = XmlManager.parseXmlFile(projectFile);

		Project project = new Project();
		project.setName(parseResult.getProjectName());
		project.setProjectFile(projectFile);
		for (Document doc : parseResult.getDocuments()) {
			doc.setParent(project);
			doc.addObserver(project);
			project.addDocument(doc);
		}

		JTree tree = MainFrameGerudok.getInstance().getTree();
		TreePath path = tree.getSelectionPath();
		tree.expandPath(path);

		((Workspace) tree.getModel().getRoot()).openProject(project);
		
	}
}
