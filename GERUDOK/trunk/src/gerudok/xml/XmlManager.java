package gerudok.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import gerudok.model.Document;
import gerudok.model.Project;

public class XmlManager {
	public static void createXmlFile(File file, Project project) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.newDocument();

			// korenski element
			Element rootElement = doc.createElement("project");
			Attr projectName = doc.createAttribute("name");
			projectName.setValue(project.getName());
			rootElement.setAttributeNode(projectName);
			doc.appendChild(rootElement);

			// document elementi unutar elementa project
			for (Document document : project.getDocuments()) {
				Element docElement = doc.createElement("document");
				Attr docAttr = doc.createAttribute("name");
				docAttr.setValue(document.getName());
				docElement.setAttributeNode(docAttr);
				rootElement.appendChild(docElement);
			}

			// zapis xml dokumenta u fajl
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ParseResult parseXmlFile(File file) {
		ParseResult parseResult = null;
		String projectName = null;
		ArrayList<Document> documents = new ArrayList<Document>();
		ArrayList<String> notFoundDocs = new ArrayList<String>();
		ArrayList<String> docNames = new ArrayList<String>();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();

			// pronalazenje imena projekta
			Element root = doc.getDocumentElement();
			projectName = root.getAttribute("name");

			// pronalazenje svih dokumenata sadrzanih u projektu
			
			NodeList docNodes = doc.getElementsByTagName("document");
			for (int i = 0; i < docNodes.getLength(); i++) {
				Element document = (Element) docNodes.item(i);
				docNames.add(document.getAttribute("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (String s : docNames) {
			File docFile = new File(file.getParent() + "\\" + s + ".gdoc");
			ObjectInputStream is;

			if (docFile.exists()) {
				try {
					is = new ObjectInputStream(new FileInputStream(docFile));
					Document d = (Document) is.readObject();
					documents.add(d);

					is.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				notFoundDocs.add(s);
			}
		}
		
		parseResult = new ParseResult(projectName, documents, notFoundDocs);

		return parseResult;
	}
}
