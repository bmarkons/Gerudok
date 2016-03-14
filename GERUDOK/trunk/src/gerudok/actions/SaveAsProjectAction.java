package gerudok.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.KeyStroke;

import gerudok.GerudokFileFilter;
import gerudok.gui.MainFrameGerudok;
import gerudok.gui.windowParts.JTreeGerudok;
import gerudok.model.Project;

@SuppressWarnings("serial")
public class SaveAsProjectAction extends AbstractActionIcon {
	
	public SaveAsProjectAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, iconGetter("/toolbar/save.png"));
		putValue(NAME, "Save as");
		putValue(SHORT_DESCRIPTION, "Save as (CTRL+F1)");

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new GerudokFileFilter());
		jfc.setDialogTitle("Save Gerudok project as");
		File projectFile = null;
		JTreeGerudok tree = MainFrameGerudok.getInstance().getTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();

		if (selectedComponent instanceof Project) {
			Project project = (Project) selectedComponent;

			// Biranje fajla u koji se snima projekat
			int choosedOption = jfc.showSaveDialog(MainFrameGerudok.getInstance());
			if (choosedOption == JFileChooser.APPROVE_OPTION)
				projectFile = jfc.getSelectedFile();
			else
				return;

			project.setProjectModified(false);
			project.setProjectFile(projectFile);

			// Snimanje projekta u izabrani fajl.
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
