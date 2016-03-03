package gerudok.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import gerudok.gui.MainFrameGerudok;
import gerudok.gui.windowParts.JTreeGerudok;
import gerudok.model.Project;
import gerudok.model.Workspace;
import gerudok.view.ProjectView;

public class NewProjectAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// dodavanje novog projekta u JTree
		JTreeGerudok tree = MainFrameGerudok.getInstance().getTree();
		TreePath path = tree.getSelectionPath();
		tree.expandPath(path);
		Project project = new Project();
		((Workspace) tree.getModel().getRoot()).addProject(project);
		SwingUtilities.updateComponentTreeUI(tree);
		createProjectViewer(project);
		// Prikazi dodati projekat u prikaznom delu

	}

	public static void createProjectViewer(Project project) {
		JDesktopPane desktopPane = MainFrameGerudok.getInstance()
				.getDesktopPane();
		ProjectView view = new ProjectView(project.getName());

		// Medjusobno uvezivanje dodatog projekta i njegovog view-a
		project.setProjectView(view);
		view.setProject(project);
		// Postavljanje naslova dodatog projectView-a
		view.setTitle(project.getName());
		// Dodavanje u desktopPane i prikazivanje
		desktopPane.add(view);
		view.setVisible(true);

	}
}
