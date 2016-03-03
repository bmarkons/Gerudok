package gerudok.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import gerudok.gui.MainFrameGerudok;
import gerudok.gui.windowParts.JTreeGerudok;
import gerudok.model.Project;

public class SaveProjectAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JTreeGerudok tree = MainFrameGerudok.getInstance().getTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();

		if (selectedComponent instanceof Project) {
			Project project = (Project) selectedComponent;
			File projectFile = project.getProjectFile();

			// Ukoliko fajl nije prethodno sniman, poziva se Save As
			if (projectFile == null) {
				new SaveAsProjectAction().actionPerformed(e);
				return;
			}
			
			if(!project.isProjectModified())
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
