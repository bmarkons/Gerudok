package gerudok.view.painters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;

import gerudok.model.GraphicSlotElement;

public class StarPainter extends ElementPainter {
	private static final long serialVersionUID = -4427769004994299310L;

	public StarPainter(GraphicSlotElement element) {
		super(element);
	}

	@Override
	public void paint(Graphics2D g) {
		Point position = this.element.getPosition();
		Color color = this.element.getColor();

		/*
		 * Iscrtati zvezdu. Polje position predstavlja referentu poziciju na
		 * kojoj treba izvrsiti iscrtavanje. Polje color predstavlja boju koju
		 * treba koristiti. Donji kod, koji iscrtava liniju, zameniti novim
		 * kodom koji iscrtava zvezdu.
		 */
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(color);
		double x = position.getX();
		double y = position.getY();
		// g.draw(new Line2D.Double(position.getX(), position.getY(),
		// position.getX() + 50, position.getY() + 50));
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

	}
}
