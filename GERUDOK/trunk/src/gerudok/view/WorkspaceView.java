package gerudok.view;

import java.beans.PropertyVetoException;
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
import gerudok.model.Page;
import gerudok.model.Project;
import gerudok.model.Slot;
import gerudok.model.SlotGraphic;
import gerudok.model.SlotText;

public class WorkspaceView extends JDesktopPane implements Observer {
	private static final long serialVersionUID = -8620524935845234444L;

	@Override
	public void update(Observable arg0, Object arg1) {
		WorkspaceEvent eventObject = (WorkspaceEvent) arg1;
		Project project = eventObject.getProject();

		if (eventObject.getType() == WorkspaceEventType.ADD_PROJECT) {

			// Dodavanje view-a i registrovanje kao osluskivaca na odgovarajuci
			// project
			ProjectView projectView = new ProjectView(project);
			add(projectView);
			projectView.setVisible(true);
			project.addObserver(projectView);

//			ArrayList<Document> documents = eventObject.getProject().getDocuments();
//			for (Document document : documents) {
//
//				project.notifyObservers(new ProjectEvent(ProjectEventType.ADD_DOCUMENT, document));
//			}

		} else if (eventObject.getType() == WorkspaceEventType.REMOVE_PROJECT) {

			// Uklanjanje view-a i uklanjanje iz liste osluskivaca odgovarajuceg
			// project-a
			for (JInternalFrame frame : getAllFrames()) {
				ProjectView projectView = (ProjectView) frame;
				if (projectView.getProject().equals(eventObject.getProject())) {
					try {
						projectView.setClosed(true);
					} catch (PropertyVetoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else if (eventObject.getType() == WorkspaceEventType.OPEN_PROJECT) {

			ProjectView projectView = new ProjectView(project);
			add(projectView);
			projectView.setVisible(true);
			project.addObserver(projectView);
			
			for(Document document : project.getDocuments()){
				DocumentView documentView = new DocumentView(document);
				projectView.addDocumentView(documentView);
				document.addObserver(documentView);
				for(Page page : document.getPages()){
					PageView pageView = new PageView(page);
					documentView.addPageView(pageView);
					page.addObserver(pageView);
					documentView.validate();
					for(Slot slot : page.getSlots()){
						SlotView slotView = null;
						if (slot instanceof SlotGraphic) {
							slotView = new SlotGraphicView(slot, false);
							pageView.addSlotView(slotView);
							slot.addObserver(slotView);
						} else if (slot instanceof SlotText) {
							slotView = new SlotTextView(slot, false);
							pageView.addSlotView(slotView);
							slot.addObserver(slotView);

							if (((SlotText) slot).getText() != null) {
								slot.notifyObservers();
							}
						}
					}
				}
			}
			
		}

		SwingUtilities.updateComponentTreeUI(MainFrameGerudok.getInstance().getTree());
	}
}
