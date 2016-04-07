package gerudok.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JTree;
import javax.swing.KeyStroke;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.filters.GerudokFileFilter;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.Project;

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
		jfc.setFileFilter(new GerudokFileFilter());
		jfc.setDialogTitle("Save Gerudok project as");
		File projectFile = null;
		JTree tree = MainFrameGerudok.getInstance().getTree();
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
			
			if(!projectFile.getName().contains(".gpf")){
				projectFile = new File (projectFile.getAbsolutePath() + ".gpf");
			}
			
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
