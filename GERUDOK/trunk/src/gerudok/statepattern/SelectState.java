package gerudok.statepattern;

import java.awt.event.MouseEvent;

import gerudok.view.SlotView;

public class SelectState extends State{

	public SelectState(SlotView view) {
		super(view);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//Nije potrebna nikakva akcija
	}

}
