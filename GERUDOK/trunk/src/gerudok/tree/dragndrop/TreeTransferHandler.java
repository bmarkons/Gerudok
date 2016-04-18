package gerudok.tree.dragndrop;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.util.ArrayList;
import java.util.List;

import javax.print.Doc;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import gerudok.model.Document;
import gerudok.model.Project;

public class TreeTransferHandler extends TransferHandler {
	private static final long serialVersionUID = 7864064491947894012L;

	DataFlavor nodesFlavor;
	DataFlavor[] flavors = new DataFlavor[1];
	// MutableTreeNode[] nodesToRemove;

	public TreeTransferHandler() {
		try {
			String mimeType = DataFlavor.javaJVMLocalObjectMimeType + ";class=\""
					+ gerudok.model.Document[].class.getName() + "\"";
			nodesFlavor = new DataFlavor(mimeType);
			flavors[0] = nodesFlavor;
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFound: " + e.getMessage());
		}
	}

	@Override
	public boolean canImport(TransferHandler.TransferSupport support) {
		if (!support.isDrop()) {
			return false;
		}
		support.setShowDropLocation(true);
		if (!support.isDataFlavorSupported(nodesFlavor)) {
			return false;
		}

		JTree tree = (JTree) support.getComponent();
		JTree.DropLocation dl = (JTree.DropLocation) support.getDropLocation();
		// int action = support.getDropAction();

		// Samo dokumenti mogu biti drag n dropovani
		int[] selectedRows = tree.getSelectionRows();
		for (int i : selectedRows) {
			TreePath selectedPath = tree.getPathForRow(i);
			MutableTreeNode node = (MutableTreeNode) selectedPath.getLastPathComponent();
			if (!(node instanceof Document)) {
				return false;
			}
		}

		// I samo u projekte
		if (!(dl.getPath().getLastPathComponent() instanceof Project)) {
			return false;
		}

		// Do not allow a drop on the drag source selections.
		int dropRow = tree.getRowForPath(dl.getPath());
		int[] selRows = tree.getSelectionRows();
		for (int i = 0; i < selRows.length; i++) {
			if (selRows[i] == dropRow) {
				return false;
			}
		}

		return true;
	}

	@Override
	protected Transferable createTransferable(JComponent c) {
		JTree tree = (JTree) c;
		TreePath[] paths = tree.getSelectionPaths();
		if (paths != null) {
			// Make up a node array of copies for transfer and
			// another for/of the nodes that will be removed in
			// exportDone after a successful drop.
			List<MutableTreeNode> copies = new ArrayList<MutableTreeNode>();

			for (int i = 0; i < paths.length; i++) {
				MutableTreeNode node = (MutableTreeNode) paths[i].getLastPathComponent();
				if (node instanceof Document) {
					copies.add(node);
				}
			}
			// List<MutableTreeNode> toRemove = new
			// ArrayList<MutableTreeNode>();
			// MutableTreeNode node = (MutableTreeNode)
			// paths[0].getLastPathComponent();
			// MutableTreeNode copy = copy(node);
			// copies.add(copy);
			// toRemove.add(node);
			// for (int i = 1; i < paths.length; i++) {
			// MutableTreeNode next = (MutableTreeNode)
			// paths[i].getLastPathComponent();
			// // Do not allow higher level nodes to be added to list.
			// if (next.getLevel() < node.getLevel()) {
			// break;
			// } else if (next.getLevel() > node.getLevel()) { // child node
			// copy.add(copy(next));
			// // node already contains child
			// } else { // sibling
			// copies.add(copy(next));
			// toRemove.add(next);
			// }
			// }
			MutableTreeNode[] nodes = copies.toArray(new MutableTreeNode[copies.size()]);
			/// nodesToRemove = toRemove.toArray(new
			/// MutableTreeNode[toRemove.size()]);
			return new NodesTransferable(nodes);
		}
		return null;
	}

	@Override
	protected void exportDone(JComponent source, Transferable data, int action) {
		// if ((action & MOVE) == MOVE) {
		// JTree tree = (JTree) source;
		// DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		// // Remove nodes saved in nodesToRemove in createTransferable.
		// for (int i = 0; i < nodesToRemove.length; i++) {
		// model.removeNodeFromParent(nodesToRemove[i]);
		// }
		// }
	}

	@Override
	public int getSourceActions(JComponent c) {
		return MOVE;
	}

	@Override
	public boolean importData(TransferHandler.TransferSupport support) {
		if (!canImport(support)) {
			return false;
		}

		// Extract transfer data.
		MutableTreeNode[] nodes = null;
		try {
			Transferable t = support.getTransferable();
			nodes = (MutableTreeNode[]) t.getTransferData(nodesFlavor);
		} catch (UnsupportedFlavorException ufe) {
			System.out.println("UnsupportedFlavor: " + ufe.getMessage());
		} catch (java.io.IOException ioe) {
			System.out.println("I/O error: " + ioe.getMessage());
		}

		// Get drop location info.
		JTree.DropLocation dl = (JTree.DropLocation) support.getDropLocation();
		TreePath dest = dl.getPath();
		Project parent = (Project) dest.getLastPathComponent();

		// Ubaci sve selektovane u projekat
		for (MutableTreeNode node : nodes) {
			if (!(node.getParent().equals(parent)) && !parent.getDocuments().contains(node)) {
				parent.importDocument((Document) node);
				((Document) node).setShared(true);
				((Document) node).addParent(parent);
			} else {
				return false;
			}
		}

		return true;
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

	public class NodesTransferable implements Transferable {
		MutableTreeNode[] nodes;

		public NodesTransferable(MutableTreeNode[] nodes) {
			this.nodes = nodes;
		}

		@Override
		public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
			if (!isDataFlavorSupported(flavor))
				throw new UnsupportedFlavorException(flavor);
			return nodes;
		}

		@Override
		public DataFlavor[] getTransferDataFlavors() {
			return flavors;
		}

		@Override
		public boolean isDataFlavorSupported(DataFlavor flavor) {
			return nodesFlavor.equals(flavor);
		}
	}
}
