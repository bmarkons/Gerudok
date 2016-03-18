package gerudok.gui.segments;

import gerudok.tree.listener.TreeMouseListener;
import gerudok.tree.view.TreePopUp;

import javax.swing.JTree;

public class JTreeGerudok extends JTree {
	//MODIFIKOVATI, STOJI TRENUTNO ZBOG REALIZACIJE POPUP MENIJA, ALI TO TREBA DRUGAČIJE!
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
