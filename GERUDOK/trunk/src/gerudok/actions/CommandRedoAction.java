package gerudok.actions;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.SlotGraphic;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.Serializable;

import javax.swing.KeyStroke;

public class CommandRedoAction extends AbstractActionIcon implements Serializable {
	private static final long serialVersionUID = 1L;

	private SlotGraphic slot;

	public CommandRedoAction(SlotGraphic slot) {
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, iconGetter("/menu/redo.png"));
		putValue(NAME, rb.getString("Redo"));
		putValue(SHORT_DESCRIPTION, rb.getString("RedoH"));
		this.slot = slot;
	}

	public CommandRedoAction() {
		putValue(ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, iconGetter("/menu/redo.png"));
		putValue(NAME, rb.getString("Redo"));
		putValue(SHORT_DESCRIPTION, rb.getString("RedoH"));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (MainFrameGerudok.getInstance().getTree().getSelectionPath() != null)
			if (MainFrameGerudok.getInstance().getTree().getSelectionPath()
					.getLastPathComponent() instanceof SlotGraphic) {
				slot = (SlotGraphic) MainFrameGerudok.getInstance().getTree().getSelectionPath().getLastPathComponent();
				slot.getCommandManager().doCommand();
			}
		
	}

}
