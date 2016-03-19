package gerudok.slot.actions;

import java.awt.Component;
import java.awt.event.ActionEvent;

import gerudok.actions.manager.AbstractActionIcon;
import gerudok.states.StateManager;

@SuppressWarnings("serial")
public class SelectAction extends AbstractActionIcon{

	private StateManager s;
	
	public SelectAction(StateManager s) {
		putValue(SMALL_ICON, iconGetter("/toolbar_slotview/select.png"));
		putValue(NAME, "Select");
		putValue(SHORT_DESCRIPTION, "Select");

		this.s = s;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		s.setSelectState();
		
		((Component) e.getSource()).getParent().getParent().setCursor(((Component) e.getSource()).getParent().getCursor());
		
	}
	
}
