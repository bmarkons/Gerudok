package gerudok.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Observable;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import gerudok.events.WorkspaceEvent;
import gerudok.events.WorkspaceEvent.WorkspaceEventType;

public class Workspace extends Observable implements MutableTreeNode, Serializable {
	private static final long serialVersionUID = -7676203044336567301L;

	private String name = "Workspace";
	private ArrayList<Project> projects = new ArrayList<Project>();

	public Workspace() {
		super();
	}

	public void addProject(Project project) {
		project.setParent(this);
		projects.add(project);
		if (project.getName() == null)
			project.setName("Project - " + projects.size());
		if (project.getProjectFile() == null) {
			project.setProjectModified(true);
		}
		
		setChanged();
		notifyObservers(new WorkspaceEvent(WorkspaceEventType.ADD_PROJECT, project));
	}

	public void deleteProject(Project project) {
		projects.remove(project);

		setChanged();
		notifyObservers(new WorkspaceEvent(WorkspaceEventType.REMOVE_PROJECT, project));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Enumeration<Project> children() {
		return (Enumeration<Project>) projects;
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int arg0) {
		return projects.get(arg0);
	}

	@Override
	public int getChildCount() {
		return projects.size();
	}

	@Override
	public int getIndex(TreeNode arg0) {
		return projects.indexOf(arg0);
	}

	@Override
	public TreeNode getParent() {
		return null;
	}

	@Override
	public boolean isLeaf() {
		return this.projects.size() == 0;
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(MutableTreeNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUserObject(Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFromParent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		// TODO Auto-generated method stub
		
	}
}
