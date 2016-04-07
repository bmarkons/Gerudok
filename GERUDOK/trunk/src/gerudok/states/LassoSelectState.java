package gerudok.states;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import gerudok.model.SlotGraphic;
import gerudok.view.SlotGraphicView;
import gerudok.view.SlotView;

public class LassoSelectState extends State {

	Rectangle2D rect = new Rectangle2D.Double();
	SlotView sv;

	public LassoSelectState(SlotView view) {
		super(view);
		sv = view;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {

		SlotGraphicView sgv;

		Point2D mousePos = e.getPoint();

		if (sv instanceof SlotGraphicView)
			sgv = (SlotGraphicView) sv;
		else
			return;

		if (!e.isControlDown()) {
			sgv.getSelectionModel().removeAllFromSelectionList();
		}

		double width = mousePos.getX() - sgv.getLastPosition().getX();
		double height = mousePos.getY() - sgv.getLastPosition().getY();
		if ((width < 0) && (height < 0)) {
			rect.setRect(mousePos.getX(), mousePos.getY(), Math.abs(width), Math.abs(height));
		} else if ((width < 0) && (height >= 0)) {
			rect.setRect(mousePos.getX(), sgv.getLastPosition().getY(), Math.abs(width), Math.abs(height));
		} else if ((width > 0) && (height < 0)) {
			rect.setRect(sgv.getLastPosition().getX(), mousePos.getY(), Math.abs(width), Math.abs(height));
		} else {
			rect.setRect(sgv.getLastPosition().getX(), sgv.getLastPosition().getY(), Math.abs(width), Math.abs(height));
		}
		sgv.setSelectionRectangle(rect);

		sgv.getSelectionModel().selectElements(rect, ((SlotGraphic) sgv.getSlot()).getElements());

		sgv.repaint();

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		SlotGraphicView sgv;

		if (sv instanceof SlotGraphicView)
			sgv = (SlotGraphicView) sv;
		else
			return;

		sgv.setSelectionRectangle(new Rectangle2D.Double(0, 0, 0, 0));
		sgv.repaint();
		sgv.getStateManager().setSelectState();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
