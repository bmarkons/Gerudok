package gerudok.view;

import gerudok.gui.MainFrameGerudok;
import gerudok.model.Project;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

public class ProjectView extends JInternalFrame {

	private static final long serialVersionUID = -1223897253460221963L;

	private static int xStart = 20;
	private static int yStart = 20;
	private static int x = xStart;
	private static int y = yStart;
	
	private Project project;
	private JTabbedPane tabPane;

	public ProjectView(String title) {

		super(title, true, true, true, true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		Dimension d = MainFrameGerudok.getInstance().getSize();
		this.setSize(d.width / 2, 2 * d.height / 3);

		ImageIcon image = new ImageIcon("images/tree/treeproj.png");
		setFrameIcon(new ImageIcon(image.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));
		
		tabPane = new JTabbedPane();
		add(tabPane);
		
		setLocation(x,y);
		int maxY = MainFrameGerudok.getInstance().getHeight();
		
		//Algoritam za određivanje pozicije prozora projekta nakon što izađe sa površine ekrana prilikom dodavanja
		if (d.height / 2 + y + 20 < maxY) {
			y += 40;
			x += 40;
		} else {
			xStart += 40;
			y = yStart;
			x = xStart + 40;
		}
	}
	
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public JTabbedPane getTabbedPane() {
		return tabPane;
	}

	public void addDocumentView(DocumentView docView) {
		tabPane.add(docView);
	}

	public void removeDocumentView(DocumentView docView) {
		tabPane.remove(docView);
	}
	
}
