package gerudok.model;

import java.awt.Point;

import gerudok.view.painters.SmileyPainter;

public class SmileyElement extends GraphicSlotElement {
	private static final long serialVersionUID = 5621100786156157501L;

	public SmileyElement(SlotGraphic parent, Point position) {
		super(parent, position);
		this.name = "Smiley";
		this.painter = new SmileyPainter(this);
	}
}
