package gerudok.view;

import gerudok.events.DocumentEvent;
import gerudok.events.DocumentEvent.DocumentEventType;
import gerudok.events.ProjectEvent;
import gerudok.events.ProjectEvent.ProjectEventType;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.Page;
import gerudok.model.Project;

import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

public class ProjectView extends JInternalFrame implements Observer {
	private static final long serialVersionUID = -1223897253460221963L;

	private static int xStart = 20;
	private static int yStart = 20;
	private static int x = xStart;
	private static int y = yStart;

	private Project project;
	private JTabbedPane tabbedPane;

	public ProjectView(Project project) {

		super(project.getName(), true, true, true, true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

		this.project = project;

		Dimension d = MainFrameGerudok.getInstance().getSize();
		this.setSize(d.width / 2, 2 * d.height / 3);

		ImageIcon image = new ImageIcon("images/tree/treeproj.png");
		setFrameIcon(new ImageIcon(image.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)));

		tabbedPane = new JTabbedPane();
		add(tabbedPane);

		setLocation(x, y);
		int maxY = MainFrameGerudok.getInstance().getHeight();

		// Algoritam za određivanje pozicije prozora projekta nakon što izađe sa
		// površine ekrana prilikom dodavanja
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

	// public void setProject(Project project) {
	// this.project = project;
	// }

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void addDocumentView(DocumentView docView) {
		tabbedPane.add(docView);
	}

	public void removeDocumentView(DocumentView docView) {
		tabbedPane.remove(docView);
	}

	@Override
	public void update(Observable o, Object arg) {
		ProjectEvent eventObject = (ProjectEvent) arg;

		if (eventObject.getType() == ProjectEventType.ADD_DOCUMENT) {
			
			DocumentView docView = new DocumentView(eventObject.getDocument());
			addDocumentView(docView);
			eventObject.getDocument().addObserver(docView);
			
			ArrayList<Page> pages = eventObject.getDocument().getPages();
			for(Page page:pages)
				eventObject.getDocument().notifyObservers(new DocumentEvent(DocumentEventType.ADD_PAGE, page));
			
		} else if (eventObject.getType() == ProjectEventType.REMOVE_DOCUMENT) {
			
			int totalTabs = tabbedPane.getTabCount();
			for (int i = 0; i < totalTabs; i++) {
				DocumentView docView = (DocumentView) tabbedPane.getTabComponentAt(i);
				if(docView.getDocument().equals(eventObject.getDocument())){
					tabbedPane.remove(docView);
				}
			}
		} else if (eventObject.getType() == ProjectEventType.RENAME_PROJECT) {
			this.title = project.getName();
		}

		SwingUtilities.updateComponentTreeUI(MainFrameGerudok.getInstance().getTree());
	}

}
