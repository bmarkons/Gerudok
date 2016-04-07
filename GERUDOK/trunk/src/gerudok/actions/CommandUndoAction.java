package gerudok.actions;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.Serializable;

import javax.swing.KeyStroke;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.model.SlotGraphic;

public class CommandUndoAction extends AbstractActionIcon implements Serializable {

	private static final long serialVersionUID = 1L;

	private SlotGraphic slot;

	public CommandUndoAction(SlotGraphic slot, Dimension d) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, iconGetter("/menu/undo.png", d));
		putValue(NAME, rb.getString("Undo"));
		putValue(SHORT_DESCRIPTION, rb.getString("UndoH"));
		this.slot = slot;
	}

	public CommandUndoAction(Dimension d) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, iconGetter("/menu/undo.png", d));
		putValue(NAME, rb.getString("Undo"));
		putValue(SHORT_DESCRIPTION, rb.getString("UndoH"));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		slot.getCommandManager().undoCommand();
	}

}
