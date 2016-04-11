package gerudok.actions;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.gui.MainFrameGerudok;
import gerudok.gui.dialogs.SlotGraphicDialog.GraphicSlotToolbar;
import gerudok.model.ElementSelection;
import gerudok.model.GraphicSlotElement;
import gerudok.model.SlotGraphic;
import gerudok.view.SlotGraphicView;

@SuppressWarnings("serial")
public class CutAction extends AbstractActionIcon{

	public CutAction(Dimension d) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, iconGetter("/toolbar/about.png",d));
		putValue(NAME, "Cut");
		putValue(SHORT_DESCRIPTION, "Cut");

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
				
				ElementSelection content = new ElementSelection(sgv.getSelectionModel().getSelected());
				MainFrameGerudok.getInstance().getClipboard().setContents(content, MainFrameGerudok.getInstance());
				
				for (GraphicSlotElement element : content.getList()) {
					((SlotGraphic)sgv.getSlot()).removeGraphicSlotElement(element);
					sgv.getSelectionModel().removeAllFromSelectionList();
				}		
			}
		}
	}

	
}
