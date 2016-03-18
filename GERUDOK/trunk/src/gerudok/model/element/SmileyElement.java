package gerudok.model.element;

import java.awt.Point;

import gerudok.model.GraphicSlotElement;
import gerudok.model.SlotGraphic;
import gerudok.view.painters.SmileyPainter;

public class SmileyElement extends GraphicSlotElement {
	private static final long serialVersionUID = 5621100786156157501L;

	public SmileyElement(SlotGraphic parent, Point position) {
		super(parent, position);
		setName("Smiley");
		setPainter(new SmileyPainter(this));
	}
}
