package gerudok.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.SwingUtilities;

import gerudok.gui.MainFrameGerudok;
import gerudok.gui.windowParts.JTreeGerudok;
import gerudok.model.Project;
import gerudok.model.Workspace;
import gerudok.view.ProjectView;

public class DeleteProjectAction implements ActionListener {
	
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
