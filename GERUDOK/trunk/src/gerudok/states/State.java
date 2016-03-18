package gerudok.states;

import java.awt.event.MouseEvent;

import gerudok.model.SlotGraphic;
import gerudok.view.SlotView;

public abstract class State{
	SlotView view;
	SlotGraphic slot;
	
	public State(SlotView view){
		this.view = view;
		this.slot = (SlotGraphic) view.getSlot();
	}
	
	public abstract void mouseClicked(MouseEvent e);
}
