package gerudok.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import gerudok.gui.MainFrameGerudok;
import gerudok.gui.dialogs.SlotGraphicDialog;
import gerudok.model.GraphicSlotElement;
import gerudok.model.Slot;
import gerudok.model.SlotGraphic;
import gerudok.model.element.SelectionModel;
import gerudok.states.StateManager;
import gerudok.view.painters.ElementPainter;

public class SlotGraphicView extends SlotView implements FocusListener {
	private static final long serialVersionUID = -5207430261641543334L;

	// private GraphicSlotToolbar toolBar = null;
	private StateManager stateManager = null;

	public StateManager getStateManager() {
		return stateManager;
	}
	
	public SlotGraphicView(Slot slot) {
		super(slot);

		addFocusListener(this);
		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

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
				requestFocus();
				System.out.println(e.getSource());
				if (SwingUtilities.isLeftMouseButton(e)
						&& !(SlotGraphicView.this.getParent() instanceof SlotGraphicDialog)) {
					if (e.getClickCount() == 2) {
						SlotGraphicDialog dialog = new SlotGraphicDialog((SlotGraphic) SlotGraphicView.this.getSlot(),
								SlotGraphicView.this.getSize());
						dialog.setVisible(true);
					}
				}
			}
		});

		setBackground(Color.WHITE);
	}
	
	public SelectionModel getSelectionModel() {
		return selectionModel;
	}

	public SlotGraphicView(Slot slot, boolean Dialog) {
		super(slot);

		addFocusListener(this);

		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setBackground(Color.WHITE);

		stateManager = new StateManager(this);

		setBackground(Color.WHITE);
	}

	/*****************************************************/

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

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		ArrayList<GraphicSlotElement> elements = ((SlotGraphic) slot).getElements();
		for (GraphicSlotElement element : elements) {
			ElementPainter painter = element.getPainter();
			painter.paint(g2);
		}

		paintSelectionHandles(g2);

		// iscrtavanje pravougaonika za lasso
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke((float) 1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 1f,
				new float[] { (float) 3, (float) 6 }, 0));
		g2.draw(selectionRectangle);
	}

	private void paintSelectionHandles(Graphics2D g2) {

		Iterator<GraphicSlotElement> it = selectionModel.getSelectionListIterator();
		while (it.hasNext()) {
			GraphicSlotElement element = it.next();
			
			Dimension dim = new Dimension();
			dim.setSize(element.getPainter().getRectangle().getWidth(), element.getPainter().getRectangle().getHeight());
			
			// Iscrtavanje pravougaonika sa isprekidanom linijom
			g2.setStroke(new BasicStroke((float) 1, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 1f,
					new float[] { 3f, 6f }, 0));
			g2.setPaint(Color.BLACK);

			g2.drawRect((int) element.getPosition().getX(), (int) element.getPosition().getY(),
					(int) dim.getWidth(), (int) dim.getHeight());

			// Iscrtavanje hendlova
			for (Handle e : Handle.values()) {
				paintSelectionHandle(g2, getHandlePoint(element.getPosition(), dim, e));
			}

		}
	}

	private void paintSelectionHandle(Graphics2D g2, Point2D position) {
		double size = handleSize;
		g2.fill(new Rectangle2D.Double(position.getX() - size / 2, position.getY() - size / 2, size, size));
	}

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

	/************************************************************/

	@Override
	public void focusGained(FocusEvent e) {
		setBorder(BorderFactory.createLineBorder(Color.BLUE));

		DefaultTreeModel m = (DefaultTreeModel) MainFrameGerudok.getInstance().getTree().getModel();
		TreeNode[] n = m.getPathToRoot(slot);

		MainFrameGerudok.getInstance().getTree().scrollPathToVisible(new TreePath(n));
		MainFrameGerudok.getInstance().getTree().setSelectionPath(new TreePath(n));
		SwingUtilities.updateComponentTreeUI(MainFrameGerudok.getInstance().getTree());
	}

	@Override
	public void focusLost(FocusEvent e) {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	@Override
	public void update(Observable o, Object arg) {
		SwingUtilities.updateComponentTreeUI(MainFrameGerudok.getInstance().getTree());
		repaint();
	}

	// private class GraphicSlotToolbar extends JToolBar {
	// private static final long serialVersionUID = -4349172055812358634L;
	//
	// public GraphicSlotToolbar() {
	// super(JToolBar.VERTICAL);
	// setFloatable(false);
	// setCursor(getCursor());
	//
	// add(new NewStar(stateManager));
	//
	// add(new NewSmiley(stateManager));
	//
	// add(new NewFrowney(stateManager));
	//
	// add(new SelectAction(stateManager));
	//
	// addSeparator();
	//
	// add(new CommandUndoAction((SlotGraphic)slot, AbstractActionIcon.small));
	//
	// add(new CommandRedoAction((SlotGraphic)slot, AbstractActionIcon.small));
	// }
	// }
}
