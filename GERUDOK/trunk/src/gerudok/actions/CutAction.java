package gerudok.actions;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.commands.DeleteCommands;
import gerudok.gui.dialogs.SlotGraphicDialog.GraphicSlotToolbar;
import gerudok.model.ElementSelection;
import gerudok.model.GraphicSlotElement;
import gerudok.model.Project;
import gerudok.model.Slot;
import gerudok.model.SlotGraphic;
import gerudok.view.SlotGraphicView;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class CutAction extends AbstractActionIcon {

	public CutAction(Dimension d) {
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, iconGetter("/toolbar/cut.png", d));
		putValue(NAME, rb.getString("CutH"));
		putValue(SHORT_DESCRIPTION, rb.getString("Cut"));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component[] c = ((GraphicSlotToolbar) ((JComponent) e.getSource())
				.getParent()).getParent().getComponents();

		SlotGraphicView sgv = null;

		for (int i = 0; i < c.length; i++) {
			if (c[i] instanceof SlotGraphicView)
				sgv = (SlotGraphicView) c[i];
		}

		if (sgv != null) {
			if (sgv.getSelectionModel().getSelectionListSize() != 0) {

				ElementSelection content = new ElementSelection(sgv
						.getSelectionModel().getSelected());

				Slot s = sgv.getSlot();
				Project p = (Project)s.getParent().getParent().getParent();
								
				p.getClipboard().setContents(content, p);
				
				s.getCommandManager()
						.addCommand(new DeleteCommands(sgv, content.getList()));

				for (GraphicSlotElement element : content.getList()) {
					((SlotGraphic) sgv.getSlot())
							.removeGraphicSlotElement(element);
					sgv.getSelectionModel().removeAllFromSelectionList();
					sgv.repaint();
				}

			}
		}
	}
}
