package gerudok.events;

import gerudok.model.Slot;

public class PageEvent {
	private PageEventType type = null;
	private Slot slot = null;

	public PageEvent(PageEventType type, Slot slot) {
		this.type = type;
		this.slot = slot;
	}

	public PageEventType getType() {
		return type;
	}

	public Slot getSlot() {
		return this.slot;
	}

	public enum PageEventType {
		ADD_SLOT, REMOVE_SLOT, RENAME_PAGE
	}
}
