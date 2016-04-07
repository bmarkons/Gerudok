package gerudok.states;

import java.awt.event.MouseEvent;

import gerudok.model.SlotGraphic;
import gerudok.view.SlotView;

public abstract class State{
	private SlotView view;
	private SlotGraphic slot;
	
	public State(SlotView view){
		this.setView(view);
		this.setSlot((SlotGraphic) view.getSlot());
	}
	
	//public abstract void mouseClicked(MouseEvent e);

	public abstract void mousePressed(MouseEvent e);
	public abstract void mouseDragged(MouseEvent e);
	public abstract void mouseReleased(MouseEvent e);
	public abstract void mouseMoved(MouseEvent e);
	
	public SlotView getView() {
		return view;
	}

	public void setView(SlotView view) {
		this.view = view;
	}

	public SlotGraphic getSlot() {
		return slot;
	}

	public void setSlot(SlotGraphic slot) {
		this.slot = slot;
	}
}
