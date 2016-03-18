package gerudok.states;

import java.awt.Point;
import java.awt.event.MouseEvent;

import gerudok.commands.AddCommand;
import gerudok.model.element.StarElement;
import gerudok.view.SlotView;

public class StarState extends State {

	public StarState(SlotView view) {
		super(view);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Dodavanje zvezde
		Point point = e.getPoint();
		slot.getCommandManager()
				.addCommand(new AddCommand(slot, new StarElement(slot, point)));
	}

}
