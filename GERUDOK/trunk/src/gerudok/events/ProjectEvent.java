package gerudok.events;

import gerudok.model.Document;

public class ProjectEvent {
	private ProjectEventType type = null;
	private Document document = null;
	
	public ProjectEvent(ProjectEventType type,Document document){
		this.type = type;
		this.document = document;
	}
	
	public ProjectEventType getType() {
		return type;
	}

	public Document getDocument() {
		return document;
	}

	public enum ProjectEventType{
		ADD_DOCUMENT,
		REMOVE_DOCUMENT,
		RENAME_PROJECT,
		IMPORT_DOCUMENT
	}
}
