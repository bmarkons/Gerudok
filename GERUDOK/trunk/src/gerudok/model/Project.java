package gerudok.model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import gerudok.gui.MainFrameGerudok;
import gerudok.view.ProjectView;

public class Project extends Observable implements MutableTreeNode, Serializable, Observer {
	private static final long serialVersionUID = -8713701240302899388L;

	Workspace parent = null;
	String name = null;
	ArrayList<Document> documents = new ArrayList<Document>();
	String documentsNum = "";
	transient ProjectView projectView = null;
	File projectFile = null;
	boolean projectModified;

	public Project() {
		super();
		this.addObserver(this);
	}

	public void setParent(Workspace parent) {
		this.parent = parent;
	}

	public ArrayList<Document> getDocuments() {
		return this.documents;
	}

	private Object readResolve() {
		projectView = new ProjectView(this.name);
		addObserver(this);
		return this;
	}

	public boolean isProjectModified() {
		return projectModified;
	}

	public void setProjectModified(boolean projectModified) {
		if (projectModified == true)
			this.name = "*" + this.name;
		else
			this.name = this.name.replace("*", "");

		this.projectModified = projectModified;
		SwingUtilities.updateComponentTreeUI(MainFrameGerudok.getInstance().getTree());
	}

	public File getProjectFile() {
		return projectFile;
	}

	public void setProjectFile(File projectFile) {
		this.projectFile = projectFile;
	}

	public ProjectView getProjectView() {
		return projectView;
	}

	public void setProjectView(ProjectView projectView) {
		this.projectView = projectView;
	}

	public void addDocument(Document document) {
		documents.add(document);
		if (document.getName() == null)
			document.setName("Document - " + documents.size());
		documentsNum = "(" + documents.size() + ")";
		// dogodila se modifikacija projekta
		setChanged();
		notifyObservers();
	}

	public void deleteDocument(Document document) {
		documents.remove(document);
		documentsNum = "(" + documents.size() + ")";
		// dogodila se modifikacija projekta
		setChanged();
		notifyObservers();
	}

	public void setName(String name) {
		this.name = name;
		if (projectView != null)
			projectView.setName(name);
		// dogodila se modifikacija projekta
		setChanged();
		notifyObservers();
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		return name + documentsNum;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Enumeration<Document> children() {
		return (Enumeration<Document>) documents;
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int arg0) {
		return documents.get(arg0);
	}

	@Override
	public int getChildCount() {
		return documents.size();
	}

	@Override
	public int getIndex(TreeNode arg0) {
		return documents.indexOf(arg0);
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
	public boolean isLeaf() {
		return this.documents.size() == 0;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (!this.projectModified) {
			this.setProjectModified(true);
		}
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		this.projectModified = true;
		this.documents.add(index, (Document) child);
	}

	@Override
	public void remove(int index) {
		this.projectModified = true;
		this.documents.remove(index);
	}

	@Override
	public void remove(MutableTreeNode node) {
		this.projectModified = true;
		this.documents.remove(node);
	}

	@Override
	public void setUserObject(Object object) {
	}

	@Override
	public void removeFromParent() {	
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		this.parent = (Workspace) newParent;
	}
}
