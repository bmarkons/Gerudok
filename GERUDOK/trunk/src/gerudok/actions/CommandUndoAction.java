package gerudok.actions;


import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.Serializable;

import javax.swing.KeyStroke;

import gerudok.model.SlotGraphic;

public class CommandUndoAction extends AbstractActionIcon implements Serializable {
	
	private static final long serialVersionUID = 1L;

	SlotGraphic slot;

	public CommandUndoAction(SlotGraphic slot) {
		this.slot = slot;
	}

	public CommandUndoAction() {
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, iconGetter("/menu/undo.png"));
		putValue(NAME, "Undo");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		slot.getCommandManager().undoCommand();
	}

}
