package gerudok.actions;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.Document;
import gerudok.model.Page;
import gerudok.model.Project;
import gerudok.model.Slot;
import gerudok.model.Workspace;
import gerudok.view.DocumentView;
import gerudok.view.PageView;
import gerudok.view.ProjectView;
import gerudok.view.SlotView;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class DeleteNode extends AbstractActionIcon {

	public DeleteNode() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		putValue(SMALL_ICON, iconGetter("/menu/deleteAll.png"));
		putValue(NAME, rb.getString("Delete"));
		putValue(SHORT_DESCRIPTION, rb.getString("DeleteH"));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JTree tree = MainFrameGerudok.getInstance().getTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();

		if (((selectedComponent instanceof Workspace)) || (selectedComponent == null)) {
			return;

		} else if (selectedComponent instanceof Project) {
			// ProjectView view = null;
			Project project = (Project) selectedComponent;
			Workspace parent = (Workspace) project.getParent();
			// preuzimanje reference na view izbrisanog projekta kako bi se
			// mogao ukloniti iz prikaza
			// view = project.getProjectView();
			parent.deleteProject(project);
			// SwingUtilities.updateComponentTreeUI(tree);

			// Brisanje view-a za izbrisani projekat
			// try {
			// view.setClosed(true);
			// } catch (PropertyVetoException e1) {
			// e1.printStackTrace();
			// }
		} else if (selectedComponent instanceof Document) {
			Document document = (Document) selectedComponent;
			Project parent = (Project) document.getParent();
			parent.deleteDocument(document);

			// SwingUtilities.updateComponentTreeUI(tree);
			// Brisanje view-a izbrisanog dokumumenta iz odgovarajuceg prozora
			// ProjectView projectView = parent.getProjectView();
			// DocumentView documentView = document.getDocumentView();
			// projectView.removeDocumentView(documentView);
		} else if (selectedComponent instanceof Page) {
			Page page = (Page) selectedComponent;
			Document parent = (Document) page.getParent();
			parent.deletePage(page);
			//SwingUtilities.updateComponentTreeUI(tree);
			//DocumentView docView = parent.getDocumentView();
			//docView.removePageView(page.getPageView());
		} else if (selectedComponent instanceof Slot) {
			Slot slot = (Slot) selectedComponent;
			Page parent = (Page) slot.getParent();
			parent.deleteSlot(slot);
			
			//SwingUtilities.updateComponentTreeUI(tree);
			// Brisanje SlotView-a iz odgovarajuceg PageView-a
			//PageView pageView = parent.getPageView();
			//SlotView slotView = slot.getSlotView();
			//pageView.removeSlotView(slotView);
		}

	}

}
