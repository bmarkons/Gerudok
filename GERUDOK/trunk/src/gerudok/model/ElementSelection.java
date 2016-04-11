package gerudok.model;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;

public class ElementSelection implements Transferable {
	
	public static DataFlavor elementFlavor;
	
	private DataFlavor[] supportedFlavors = { elementFlavor };
	public ArrayList<GraphicSlotElement> elements = new ArrayList<GraphicSlotElement>();
	
	public ArrayList<GraphicSlotElement> getList() {
		return elements;
	}
	
	public ElementSelection(ArrayList<GraphicSlotElement> elements) {
		this.elements = elements;
		
		try {
			elementFlavor = new DataFlavor(Class.forName("java.util.ArrayList"), "Elements");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Object getTransferData(DataFlavor f)
			throws UnsupportedFlavorException, IOException {
		if(f.equals(elementFlavor))
			return (elements);
		else
			throw new UnsupportedFlavorException(elementFlavor);
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return (supportedFlavors);
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor f) {
		return (f.equals(elementFlavor));
	}

}
