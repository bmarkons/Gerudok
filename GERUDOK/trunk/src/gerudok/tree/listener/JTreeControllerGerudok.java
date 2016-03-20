package gerudok.tree.listener;

import java.beans.PropertyVetoException;

import gerudok.model.Document;
import gerudok.model.Page;
import gerudok.model.Project;
import gerudok.model.Slot;
import gerudok.view.DocumentView;
import gerudok.view.PageView;
import gerudok.view.ProjectView;
import gerudok.view.SlotView;

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
		if (selectedComponent instanceof Project) {
			Project project = (Project) selectedComponent;
			//ProjectView view = project.getProjectView();
			// Postavljanje internog prozora projekta u fokus
			// try {
			// view.setSelected(true);
			// } catch (PropertyVetoException e1) {
			// e1.printStackTrace();
			// }

		} else if (selectedComponent instanceof Document) {
			/*
			 * Selektovanje slota u stablu dovodi, redom, u fokus interni prozor
			 * projekta, zatim tab dokumenta.
			 */
			Document document = (Document) selectedComponent;
			Project parent = (Project) document.getParent();
//			ProjectView pView = parent.getProjectView();
//			// Postavljanje internog prozora projekta u fokus
//			try {
//				pView.setSelected(true);
//			} catch (PropertyVetoException e1) {
//				e1.printStackTrace();
//			}
//			// Postavljanje tab-a documenta u fokus
//			DocumentView dView = document.getDocumentView();
//			pView.getTabbedPane().setSelectedComponent(dView);

		} else if (selectedComponent instanceof Page) {
			/*
			 * Selektovanje slota u stablu dovodi, redom, u fokus interni prozor
			 * projekta, zatim tab dokumenta.
			 * 
			 * NAPOMENA: trebalo bi jos da skroluje na prikaz stranice
			 */
			Page page = (Page) selectedComponent;
			Document document = (Document) page.getParent();
			Project project = (Project) document.getParent();
			// Postavljanje internog prozora projekta u fokus
//			ProjectView projectView = project.getProjectView();
//			try {
//				projectView.setSelected(true);
//			} catch (PropertyVetoException e1) {
//				e1.printStackTrace();
//			}
//			// Postavljanje tab-a documenta u fokus
//			DocumentView documentView = document.getDocumentView();
//			projectView.getTabbedPane().setSelectedComponent(documentView);
//			PageView pageView = page.getPageView();
//			pageView.requestFocus();

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
			Project project = (Project) document.getParent();
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
		}
	}
}