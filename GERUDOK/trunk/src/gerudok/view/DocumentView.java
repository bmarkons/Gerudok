package gerudok.view;

import javax.swing.JScrollPane;

import gerudok.model.Document;

public class DocumentView extends JScrollPane {
	private static final long serialVersionUID = -1537590242534111512L;

	private String name = "";
	private Document document = null;
	private DocumentPanel panel;

	public DocumentView(String name) {
		super();
		this.name = name;
		this.panel = new DocumentPanel();
		setViewportView(this.panel);
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

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
	
	 public Document getDocumentModel()
	  {
	    return this.document;
	  }
}
