package gerudok.model.element;

import java.awt.Point;

import gerudok.model.GraphicSlotElement;
import gerudok.model.SlotGraphic;
import gerudok.view.painters.StarPainter;

public class StarElement extends GraphicSlotElement {
	private static final long serialVersionUID = -9134833273048330506L;

	public StarElement(SlotGraphic parent, Point position) {
		super(parent,position);
		setName("Star");
		setPainter(new StarPainter(this));
	}
}