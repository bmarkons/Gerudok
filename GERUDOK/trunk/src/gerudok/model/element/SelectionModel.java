package gerudok.model.element;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultSingleSelectionModel;
import javax.swing.event.EventListenerList;

import gerudok.model.GraphicSlotElement;

@SuppressWarnings("serial")
public class SelectionModel extends DefaultSingleSelectionModel {

	private ArrayList<GraphicSlotElement> selectionList = new ArrayList<GraphicSlotElement>();

	transient EventListenerList listenerList = new EventListenerList();

	public void addToSelectionList(GraphicSlotElement element) {
		selectionList.add(element);

		//fireUpdatePerformed();
	}

	public void addToSelectionList(ArrayList<GraphicSlotElement> list) {
		selectionList.addAll(list);

		//fireUpdatePerformed();
	}

	public int getSelectionListSize() {
		return selectionList.size();
	}

	public GraphicSlotElement getElementFromSelectionListAt(int index) {
		return selectionList.get(index);
	}

	public int getIndexByObject(GraphicSlotElement element) {
		return selectionList.indexOf(element);
	}

	public void removeFromSelectionList(GraphicSlotElement element) {
		selectionList.remove(element);

		// fireUpdatePerformed();
	}

	public void removeAllFromSelectionList() {
		selectionList.clear();

		// fireUpdatePerformed();
	}

	public ArrayList<GraphicSlotElement> getSelectionList() {
		return selectionList;
	}

	public Iterator<GraphicSlotElement> getSelectionListIterator() {
		return selectionList.iterator();
	}

	public boolean isElementSelected(GraphicSlotElement element) {
		return selectionList.contains(element);
	}

	public void selectElements(Rectangle2D rec, ArrayList<GraphicSlotElement> elements) {
		Iterator<GraphicSlotElement> it = elements.iterator();
		while (it.hasNext()) {
			GraphicSlotElement element = it.next();

			if (rec.intersects(element.getPainter().getRectangle())) {
				if (!isElementSelected(element))
					selectionList.add(element);
			}
		}
	}

}
