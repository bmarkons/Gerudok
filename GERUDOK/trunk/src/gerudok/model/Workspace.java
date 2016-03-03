package gerudok.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

public class Workspace implements TreeNode, Serializable {
	private static final long serialVersionUID = -7676203044336567301L;
	
	String name = "DefaultWorkspace";
	private ArrayList<Project> projects = new ArrayList<Project>();

	public Workspace() {
		super();
	}

	public void addProject(Project project) {
		project.setParent(this);
		projects.add(project);
		if (project.getName() == null)
			project.setName("Project - " + projects.size());
		if(project.getProjectFile() == null){
			project.setProjectModified(true);
		}
	}

	public void deleteProject(Project project) {
		projects.remove(project);
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
		return false;
	}
}
