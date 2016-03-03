package gerudok.view;

import gerudok.actions.DeleteNode;
import gerudok.actions.DisplayProject;
import gerudok.actions.RenameNode;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class TreePopUp extends JPopupMenu {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem Rename;
	private JMenuItem Open;
	private JMenuItem Delete;

	public TreePopUp() {
		createItems();
		addItems();
	}

	private void createItems() {
		this.Rename = new JMenuItem("Rename");
		this.Rename.addActionListener(new RenameNode());

		this.Delete = new JMenuItem("Delete");
		this.Delete.addActionListener(new DeleteNode());

		this.Open = new JMenuItem("Display");
		this.Open.addActionListener(new DisplayProject());
	}

	public void enableDocument() {
		this.Rename.setEnabled(true);
		this.Delete.setEnabled(true);
		this.Open.setEnabled(false);
	}

	public void enableProject() {
		this.Rename.setEnabled(true);
		this.Delete.setEnabled(true);
		this.Open.setEnabled(true);
	}

	public void enablePage() {
		this.Rename.setEnabled(true);
		this.Delete.setEnabled(true);
		this.Open.setEnabled(false);
	}

	public void enableSlot() {
		this.Rename.setEnabled(true);
		this.Delete.setEnabled(true);
		this.Open.setEnabled(false);
	}

	public void disableAll() {
		this.Rename.setEnabled(false);
		this.Delete.setEnabled(false);
		this.Open.setEnabled(false);
	}

	public void enableAll() {
		this.Rename.setEnabled(true);
		this.Delete.setEnabled(true);
		this.Open.setEnabled(true);
	}

	private void addItems() {
		add(this.Rename);
		addSeparator();
		add(this.Delete);
		addSeparator();
		add(this.Open);
	}

}
