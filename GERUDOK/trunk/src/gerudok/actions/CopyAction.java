package gerudok.actions;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.gui.MainFrameGerudok;
import gerudok.gui.dialogs.SlotGraphicDialog;
import gerudok.gui.dialogs.SlotGraphicDialog.GraphicSlotToolbar;
import gerudok.model.ElementSelection;
import gerudok.model.SlotGraphic;
import gerudok.view.SlotGraphicView;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class CopyAction extends AbstractActionIcon {

	public CopyAction(Dimension d) {
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, iconGetter("/toolbar/about.png", d));
		putValue(NAME, "Copy");
		putValue(SHORT_DESCRIPTION, "Copy");

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
			
			System.out.println("copy");
			
			if (sgv.getSelectionModel().getSelectionListSize() == 0) {
				Clipboard clipboard = sgv.getClipboard();
				
				ElementSelection content = new ElementSelection(sgv.getSelectionModel().getSelected());
				clipboard.setContents(content, MainFrameGerudok.getInstance());
			}
		}
	}

}
