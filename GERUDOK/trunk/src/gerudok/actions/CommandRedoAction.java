package gerudok.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.Serializable;

import javax.swing.KeyStroke;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.SlotGraphic;

public class CommandRedoAction extends AbstractActionIcon implements Serializable {
	private static final long serialVersionUID = 1L;

	private SlotGraphic slot;

	public CommandRedoAction(SlotGraphic slot, Dimension d) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, iconGetter("/menu/redo.png", d));
		putValue(NAME, rb.getString("Redo"));
		putValue(SHORT_DESCRIPTION, rb.getString("RedoH"));
		this.slot = slot;
		//setEnabled(false);
	}

	public CommandRedoAction(Dimension d) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, iconGetter("/menu/redo.png", d));
		putValue(NAME, rb.getString("Redo"));
		putValue(SHORT_DESCRIPTION, rb.getString("RedoH"));
		setEnabled(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(slot == null)
		{
			slot = (SlotGraphic) MainFrameGerudok.getInstance().getTree().getLastSelectedPathComponent();
		}
		slot.getCommandManager().doCommand();
	}

}
