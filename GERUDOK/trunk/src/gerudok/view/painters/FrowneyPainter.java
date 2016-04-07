package gerudok.view.painters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;

import gerudok.model.GraphicSlotElement;

public class FrowneyPainter extends ElementPainter {
	private static final long serialVersionUID = -4476634890356537059L;
	private Rectangle2D rect;
	private Shape shape;
	
	public FrowneyPainter(GraphicSlotElement element) {
		super(element);
	}

	@Override
	public void paint(Graphics2D g) {
		Point position = getElement().getPosition();
		/*
		 * Iscrtati tuznog smajlija. Polje position predstavlja referentu
		 * poziciju na kojoj treba izvrsiti iscrtavanje. Polje color predstavlja
		 * boju koju treba koristiti. Donji kod, koji iscrtava liniju, zameniti
		 * novim kodom koji iscrtava tuznog smajlija.
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
		

		QuadCurve2D frow = new QuadCurve2D.Double(x + 25, y + 100, x + 50,
				y + 60, x + 100, y + 100);
		g.draw(frow);
		
		rect = krug.getBounds2D();
		shape = krug;
	}

	@Override
	public Rectangle2D getRectangle() {
		return rect;
	}

	@Override
	public Shape getShape() {
		return shape;
	}

}
