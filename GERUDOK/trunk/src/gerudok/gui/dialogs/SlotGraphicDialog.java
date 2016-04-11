package gerudok.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.EventListener;

import javax.swing.JDialog;
import javax.swing.JToolBar;

import gerudok.actions.manager.ActionManager;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.SlotGraphic;
import gerudok.slot.actions.NewFrowney;
import gerudok.slot.actions.NewSmiley;
import gerudok.slot.actions.NewStar;
import gerudok.slot.actions.SelectAction;
import gerudok.states.StateManager;
import gerudok.view.SlotGraphicView;
import gerudok.view.SlotView;

public class SlotGraphicDialog extends JDialog implements EventListener{
	private static final long serialVersionUID = 1849432776128841231L;

	private SlotGraphicView view = null;
	private GraphicSlotToolbar toolBar = null;
	private StateManager stateManager = null;

	private DiagramController controler = new DiagramController();
	
	public StateManager getStateManager() {
		return stateManager;
	}
	
	public SlotGraphicDialog(SlotGraphic slot) {
		super(MainFrameGerudok.getInstance(), "Edit " + slot.getName(), true);
		setLocationRelativeTo(MainFrameGerudok.getInstance());
		setLayout(new BorderLayout());
		setSize(SlotView.SLOT_DIM);

		this.view = new SlotGraphicView(slot, true);
		this.stateManager = view.getStateManager();
		
		view.addMouseListener(controler);
		view.addMouseMotionListener(controler);
		
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
			
			//add(new CommandUndoAction((SlotGraphic) (view.getSlot()), AbstractActionIcon.small));
			add(ActionManager.getInstance().getUndo());
			
			//add(new CommandRedoAction((SlotGraphic) (view.getSlot()), AbstractActionIcon.small));
			add(ActionManager.getInstance().getRedo());
			
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
	
}
