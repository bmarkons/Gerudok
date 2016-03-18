package gerudok.model.element;

import java.awt.Point;

import gerudok.model.GraphicSlotElement;
import gerudok.model.SlotGraphic;
import gerudok.view.painters.FrowneyPainter;

public class FrowneyElement extends GraphicSlotElement{
	private static final long serialVersionUID = -3723941854985928823L;

	public FrowneyElement(SlotGraphic parent, Point position) {
		super(parent, position);
		setName("Frowney");
		setPainter(new FrowneyPainter(this));
	}

}
