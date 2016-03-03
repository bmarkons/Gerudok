package gerudok.model;

import javax.swing.tree.DefaultTreeModel;

public class JTreeModelGerudok extends DefaultTreeModel {

	private static final long serialVersionUID = -9200099307378239671L;

	public JTreeModelGerudok() {
		super(new Workspace());
	}

	// Treba istraziti za sta sluze ove metode iz primera

	// public Object getChild(Object parent, int index) {
	// if (parent instanceof Page) {
	// return null;
	// } else if (parent instanceof Workspace) {
	// return ((Workspace) parent).getProject(index);
	// } else if (parent instanceof Project) {
	// return ((Project) parent).getDocument(index);
	// } else if (parent instanceof Document) {
	// return ((Document) parent).getPage(index);
	// }
	// return getRoot();
	// }
	//
	// public int getChildCount(Object parent) {
	// if (parent instanceof Page) {
	// return 0;
	// } else if (parent instanceof Document) {
	// return ((Document) parent).getChildCount();
	// } else if (parent instanceof Project) {
	// return ((Project) parent).getChildCount();
	// } else if (parent instanceof Workspace) {
	// return ((Workspace) parent).getChildCount();
	// }
	// return 0;
	// }
	//
	// public boolean isLeaf(Object node) {
	// return (node instanceof Page);
	// }
	//
	// public int getIndexOfChild(Object parent, Object child) {
	// if (parent instanceof Page) {
	// return -1;
	// } else if (parent instanceof Workspace) {
	// if (child instanceof Project)
	// return ((Workspace) parent).getIndex((Project) child);
	// } else if (parent instanceof Project) {
	// return ((Project) parent).getIndex((Document) child);
	// } else if (parent instanceof Document) {
	// return ((Document)parent).getIndex((Page) child);
	// }
	// return -1;
	// }
	// Treba istraziti za sta sluze ove metode iz primera

	// public Object getChild(Object parent, int index) {
	// if (parent instanceof Page) {
	// return null;
	// } else if (parent instanceof Workspace) {
	// return ((Workspace) parent).getProject(index);
	// } else if (parent instanceof Project) {
	// return ((Project) parent).getDocument(index);
	// } else if (parent instanceof Document) {
	// return ((Document) parent).getPage(index);
	// }
	// return getRoot();
	// }
	//
	// public int getChildCount(Object parent) {
	// if (parent instanceof Page) {
	// return 0;
	// } else if (parent instanceof Document) {
	// return ((Document) parent).getChildCount();
	// } else if (parent instanceof Project) {
	// return ((Project) parent).getChildCount();
	// } else if (parent instanceof Workspace) {
	// return ((Workspace) parent).getChildCount();
	// }
	// return 0;
	// }
	//
	// public boolean isLeaf(Object node) {
	// return (node instanceof Page);
	// }
	//
	// public int getIndexOfChild(Object parent, Object child) {
	// if (parent instanceof Page) {
	// return -1;
	// } else if (parent instanceof Workspace) {
	// if (child instanceof Project)
	// return ((Workspace) parent).getIndex((Project) child);
	// } else if (parent instanceof Project) {
	// return ((Project) parent).getIndex((Document) child);
	// } else if (parent instanceof Document) {
	// return ((Document)parent).getIndex((Page) child);
	// }
	// return -1;
	// }
}
