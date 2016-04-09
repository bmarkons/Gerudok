package gerudok.xml;

import java.util.ArrayList;

import gerudok.model.Document;

public class ParseResult {

	private String projectName;
	private ArrayList<Document> documents;
	private ArrayList<String> notFoundDocs;

	public ParseResult(String projectName, ArrayList<Document> documents, ArrayList<String> notFoundDocs) {
		this.projectName = projectName;
		this.documents = documents;
		this.notFoundDocs = notFoundDocs;
	}

	public String getProjectName() {
		return projectName;
	}

	public ArrayList<Document> getDocuments() {
		return documents;
	}

	public ArrayList<String> getNotFoundDocs() {
		return notFoundDocs;
	}
}
