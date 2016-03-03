package gerudok.actions;

import gerudok.model.SlotGraphic;

import java.awt.event.ActionEvent;
import java.io.Serializable;

import javax.swing.AbstractAction;

public class CommandRedoAction extends AbstractAction implements Serializable {
	private static final long serialVersionUID = 1L;

	SlotGraphic slot;

	public CommandRedoAction(SlotGraphic slot) {
		this.slot = slot;
	}

	public CommandRedoAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		slot.getCommandManager().doCommand();
	}

}
