package gerudok.tree.listener;

import gerudok.gui.MainFrameGerudok;
import gerudok.gui.segments.JTreeGerudok;
import gerudok.model.Document;
import gerudok.model.Page;
import gerudok.model.Project;
import gerudok.model.Slot;
import gerudok.model.Workspace;
import gerudok.tree.view.TreePopUp;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

public class TreeMouseListener extends MouseAdapter {

	public void mousePressed(MouseEvent e) {
		
		//JTree tree = MainFrameGerudok.getInstance().getTree();
		//TreePath path = tree.getPathForLocation(e.getX(), e.getY());
		//Object node = null;
		//JPopupMenu popup = tree.getComponentPopupMenu();
		/*
		if (SwingUtilities.isRightMouseButton(e)) {
			if (path == null) {
				popup.disableAll();
			} else {
				tree.setSelectionPath(path);
				node = path.getLastPathComponent();
			}
			if (node instanceof Workspace) {
				popup.disableAll();
			} else if (node instanceof Document) {
				popup.enableDocument();
			} else if (node instanceof Project) {
				popup.enableProject();
			} else if (node instanceof Page) {
				popup.enablePage();
			} else if (node instanceof Slot) {
				popup.enableSlot();
			}
			popup.show(tree, e.getX(), e.getY());
		}
		*/
	}
}