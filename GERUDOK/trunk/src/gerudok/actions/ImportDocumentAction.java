package gerudok.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JTree;
import javax.swing.KeyStroke;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.filters.DocumentFileFilter;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.Document;
import gerudok.model.Project;
import gerudok.model.Workspace;

public class ImportDocumentAction extends AbstractActionIcon {
	private static final long serialVersionUID = 8208866974431745427L;

	public ImportDocumentAction(Dimension d) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_I);
		putValue(SMALL_ICON, iconGetter("/toolbar/importdoc.png", d));
		putValue(NAME, rb.getString("Import"));
		putValue(SHORT_DESCRIPTION, rb.getString("ImportH"));

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JTree tree = MainFrameGerudok.getInstance().getTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();

		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Select document file to import");// rb
		jfc.setFileFilter(new DocumentFileFilter());

		int choosen = jfc.showOpenDialog(MainFrameGerudok.getInstance());
		if (choosen != JFileChooser.APPROVE_OPTION) {
			return;
		}

		File docFile = jfc.getSelectedFile();
		Document doc = null;
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(docFile));
			doc = (Document) is.readObject();
			
			is.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		if(doc == null){
			return;
		}

		if (selectedComponent instanceof Project) {
			Project project = (Project) selectedComponent;
			doc.setParent(project);
			
			project.addDocument(doc);
		}else if(selectedComponent instanceof Workspace){
			// TODO
		}
	}

}
