package gerudok.model;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import gerudok.events.ProjectEvent;
import gerudok.events.ProjectEvent.ProjectEventType;

public class Project extends Observable implements MutableTreeNode, Serializable, Observer, ClipboardOwner {
	private static final long serialVersionUID = -8713701240302899388L;

	private Workspace parent = null;
	private String name = null;
	private ArrayList<Document> documents = new ArrayList<Document>();
	private File projectFile = null;
	private boolean projectModified;

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
		addObserver(this);
		return this;
	}

	public boolean isProjectModified() {
		return projectModified;
	}

	public void setProjectModified(boolean projectModified) {
		this.projectModified = projectModified;
		// SwingUtilities.updateComponentTreeUI(MainFrameGerudok.getInstance().getTree());
	}

	public File getProjectFile() {
		return projectFile;
	}

	public void setProjectFile(File projectFile) {
		this.projectFile = projectFile;
	}

	public void importDocument(Document document) {
		documents.add(document);

		// dogodila se modifikacija projekta
		notifyObservers(new ProjectEvent(ProjectEventType.IMPORT_DOCUMENT, document));
	}

	public void addDocument(Document document) {
		documents.add(document);
		if (document.getName() == null)
			document.setName("Document - " + documents.size());

		// dogodila se modifikacija projekta
		notifyObservers(new ProjectEvent(ProjectEventType.ADD_DOCUMENT, document));
	}

	public void deleteDocument(Document document) {
		documents.remove(document);

		if (document.getParent().equals(this)) {
			for (Object p : document.getAllParents().toArray()) {
				((Project) p).deleteDocument(document);
			}
		} else {
			document.getAllParents().remove(this);

			if (document.getAllParents().isEmpty()) {
				document.setShared(false);
			}
		}

		// dogodila se modifikacija projekta
		notifyObservers(new ProjectEvent(ProjectEventType.REMOVE_DOCUMENT, document));
	}

	public void setName(String name) {
		this.name = name;

		// dogodila se modifikacija projekta
		notifyObservers(new ProjectEvent(ProjectEventType.RENAME_PROJECT, null));
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		String indicator = projectModified ? "*" : "";
		return indicator + name + "(" + documents.size() + ")";
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

	@Override
	public void notifyObservers(Object arg) {
		setChanged();
		super.notifyObservers(arg);
	}

	transient private Clipboard clipboard = new Clipboard("Project clipboard");

	public Clipboard getClipboard() {
		return clipboard;
	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		// TODO Auto-generated method stub

	}
}
