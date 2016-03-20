package gerudok.slot.actions;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.states.StateManager;

@SuppressWarnings("serial")
public class NewSmiley extends AbstractActionIcon {

	private StateManager s;

	public NewSmiley(StateManager s) {
		putValue(SMALL_ICON, iconGetter("/toolbar_slotview/smiley.png", AbstractActionIcon.small));
		putValue(NAME, "Smiley");
		putValue(SHORT_DESCRIPTION, "Smiley");

		this.s = s;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		s.setSmileyState();
		// Promena izgleda kursora
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("images/toolbar_slotview/smiley.png");
		Cursor cursor = kit.createCustomCursor(img, new Point(0, 0), "Smiley");
		((Component) e.getSource()).getParent().getParent().setCursor(cursor);

	}
}
