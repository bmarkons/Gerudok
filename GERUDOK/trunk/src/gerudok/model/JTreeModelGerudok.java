package gerudok.model;

import javax.swing.tree.DefaultTreeModel;

public class JTreeModelGerudok extends DefaultTreeModel {

	private static final long serialVersionUID = -9200099307378239671L;

	public JTreeModelGerudok() {
		super(new Workspace());
	}
}
