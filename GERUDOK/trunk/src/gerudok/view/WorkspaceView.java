package gerudok.view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;

import gerudok.events.ProjectEvent;
import gerudok.events.ProjectEvent.ProjectEventType;
import gerudok.events.WorkspaceEvent;
import gerudok.events.WorkspaceEvent.WorkspaceEventType;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.Document;

public class WorkspaceView extends JDesktopPane implements Observer {
	private static final long serialVersionUID = -8620524935845234444L;

	@Override
	public void update(Observable arg0, Object arg1) {
		WorkspaceEvent eventObject = (WorkspaceEvent) arg1;

		if (eventObject.getType() == WorkspaceEventType.ADD_PROJECT) {

			// Dodavanje view-a i registrovanje kao osluskivaca na odgovarajuci
			// project
			ProjectView projectView = new ProjectView(eventObject.getProject());
			add(projectView);
			projectView.setVisible(true);
			eventObject.getProject().addObserver(projectView);

			ArrayList<Document> documents = eventObject.getProject().getDocuments();
			for (Document document : documents)
				eventObject.getProject().notifyObservers(new ProjectEvent(ProjectEventType.ADD_DOCUMENT, document));
			

		} else if (eventObject.getType() == WorkspaceEventType.REMOVE_PROJECT) {

			// Uklanjanje view-a i uklanjanje iz liste osluskivaca odgovarajuceg
			// project-a
			for (JInternalFrame frame : getAllFrames()) {
				ProjectView projectView = (ProjectView) frame;
				if (projectView.getProject().equals(eventObject.getProject()))
					remove(projectView);
			}
		}

		SwingUtilities.updateComponentTreeUI(MainFrameGerudok.getInstance().getTree());
	}
}
