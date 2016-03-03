package gerudok.actions;


import java.awt.event.ActionEvent;
import java.io.Serializable;

import javax.swing.AbstractAction;

import gerudok.model.SlotGraphic;

public class CommandUndoAction extends AbstractAction implements Serializable {
	private static final long serialVersionUID = 1L;

	SlotGraphic slot;

	public CommandUndoAction(SlotGraphic slot) {
		this.slot = slot;
	}

	public CommandUndoAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		slot.getCommandManager().undoCommand();
	}

}
