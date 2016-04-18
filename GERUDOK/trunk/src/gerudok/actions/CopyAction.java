package gerudok.actions;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.gui.dialogs.SlotGraphicDialog.GraphicSlotToolbar;
import gerudok.model.ElementSelection;
import gerudok.model.Project;
import gerudok.model.Slot;
import gerudok.view.SlotGraphicView;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class CopyAction extends AbstractActionIcon {

	public CopyAction(Dimension d) {
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, iconGetter("/toolbar/copy.png", d));
		putValue(NAME, rb.getString("CopyH"));
		putValue(SHORT_DESCRIPTION, rb.getString("Copy"));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Component[] c = ((GraphicSlotToolbar)((JComponent)e.getSource()).getParent()).getParent().getComponents();
		
		SlotGraphicView sgv = null;
		
		for(int i=0; i<c.length; i++) {
			if(c[i] instanceof SlotGraphicView)
				sgv = (SlotGraphicView) c[i];
		}
		
		
		if (sgv!=null) {
			if (sgv.getSelectionModel().getSelectionListSize() != 0) {
				
				Slot s = sgv.getSlot();
				Project p = (Project)s.getParent().getParent().getParent();
				
				ElementSelection content = new ElementSelection(sgv.getSelectionModel().getSelected());
				p.getClipboard().setContents(content, p);
			}
		}
	}

}
