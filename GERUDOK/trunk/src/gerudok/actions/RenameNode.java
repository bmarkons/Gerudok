package gerudok.actions;

import gerudok.gui.MainFrameGerudok;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

public class RenameNode extends AbstractAction {
	private static final long serialVersionUID = 1L;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JTree tree = MainFrameGerudok.getInstance().getTree();
		TreePath path = tree.getSelectionPath();
		if (path != null) {
			tree.startEditingAtPath(path);
		}
	}
}