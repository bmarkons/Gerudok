package gerudok.events;

import gerudok.model.Slot;

public class PageEvent {
	private Type type = null;
	private Slot slot = null;

	public PageEvent(Type type, Slot slot) {
		this.type = type;
		this.slot = slot;
	}

	public Type getType() {
		return type;
	}

	public Slot getPage() {
		return this.slot;
	}

	public enum Type {
		ADD_SLOT, REMOVE_SLOT, RENAME_PAGE
	}
}
