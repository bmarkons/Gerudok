package gerudok.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JTree;
import javax.swing.KeyStroke;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.actions.manager.ActionManager;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.Project;

@SuppressWarnings("serial")
public class SaveProjectAction extends AbstractActionIcon {

	public SaveProjectAction(Dimension d) {
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, iconGetter("/menu/save.png", d));
		putValue(NAME, rb.getString("Save"));
		putValue(SHORT_DESCRIPTION, rb.getString("SaveH"));

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
				//new SaveAsProjectAction().actionPerformed(e);
				return;
			}

			if (!project.isProjectModified())
				return;

			project.setProjectModified(false);

			// Zapisivanje projekta u datoteku
			ObjectOutputStream os;
			try {

				os = new ObjectOutputStream(new FileOutputStream(projectFile));
				os.writeObject(project);

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
