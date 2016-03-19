package gerudok.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JDesktopPane;
import javax.swing.SwingUtilities;

import gerudok.events.WorkspaceEvent;
import gerudok.events.WorkspaceEvent.Type;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.Project;

public class WorkspaceView extends JDesktopPane implements Observer {
	private static final long serialVersionUID = -8620524935845234444L;

	@Override
	public void update(Observable arg0, Object arg1) {
		WorkspaceEvent eventObject = (WorkspaceEvent) arg1;
		
		if (eventObject.getType() == Type.ADD_PROJECT) {
			//Dodavanje view-a i registrovanje kao osluskivaca na odgovarajuci project
			ProjectView projectView = addProjectView(eventObject.getProject());
			eventObject.getProject().addObserver(projectView);
		}else if(eventObject.getType() == Type.REMOVE_PROJECT){
			//Uklanjanje view-a i uklanjanje iz liste osluskivaca odgovarajuceg project-a
			removeProjectView(eventObject.getProject());
			//TODO uklanjanje osluskivaca
		}
		
		SwingUtilities.updateComponentTreeUI(MainFrameGerudok.getInstance().getTree());
	}
	
	private ProjectView addProjectView(Project project){
		ProjectView projectView = new ProjectView(project.getName());
		// TODO
		
		return projectView;
	}
	
	private void removeProjectView(Project project){
		// TODO
		
	}

}
