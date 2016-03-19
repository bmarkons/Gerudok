package gerudok.events;

import gerudok.model.Document;

public class ProjectEvent {
	private Type type = null;
	private Document document = null;
	
	public ProjectEvent(Type type,Document document){
		this.type = type;
		this.document = document;
	}
	
	public Type getType() {
		return type;
	}

	public Document getDocument() {
		return document;
	}

	public enum Type{
		ADD_DOCUMENT,
		REMOVE_DOCUMENT,
		RENAME_PROJECT
	}
}
