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
public class NewStar extends AbstractActionIcon {

	private StateManager s;

	public NewStar(StateManager s) {
		putValue(SMALL_ICON, iconGetter("/toolbar_slotview/star.png"));
		putValue(NAME, "Star");
		putValue(SHORT_DESCRIPTION, "Star");

		this.s = s;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		s.setStarState();
		// Promena izgleda kursora
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("images/toolbar_slotview/star.png");
		Cursor cursor = kit.createCustomCursor(img, new Point(0, 0), "Star");
		((Component)e.getSource()).getParent().getParent().setCursor(cursor);
	}

}
