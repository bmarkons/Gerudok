package gerudok.states;

import gerudok.view.SlotView;

public class StateManager {

	private State currentState;

	private StarState starState;
	private SmileyState smileyState;
	private FrowneyState frowneyState;
	private SelectState selectState;

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