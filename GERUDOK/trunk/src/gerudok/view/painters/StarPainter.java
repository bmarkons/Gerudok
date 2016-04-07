package gerudok.view.painters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import gerudok.model.GraphicSlotElement;

public class StarPainter extends ElementPainter {
	private static final long serialVersionUID = -4427769004994299310L;
	private Rectangle2D rect;
	private Shape shape;
	
	public StarPainter(GraphicSlotElement element) {
		super(element);
	}

	@Override
	public void paint(Graphics2D g) {
		Point position = getElement().getPosition();
		/*
		 * Iscrtati zvezdu. Polje position predstavlja referentu poziciju na
		 * kojoj treba izvrsiti iscrtavanje. Polje color predstavlja boju koju
		 * treba koristiti. Donji kod, koji iscrtava liniju, zameniti novim
		 * kodom koji iscrtava zvezdu.
		 */
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.BLUE);
		double x = position.getX();
		double y = position.getY();
		double xPoints[] = { x + 55, x + 67, x + 109, x + 73, x + 83, x + 55,
				x + 27, x + 37, x + 1, x + 43 };

		double yPoints[] = { y, y + 36, y + 36, y + 54, y + 96, y + 72, y + 96,
				y + 54, y + 36, y + 36, };

		GeneralPath star = new GeneralPath();
		star.moveTo(xPoints[0], yPoints[0]);
		for (int k = 1; k < xPoints.length; k++)
			star.lineTo(xPoints[k], yPoints[k]);

		// close the shape
		star.closePath();
		// draw the outline
		g.draw(star);
		g.setColor(Color.YELLOW);
		g.fill(star);
		g.setColor(Color.BLUE);
		g.draw(star);

		rect = star.getBounds2D();
		shape = star;
	}

	@Override
	public Rectangle2D getRectangle(){
		return rect;
	}

	@Override
	public Shape getShape() {
		return shape;
	}
}
