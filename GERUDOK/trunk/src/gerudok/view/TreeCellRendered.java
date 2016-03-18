package gerudok.view;

import gerudok.model.Page;
import gerudok.model.Project;
import gerudok.model.Document;
import gerudok.model.SlotGraphic;
import gerudok.model.SlotText;
import gerudok.model.Workspace;
import gerudok.model.element.FrowneyElement;
import gerudok.model.element.SmileyElement;
import gerudok.model.element.StarElement;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class TreeCellRendered extends DefaultTreeCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	public TreeCellRendered() {

		// TODO Auto-generated constructor stub
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);
		

		if (value instanceof Workspace) {
			ImageIcon icon = new ImageIcon("images/tree/treeworkspace.png");
			if (icon != null)
				setIcon(icon);
		} else if (value instanceof Document) {
			ImageIcon icon = new ImageIcon("images/tree/treedoc.png");
			if (icon != null)
				setIcon(icon);

		} else if (value instanceof Project) {
			ImageIcon icon = new ImageIcon("images/tree/treeproj.png");
			if (icon != null)
				setIcon(icon);

		} else if (value instanceof Page) {
			ImageIcon icon = new ImageIcon("images/tree/treepage.png");
			if (icon != null)
				setIcon(icon);

		} else if (value instanceof SlotText) {
			ImageIcon icon = new ImageIcon("images/tree/treestext.png");
			if (icon != null)
				setIcon(icon);

		} else if (value instanceof SlotGraphic) {
			ImageIcon icon = new ImageIcon("images/tree/treesgraph.png");
			if (icon != null)
				setIcon(icon);

		} else if (value instanceof StarElement) {
			ImageIcon icon = new ImageIcon("images/tree/star16x16.png");
			if (icon != null)
				setIcon(icon);

		} else if (value instanceof SmileyElement) {
			ImageIcon icon = new ImageIcon("images/tree/smiley16x16.png");
			if (icon != null)
				setIcon(icon);

		} else if (value instanceof FrowneyElement) {
			ImageIcon icon = new ImageIcon("images/tree/frowney16x16.png");
			if (icon != null)
				setIcon(icon);
		}

		return this;
	}

}
