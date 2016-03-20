package gerudok.actions;

import gerudok.gui.MainFrameGerudok;
import gerudok.model.Project;
import gerudok.view.ProjectView;
import gerudok.view.WorkspaceView;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JInternalFrame;

public class DisplayProject extends AbstractAction {
	private static final long serialVersionUID = 5194168022973916790L;

	@Override
	public void actionPerformed(ActionEvent e) {
		Object selectedComponent = MainFrameGerudok.getInstance().getTree().getLastSelectedPathComponent();
		if (selectedComponent instanceof Project) {
			Project project = (Project) selectedComponent;

			//?!
			WorkspaceView workspaceView = MainFrameGerudok.getInstance().getWorkspaceView();
			for (JInternalFrame frame : workspaceView.getAllFrames()) {
				if(((ProjectView)frame).getProject().equals(project))
					frame.setVisible(true);
			}
			// ProjectView view = project.getProjectView();
			// view.setVisible(true);
		}
	}
}
