package gerudok.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.EventListener;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JToolBar;

import gerudok.actions.CommandRedoAction;
import gerudok.actions.CommandUndoAction;
import gerudok.actions.manager.AbstractActionIcon;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.GraphicSlotElement;
import gerudok.model.SlotGraphic;
import gerudok.model.element.SelectionModel;
import gerudok.slot.actions.NewFrowney;
import gerudok.slot.actions.NewSmiley;
import gerudok.slot.actions.NewStar;
import gerudok.slot.actions.SelectAction;
import gerudok.states.StateManager;
import gerudok.view.SlotGraphicView;

public class SlotGraphicDialog extends JDialog implements EventListener{
	private static final long serialVersionUID = 1849432776128841231L;

	private SlotGraphicView view = null;
	private GraphicSlotToolbar toolBar = null;
	private StateManager stateManager = null;

	private DiagramController controler = new DiagramController();
	
	public StateManager getStateManager() {
		return stateManager;
	}
	
	public SlotGraphicDialog(SlotGraphic slot, Dimension dim) {
		super(MainFrameGerudok.getInstance(), "Edit " + slot.getName(), true);
		setLocationRelativeTo(MainFrameGerudok.getInstance());
		setLayout(new BorderLayout());
		setSize(dim);

		this.view = new SlotGraphicView(slot, true);
		this.stateManager = view.getStateManager();
		/*
		view.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				stateManager.getCurrentState().mouseClicked(e);
				repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		*/
		
		view.addMouseListener(controler);
		view.addMouseMotionListener(controler);
		
		//stateManager = new StateManager(view);
		toolBar = new GraphicSlotToolbar();

		add(toolBar, BorderLayout.WEST);
		add(view, BorderLayout.CENTER);
	}

	private class GraphicSlotToolbar extends JToolBar {
		private static final long serialVersionUID = -4349172055812358634L;

		public GraphicSlotToolbar() {
			super(JToolBar.VERTICAL);
			setFloatable(false);
			setCursor(getCursor());

			add(new NewStar(stateManager));

			add(new NewSmiley(stateManager));

			add(new NewFrowney(stateManager));

			add(new SelectAction(stateManager));

			addSeparator();

			add(new CommandUndoAction((SlotGraphic) (view.getSlot()), AbstractActionIcon.small));

			add(new CommandRedoAction((SlotGraphic) (view.getSlot()), AbstractActionIcon.small));
		}
	}
	
	/**************************************/
	
	private class DiagramController extends MouseAdapter implements MouseMotionListener {

		public void mousePressed(MouseEvent e) {
			lastPosition = e.getPoint();
			view.setLastPosition(lastPosition);
			stateManager.getCurrentState().mousePressed(e);
		}

		public void mouseReleased(MouseEvent e) {
			stateManager.getCurrentState().mouseReleased(e);
		}

		public void mouseDragged(MouseEvent e) {
			stateManager.getCurrentState().mouseDragged(e);
		}

		public void mouseMoved(MouseEvent e) {
			stateManager.getCurrentState().mouseMoved(e);
		}

	}
	
	
	
	public enum Handle {
		North, South, East, West, SouthEast, SouthWest, NorthEast, NorthWest
	}

	static final int handleSize = 7;

	private SelectionModel selectionModel = new SelectionModel();

	private Point2D lastPosition = null;
	private Rectangle2D selectionRectangle = new Rectangle2D.Double();

	public Point2D getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(Point2D lastPosition) {
		this.lastPosition = lastPosition;
	}

	public Rectangle2D getSelectionRectangle() {
		return selectionRectangle;
	}

	public void setSelectionRectangle(Rectangle2D selectionRectangle) {
		this.selectionRectangle = selectionRectangle;
	}

	/*****************************************************/

	private Point2D getHandlePoint(Point2D topLeft, Dimension2D size, Handle handlePosition) {
		double x = 0, y = 0;

		// OdreÄ‘ivanje y koordinate

		// Ako su gornji hendlovi
		if (handlePosition == Handle.NorthWest || handlePosition == Handle.North
				|| handlePosition == Handle.NorthEast) {
			y = topLeft.getY();
		}
		// Ako su centralni po y osi
		if (handlePosition == Handle.East || handlePosition == Handle.West) {
			y = topLeft.getY() + size.getHeight() / 2;
		}
		// Ako su donji
		if (handlePosition == Handle.SouthWest || handlePosition == Handle.South
				|| handlePosition == Handle.SouthEast) {
			y = topLeft.getY() + size.getHeight();
		}

		// OdreÄ‘ivanje x koordinate

		// Ako su levi
		if (handlePosition == Handle.NorthWest || handlePosition == Handle.West || handlePosition == Handle.SouthWest) {
			x = topLeft.getX();
		}
		// ako su centralni po x osi
		if (handlePosition == Handle.North || handlePosition == Handle.South) {
			x = topLeft.getX() + size.getWidth() / 2;
		}
		// ako su desni
		if (handlePosition == Handle.NorthEast || handlePosition == Handle.East || handlePosition == Handle.SouthEast) {
			x = topLeft.getX() + size.getWidth();
		}

		return new Point2D.Double(x, y);

	}

	/**
	 * Na osnovu hendla iznad koga se nalazi postavlja kursor
	 */
	public void setMouseCursor(Point2D point) {

		Handle handle = getDeviceAndHandleForPoint(point);

		if (handle != null) {
			Cursor cursor = null;

			switch (handle) {
			case North:
				cursor = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
				break;
			case South:
				cursor = Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
				break;
			case East:
				cursor = Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
				break;
			case West:
				cursor = Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
				break;
			case SouthEast:
				cursor = Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
				break;
			case NorthWest:
				cursor = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
				break;
			case SouthWest:
				cursor = Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
				break;
			case NorthEast:
				cursor = Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
				break;
			}
			this.setCursor(cursor);
		} else
			this.setCursor(Cursor.getDefaultCursor());
	}

	public Handle getDeviceAndHandleForPoint(Point2D point) {
		GraphicSlotElement element;

		Iterator<GraphicSlotElement> it = selectionModel.getSelectionListIterator();
		while (it.hasNext()) {
			element = it.next();
			return getHandleForPoint(element, point);
		}
		return null;
	}

	private Handle getHandleForPoint(GraphicSlotElement element, Point2D point) {
		for (Handle h : Handle.values()) {
			if (isPointInHandle(element, point, h)) {
				return h;
			}
		}
		return null;
	}

	private boolean isPointInHandle(GraphicSlotElement element, Point2D point, Handle handle) {
		Dimension dim = new Dimension();
		dim.setSize(element.getPainter().getRectangle().getWidth(), element.getPainter().getRectangle().getHeight());
		Point2D handleCenter = getHandlePoint(element.getPosition(), dim, handle);
		return ((Math.abs(point.getX() - handleCenter.getX()) <= (double) handleSize / 2)
				&& (Math.abs(point.getY() - handleCenter.getY()) <= (double) handleSize / 2));
	}

}
