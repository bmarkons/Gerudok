package gerudok.tree.listener;

import java.beans.PropertyVetoException;
import java.util.ArrayList;

import gerudok.actions.manager.ActionManager;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.Document;
import gerudok.model.Page;
import gerudok.model.Project;
import gerudok.model.Slot;
import gerudok.model.SlotGraphic;
import gerudok.model.Workspace;
import gerudok.view.DocumentView;
import gerudok.view.PageView;
import gerudok.view.ProjectView;
import gerudok.view.SlotView;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class JTreeControllerGerudok implements TreeSelectionListener {

	// Postavljanje odgovarajuci VIEW selektovanog cvora u fokus

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// putanja selektovanog cvora
		TreePath path = e.getPath();
		// preuzima se poslednji cvor u putanji (selektovani cvor)
		Object selectedComponent = path.getLastPathComponent();

		/*
		 * Selektovanje slota u stablu dovodi u fokus interni prozor projekta.
		 */
		if (selectedComponent instanceof Workspace) {

			ActionManager.getInstance().getSave().setEnabled(false);
			ActionManager.getInstance().getSaveas().setEnabled(false);

			ActionManager.getInstance().getNewdocument().setEnabled(false);
			ActionManager.getInstance().getNewpage().setEnabled(false);
			ActionManager.getInstance().getNewgraphicslot().setEnabled(false);
			ActionManager.getInstance().getNewtextslot().setEnabled(false);

			ActionManager.getInstance().getDeletenode().setEnabled(false);

			ActionManager.getInstance().getUndo().setEnabled(false);
			ActionManager.getInstance().getRedo().setEnabled(false);

			ActionManager.getInstance().getImportAction().setEnabled(true);

		} else if (selectedComponent instanceof Project) {
			Project project = (Project) selectedComponent;

			setProjectViewInFront(project);

			ActionManager.getInstance().getSave().setEnabled(true);
			ActionManager.getInstance().getSaveas().setEnabled(true);

			ActionManager.getInstance().getNewdocument().setEnabled(true);
			ActionManager.getInstance().getNewpage().setEnabled(false);
			ActionManager.getInstance().getNewgraphicslot().setEnabled(false);
			ActionManager.getInstance().getNewtextslot().setEnabled(false);

			ActionManager.getInstance().getDeletenode().setEnabled(true);

			ActionManager.getInstance().getUndo().setEnabled(false);
			ActionManager.getInstance().getRedo().setEnabled(false);

			ActionManager.getInstance().getImportAction().setEnabled(true);

		} else if (selectedComponent instanceof Document) {
			/*
			 * Selektovanje slota u stablu dovodi, redom, u fokus interni prozor
			 * projekta, zatim tab dokumenta.
			 */
			Document document = (Document) selectedComponent;
			Project project = null;
			if (document.isShared()) {
				Object[] pathElements = path.getPath();
				ArrayList<Project> projects = new ArrayList<Project>();
				projects.addAll(document.getAllParents());
				projects.add((Project) document.getParent());
				Object obj = pathElements[pathElements.length - 2];
				for (Project p : projects) {
					if (obj.equals(p)) {
						project = p;
					}
				}
			} else {
				project = (Project) document.getParent();
			}

			ProjectView projectView = setProjectViewInFront(project);
			setDocumentViewInFront(document, projectView);

			ActionManager.getInstance().getSave().setEnabled(false);
			ActionManager.getInstance().getSaveas().setEnabled(false);

			ActionManager.getInstance().getNewdocument().setEnabled(false);
			ActionManager.getInstance().getNewpage().setEnabled(true);
			ActionManager.getInstance().getNewgraphicslot().setEnabled(false);
			ActionManager.getInstance().getNewtextslot().setEnabled(false);

			ActionManager.getInstance().getDeletenode().setEnabled(true);

			ActionManager.getInstance().getUndo().setEnabled(false);
			ActionManager.getInstance().getRedo().setEnabled(false);

			ActionManager.getInstance().getImportAction().setEnabled(false);

		} else if (selectedComponent instanceof Page) {
			/*
			 * Selektovanje slota u stablu dovodi, redom, u fokus interni prozor
			 * projekta, zatim tab dokumenta.
			 * 
			 * NAPOMENA: trebalo bi jos da skroluje na prikaz stranice
			 */
			Page page = (Page) selectedComponent;
			Document document = (Document) page.getParent();
			Project project = null;
			if (document.isShared()) {
				Object[] pathElements = path.getPath();
				ArrayList<Project> projects = new ArrayList<Project>();
				projects.addAll(document.getAllParents());
				projects.add((Project) document.getParent());
				Object obj = pathElements[pathElements.length - 3];
				for (Project p : projects) {
					if (obj.equals(p)) {
						project = p;
					}
				}

			} else {
				project = (Project) document.getParent();
			}

			ProjectView projectView = setProjectViewInFront(project);
			DocumentView documentView = setDocumentViewInFront(document, projectView);
			setPageViewInFront(page, documentView);
			// TODO Potrebno odraditi skrolovanje na izabranu stranicu

			// Postavljanje internog prozora projekta u fokus
			// ProjectView projectView = project.getProjectView();
			// try {
			// projectView.setSelected(true);
			// } catch (PropertyVetoException e1) {
			// e1.printStackTrace();
			// }
			// // Postavljanje tab-a documenta u fokus
			// DocumentView documentView = document.getDocumentView();
			// projectView.getTabbedPane().setSelectedComponent(documentView);
			// PageView pageView = page.getPageView();
			// pageView.requestFocus();

			ActionManager.getInstance().getSave().setEnabled(false);
			ActionManager.getInstance().getSaveas().setEnabled(false);

			ActionManager.getInstance().getNewdocument().setEnabled(false);
			ActionManager.getInstance().getNewpage().setEnabled(false);
			ActionManager.getInstance().getNewgraphicslot().setEnabled(true);
			ActionManager.getInstance().getNewtextslot().setEnabled(true);

			ActionManager.getInstance().getDeletenode().setEnabled(true);

			ActionManager.getInstance().getUndo().setEnabled(false);
			ActionManager.getInstance().getRedo().setEnabled(false);

			ActionManager.getInstance().getImportAction().setEnabled(false);

		} else if (selectedComponent instanceof Slot) {
			/*
			 * Selektovanje slota u stablu dovodi, redom, u fokus interni prozor
			 * projekta, zatim tab dokumenta.
			 * 
			 * NAPOMENA: trebalo bi jos da skroluje na stranicu a zatim i na
			 * slot
			 */
			Slot slot = (Slot) selectedComponent;
			Page page = (Page) slot.getParent();
			Document document = (Document) page.getParent();
			
			Project project = null;
			if (document.isShared()) {
				Object[] pathElements = path.getPath();
				ArrayList<Project> projects = new ArrayList<Project>();
				projects.addAll(document.getAllParents());
				projects.add((Project) document.getParent());
				Object obj = pathElements[pathElements.length - 4];
				for (Project p : projects) {
					if (obj.equals(p)) {
						project = p;
					}
				}

			} else {
				project = (Project) document.getParent();
			}

			ProjectView projectView = setProjectViewInFront(project);
			DocumentView documentView = setDocumentViewInFront(document, projectView);
			PageView pageView = setPageViewInFront(page, documentView);
			setSlotViewInFront(slot, pageView);

			// TODO Potrebno odraditi skrolovanje na izabrani slot

			// Postavljanje internog prozora projekta u fokus
			// ProjectView pView = project.getProjectView();
			// try {
			// pView.setSelected(true);
			// } catch (PropertyVetoException e1) {
			// e1.printStackTrace();
			// }
			// // Postavljanje tab-a documenta u fokus
			// DocumentView dView = document.getDocumentView();
			// pView.getTabbedPane().setSelectedComponent(dView);
			// SlotView sView = slot.getSlotView();
			// sView.requestFocus();

			ActionManager.getInstance().getSave().setEnabled(false);
			ActionManager.getInstance().getSaveas().setEnabled(false);

			ActionManager.getInstance().getNewdocument().setEnabled(false);
			ActionManager.getInstance().getNewpage().setEnabled(false);
			ActionManager.getInstance().getNewgraphicslot().setEnabled(false);
			ActionManager.getInstance().getNewtextslot().setEnabled(false);

			ActionManager.getInstance().getDeletenode().setEnabled(true);

			ActionManager.getInstance().getImportAction().setEnabled(false);

			if (selectedComponent instanceof SlotGraphic) {
				ActionManager.getInstance().getUndo().setEnabled(true);
				ActionManager.getInstance().getRedo().setEnabled(true);
			} else {
				ActionManager.getInstance().getUndo().setEnabled(false);
				ActionManager.getInstance().getRedo().setEnabled(false);
			}

		}
	}

	private SlotView setSlotViewInFront(Slot slot, PageView pageView) {
		for (SlotView slotView : pageView.getSlotViews()) {
			if (slotView.getSlot().equals(slot)) {
				pageView.scrollRectToVisible(slotView.getBounds());
				return slotView;
			}
		}
		return null;
	}

	private PageView setPageViewInFront(Page page, DocumentView documentView) {
		for (PageView pageView : documentView.getDocumentPanel().getPageViews()) {
			if (pageView.getPage().equals(page)) {
				pageView.scrollRectToVisible(pageView.getBounds());
				return pageView;
			}
		}
		return null;
	}

	private DocumentView setDocumentViewInFront(Document document, ProjectView projectView) {
		JTabbedPane tabbedPane = projectView.getTabbedPane();
		int tabCount = tabbedPane.getTabCount();
		for (int i = 0; i < tabCount; i++) {
			DocumentView documentView = (DocumentView) tabbedPane.getComponentAt(i);
			if (document.equals(documentView.getDocument())) {
				tabbedPane.setSelectedComponent(documentView);
				return documentView;
			}
		}
		return null;
	}

	private ProjectView setProjectViewInFront(Project project) {
		JInternalFrame[] projectViews = MainFrameGerudok.getInstance().getWorkspaceView().getAllFrames();
		for (JInternalFrame frame : projectViews) {
			Project p = ((ProjectView) frame).getProject();
			if (p.equals(project)) {
				try {
					((ProjectView) frame).setSelectedFromTree(true);
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
				return (ProjectView) frame;
			}
		}
		return null;
	}

}