package gerudok.events;

import gerudok.model.Page;

public class DocumentEvent {
	private Type type = null;
	private Page page = null;
	
	public DocumentEvent(Type type,Page page){
		this.type = type;
		this.page = page;
	}
	
	public Type getType() {
		return type;
	}

	public Page getPage() {
		return page;
	}

	public enum Type{
		ADD_PAGE,
		REMOVE_PAGE,
		RENAME_DOCUMENT
	}
}
