package gerudok.actions;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.commands.DeleteCommand;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.Document;
import gerudok.model.GraphicSlotElement;
import gerudok.model.Page;
import gerudok.model.Project;
import gerudok.model.Slot;
import gerudok.model.SlotGraphic;
import gerudok.model.Workspace;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTree;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class DeleteNode extends AbstractActionIcon {

	public DeleteNode(Dimension d) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		putValue(SMALL_ICON, iconGetter("/menu/deleteAll.png", d));
		putValue(NAME, rb.getString("Delete"));
		putValue(SHORT_DESCRIPTION, rb.getString("DeleteH"));
		setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JTree tree = MainFrameGerudok.getInstance().getTree();
		Object selectedComponent = tree.getLastSelectedPathComponent();

		if (((selectedComponent instanceof Workspace)) || (selectedComponent == null)) {
			return;

		} else if (selectedComponent instanceof Project) {

			Project project = (Project) selectedComponent;
			Workspace parent = (Workspace) project.getParent();
			parent.deleteProject(project);

		} else if (selectedComponent instanceof Document) {
			
			Document document = (Document) selectedComponent;
			Project parent = (Project) document.getParent();
			parent.deleteDocument(document);

		} else if (selectedComponent instanceof Page) {
			
			Page page = (Page) selectedComponent;
			Document parent = (Document) page.getParent();
			parent.deletePage(page);
			
		} else if (selectedComponent instanceof Slot) {
			
			Slot slot = (Slot) selectedComponent;
			Page parent = (Page) slot.getParent();
			parent.deleteSlot(slot);
			
		} else if (selectedComponent instanceof GraphicSlotElement) {
			
			GraphicSlotElement element = (GraphicSlotElement) selectedComponent;
			SlotGraphic parent = (SlotGraphic) element.getParent();
			
			parent.getCommandManager().addCommand(new DeleteCommand(parent, element));
			
		}

	}

}
