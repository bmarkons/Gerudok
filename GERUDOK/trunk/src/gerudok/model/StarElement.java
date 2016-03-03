package gerudok.model;

import java.awt.Point;

import gerudok.view.painters.StarPainter;

public class StarElement extends GraphicSlotElement {
	private static final long serialVersionUID = -9134833273048330506L;

	public StarElement(SlotGraphic parent, Point position) {
		super(parent,position);
		this.name = "Star";
		this.painter = new StarPainter(this);
	}
}