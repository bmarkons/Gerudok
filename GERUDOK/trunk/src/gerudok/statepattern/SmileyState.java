package gerudok.statepattern;

import java.awt.Point;
import java.awt.event.MouseEvent;

import gerudok.comands.AddCommand;
import gerudok.model.SmileyElement;
import gerudok.view.SlotView;

public class SmileyState extends State {

	public SmileyState(SlotView view) {
		super(view);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Dodavanje smajlija :)
		Point point = e.getPoint();
		slot.getCommandManager()
				.addCommand(new AddCommand(slot, new SmileyElement(slot, point)));
	}
}
