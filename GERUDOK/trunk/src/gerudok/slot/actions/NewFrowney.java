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
public class NewFrowney extends AbstractActionIcon {

	private StateManager s;

	public NewFrowney(StateManager s) {
		putValue(SMALL_ICON, iconGetter("/toolbar_slotview/frowney.png", AbstractActionIcon.small));
		putValue(NAME, "Frowney");
		putValue(SHORT_DESCRIPTION, "Frowney");

		this.s = s;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		s.setFrowneyState();
		// Promena izgleda kursora
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("images/toolbar_slotview/frowney.png");
		Cursor cursor = kit.createCustomCursor(img, new Point(0, 0), "Frowney");

		((Component) e.getSource()).getParent().getParent().setCursor(cursor);

	}

}
