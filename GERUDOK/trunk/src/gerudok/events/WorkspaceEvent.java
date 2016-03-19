package gerudok.events;

import gerudok.model.Project;

public class WorkspaceEvent {
	private Type type = null;
	private Project project = null;
	
	public WorkspaceEvent(Type type, Project project){
		this.type = type;
		this.project = project;
	}
	
	public Project getProject() {
		return project;
	}

	public Type getType() {
		return type;
	}

	public enum Type{
		ADD_PROJECT,
		REMOVE_PROJECT
	}
}
