package gerudok.view.painters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;

import gerudok.model.GraphicSlotElement;

public class SmileyPainter extends ElementPainter {
	private static final long serialVersionUID = 3066960397745139340L;

	public SmileyPainter(GraphicSlotElement element) {
		super(element);
	}

	@Override
	public void paint(Graphics2D g) {
		Point position = this.element.getPosition();
		/*
		 * Iscrtati Smajlija. Polje position predstavlja referentu poziciju na
		 * kojoj treba izvrsiti iscrtavanje. Polje color predstavlja boju koju
		 * treba koristiti. Donji kod, koji iscrtava liniju, zameniti novim
		 * kodom koji iscrtava smajlija.
		 */
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.YELLOW);
		double x = position.getX();
		double y = position.getY();
		Ellipse2D krug = new Ellipse2D.Double(x, y, 125, 125);
		g.draw(krug);
		g.fill(krug);
		g.setColor(Color.BLACK);
		g.draw(krug);
		Ellipse2D loko = new Ellipse2D.Double(x + 25, y + 25, 20, 20);
		Ellipse2D doko = new Ellipse2D.Double(x + 80, y + 25, 20, 20);
		g.fill(loko);
		g.fill(doko);
		QuadCurve2D smile = new QuadCurve2D.Double(x + 15, y + 70, 
				x + 65, y+95,
				x + 110, y + 70);
		g.draw(smile);

		QuadCurve2D smile2 = new QuadCurve2D.Double(x + 15, y + 70, 
				x + 65,	y+155, 
				x + 110, y + 70);
		g.draw(smile2);

		g.setColor(Color.WHITE);

		g.fill(smile2);
		g.setColor(Color.YELLOW);
		g.fill(smile);
		g.setColor(Color.BLACK);
		g.draw(smile);
		
		QuadCurve2D smile3 = new QuadCurve2D.Double(x + 15, y + 70, 
				x + 65, y+125,
				x + 110, y + 70);
		g.draw(smile3);
		
		Line2D linija = new Line2D.Double(x+62, y+82, x+62, y+112);
		g.draw(linija);
		Line2D linija2 = new Line2D.Double(x+80, y+82, x+80, y+108);
		g.draw(linija2);
		Line2D linija3 = new Line2D.Double(x+44, y+80, x+44, y+106);
		g.draw(linija3);
		Line2D linija4 = new Line2D.Double(x+98, y+76, x+98, y+90);
		g.draw(linija4);
		Line2D linija5 = new Line2D.Double(x+26, y+75, x+26, y+87);
		g.draw(linija5);
	}

}
