package gerudok.view;

import gerudok.model.Project;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

public class ProjectView extends JInternalFrame {

	private static final long serialVersionUID = -1223897253460221963L;

	Project project;
	static int openFrameCount = 0;
	static final int xOffset = 30, yOffset = 30;
	JTabbedPane tabPane;

	public ProjectView(String title) {

		super("", true, true, true, true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		this.setSize(1200, 600);
		this.title = title;
		++openFrameCount;
		setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
		ImageIcon image = new ImageIcon("images/tree/treeproj.png");
		setFrameIcon(image);
		tabPane = new JTabbedPane();
		add(tabPane);
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
