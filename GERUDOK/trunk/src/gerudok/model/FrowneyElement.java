package gerudok.model;

import java.awt.Point;

import gerudok.view.painters.FrowneyPainter;

public class FrowneyElement extends GraphicSlotElement{
	private static final long serialVersionUID = -3723941854985928823L;

	public FrowneyElement(SlotGraphic parent, Point position) {
		super(parent, position);
		this.name = "Frowney";
		this.painter = new FrowneyPainter(this);
	}

}
