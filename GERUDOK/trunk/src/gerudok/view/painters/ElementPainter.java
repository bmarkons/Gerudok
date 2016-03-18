package gerudok.view.painters;

import java.awt.Graphics2D;
import java.io.Serializable;

import gerudok.model.GraphicSlotElement;

public abstract class ElementPainter implements Serializable{
	private static final long serialVersionUID = -5044489850009660235L;
	
	private GraphicSlotElement element;
	
	public ElementPainter(GraphicSlotElement element){
		this.element = element;
	}
	
	public abstract void paint(Graphics2D g);
	
	public GraphicSlotElement getElement() {
		return element;
	}
}
