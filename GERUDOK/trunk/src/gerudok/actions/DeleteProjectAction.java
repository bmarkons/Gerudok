package gerudok.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import gerudok.gui.MainFrameGerudok;
import gerudok.gui.windowParts.JTreeGerudok;
import gerudok.model.Project;
import gerudok.model.Workspace;
import gerudok.view.ProjectView;

@SuppressWarnings("serial")
public class DeleteProjectAction extends AbstractActionIcon {
	
	public DeleteProjectAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.SHIFT_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_P);
		putValue(SMALL_ICON, iconGetter("/menu/delproj.png"));
		putValue(NAME, "Delete project");
		putValue(SHORT_DESCRIPTION, "Delete project (SHIFT+N)");

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Brisanje selektovanog projekta
		JTreeGerudok tree = MainFrameGerudok.getInstance().getTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();
		ProjectView view = null;
		if (selectedComponent instanceof Project) {
			Project project = (Project) selectedComponent;
			Workspace parent = (Workspace) project.getParent();
			// preuzimanje reference na view izbrisanog projekta kako bi se
			// mogao ukloniti iz prikaza
			view = project.getProjectView();
			parent.deleteProject(project);
			SwingUtilities.updateComponentTreeUI(tree);

			// Brisanje view-a za izbrisani projekat
			try {
				view.setClosed(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		}
	}
}
