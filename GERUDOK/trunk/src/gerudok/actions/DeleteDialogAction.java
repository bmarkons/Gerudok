package gerudok.actions;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.commands.DeleteCommands;
import gerudok.gui.dialogs.SlotGraphicDialog.GraphicSlotToolbar;
import gerudok.model.ElementSelection;
import gerudok.model.GraphicSlotElement;
import gerudok.model.Slot;
import gerudok.model.SlotGraphic;
import gerudok.view.SlotGraphicView;

@SuppressWarnings("serial")
public class DeleteDialogAction extends AbstractActionIcon {

	public DeleteDialogAction(Dimension d) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		putValue(SMALL_ICON, iconGetter("/menu/deleteAll.png", d));
		putValue(NAME, rb.getString("Delete"));
		putValue(SHORT_DESCRIPTION, rb.getString("DeleteH"));
		setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component[] c = ((GraphicSlotToolbar) ((JComponent) e.getSource()).getParent()).getParent().getComponents();

		SlotGraphicView sgv = null;

		for (int i = 0; i < c.length; i++) {
			if (c[i] instanceof SlotGraphicView)
				sgv = (SlotGraphicView) c[i];
		}

		if (sgv != null) {
			if (sgv.getSelectionModel().getSelectionListSize() != 0) {

				ElementSelection content = new ElementSelection(sgv.getSelectionModel().getSelected());

				Slot s = sgv.getSlot();

				s.getCommandManager().addCommand(new DeleteCommands(sgv, content.getList()));

				for (GraphicSlotElement element : content.getList()) {
					((SlotGraphic) sgv.getSlot()).removeGraphicSlotElement(element);
					sgv.getSelectionModel().removeAllFromSelectionList();
					sgv.repaint();
				}

			}

		}
	}
}