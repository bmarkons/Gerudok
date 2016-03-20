package gerudok.view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import gerudok.events.DocumentEvent;
import gerudok.events.DocumentEvent.DocumentEventType;
import gerudok.events.PageEvent;
import gerudok.events.PageEvent.PageEventType;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.Document;
import gerudok.model.Slot;

public class DocumentView extends JScrollPane implements Observer {
	private static final long serialVersionUID = -1537590242534111512L;

	private String name = "";
	private Document document = null;
	private DocumentPanel panel;

	public DocumentView(Document document) {
		super();
		this.document = document;
		this.name = document.getName();
		this.panel = new DocumentPanel();
		setViewportView(this.panel);
	}

	public Document getDocument() {
		return this.document;
	}

	// public void setDocument(Document document) {
	// this.document = document;
	// }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addPageView(PageView view) {
		panel.getPageViews().add(view);
		panel.add(view, "wrap");
		panel.validate();
	}

	public void removePageView(PageView view) {
		panel.getPageViews().remove(view);
		panel.remove(view);
		panel.validate();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg == null)
			return;
		
		DocumentEvent eventObject = (DocumentEvent) arg;

		if (eventObject.getType() == DocumentEventType.ADD_PAGE) {
			
			PageView pageView = new PageView(eventObject.getPage());
			addPageView(pageView);
			eventObject.getPage().addObserver(pageView);
			
			ArrayList<Slot> slots = eventObject.getPage().getSlots();
			for(Slot slot:slots)
				eventObject.getPage().notifyObservers(new PageEvent(PageEventType.ADD_SLOT, slot));
			
		} else if (eventObject.getType() == DocumentEventType.REMOVE_PAGE) {
			
			ArrayList<PageView> pageViews = this.panel.getPageViews();
			for (PageView view : pageViews) {
				if(view.getPage().equals(eventObject.getPage()))
					removePageView(view);
			}
			
		} else if (eventObject.getType() == DocumentEventType.RENAME_DOCUMENT) {
			
			setName(getDocument().getName());
			
		}

		SwingUtilities.updateComponentTreeUI(MainFrameGerudok.getInstance().getTree());
	}
}
