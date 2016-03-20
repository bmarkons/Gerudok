package gerudok.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import gerudok.events.DocumentEvent;
import gerudok.events.DocumentEvent.DocumentEventType;
import gerudok.view.DocumentView;

public class Document extends Observable implements MutableTreeNode, Serializable, Observer {
	private static final long serialVersionUID = 3563415829958763008L;

	private Project parent = null;
	private String name = null;
	private ArrayList<Page> pages = new ArrayList<Page>();
	//transient DocumentView documentView = null;

	public Document(Project parent) {
		super();
		this.parent = parent;
		addObserver(parent);
	}

	public ArrayList<Page> getPages() {
		return this.pages;
	}

	private Object readResolve() {
		//documentView = new DocumentView(this.name);
		addObserver(parent);
		return this;
	}

//	public DocumentView getDocumentView() {
//		return documentView;
//	}
//
//	public void setDocumentView(DocumentView documentView) {
//		this.documentView = documentView;
//	}

	public void addPage(Page page) {
		pages.add(page);
		if (page.getName() == null)
			page.setName("Page - " + pages.size());
		// dogodila se modifikacija dokumenta
		setChanged();
		notifyObservers(new DocumentEvent(DocumentEventType.ADD_PAGE, page));
	}

	public void deletePage(Page page) {
		pages.remove(page);
		// dogodila se modifikacija dokumenta
		setChanged();
		notifyObservers(new DocumentEvent(DocumentEventType.REMOVE_PAGE, page));
	}

	public void setName(String name) {
		this.name = name;
		
		// dogodila se modifikacija projekta
		setChanged();
		notifyObservers(new DocumentEvent(DocumentEventType.RENAME_DOCUMENT, null));
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		return name + "(" + pages.size() + ")";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Enumeration<Page> children() {
		return (Enumeration<Page>) pages;
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return pages.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return pages.size();
	}

	@Override
	public int getIndex(TreeNode node) {
		return pages.indexOf(node);
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
	public boolean isLeaf() {
		return this.pages.size() == 0;
	}

	@Override
	public void update(Observable o, Object arg) {
		// Prosledjuje obavestenje o promeni ka parent-u
		setChanged();
		notifyObservers();
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
