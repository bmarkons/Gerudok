package gerudok.statepattern;

import gerudok.view.SlotView;

public class StateManager {

	State currentState;

	StarState starState;
	SmileyState smileyState;
	FrowneyState frowneyState;
	SelectState selectState;

	public StateManager(SlotView view) {
		starState = new StarState(view);
		smileyState = new SmileyState(view);
		frowneyState = new FrowneyState(view);
		selectState = new SelectState(view);
		currentState = selectState;
	}

	public void setStarState() {
		currentState = starState;
	}

	public void setSmileyState() {
		currentState = smileyState;
	}

	public void setFrowneyState() {
		currentState = frowneyState;
	}

	public void setSelectState() {
		currentState = selectState;
	}

	public State getCurrentState() {
		return currentState;
	}
}
