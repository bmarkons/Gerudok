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
	public void mousePressed(MouseEvent e) {
		// Dodavanje smajlija :)
				Point point = e.getPoint();
				getSlot().getCommandManager()
						.addCommand(new AddCommand(getSlot(), new SmileyElement(getSlot(), point)));
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
