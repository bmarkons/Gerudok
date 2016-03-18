package gerudok.states;

import java.awt.Point;
import java.awt.event.MouseEvent;

import gerudok.commands.AddCommand;
import gerudok.model.element.SmileyElement;
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
