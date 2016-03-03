package gerudok.gui.windowParts;

import gerudok.actions.TreeMouseListener;
import gerudok.view.TreePopUp;

import javax.swing.JTree;

public class JTreeGerudok extends JTree {

	private static final long serialVersionUID = -31573860135626863L;
	private TreePopUp popup;

	public JTreeGerudok() {
		super();
		setEditable(true);
		addListeners();
		this.popup = new TreePopUp();
		add(this.popup);
	}

	// za debug
	public String toString() {
		return "Stablo";
	}

	public TreePopUp getPopUp() {
		this.popup.enableAll();
		return this.popup;
	}
	

	public void addListeners() {
		addMouseListener(new TreeMouseListener());
	}

}
