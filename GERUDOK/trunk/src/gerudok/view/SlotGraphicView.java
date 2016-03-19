package gerudok.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import gerudok.actions.CommandRedoAction;
import gerudok.actions.CommandUndoAction;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.GraphicSlotElement;
import gerudok.model.Slot;
import gerudok.model.SlotGraphic;
import gerudok.slot.actions.NewFrowney;
import gerudok.slot.actions.NewSmiley;
import gerudok.slot.actions.NewStar;
import gerudok.slot.actions.SelectAction;
import gerudok.states.StateManager;
import gerudok.view.painters.ElementPainter;

public class SlotGraphicView extends SlotView implements FocusListener,
		MouseListener {
	private static final long serialVersionUID = -5207430261641543334L;

	private GraphicSlotToolbar toolBar = null;
	private StateManager stateManager = null;

	public SlotGraphicView(Slot slot) {
		super(slot);

		addFocusListener(this);
		addMouseListener(this);

		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		stateManager = new StateManager(this);
		
		toolBar = new GraphicSlotToolbar();
		add(toolBar, BorderLayout.EAST);		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		ArrayList<GraphicSlotElement> elements = ((SlotGraphic) slot)
				.getElements();
		for (GraphicSlotElement element : elements) {
			ElementPainter painter = element.getPainter();
			painter.paint(g2);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.requestFocus();
		stateManager.getCurrentState().mouseClicked(e);

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent e) {
		setBorder(BorderFactory.createLineBorder(Color.BLUE));
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

			add(new CommandUndoAction((SlotGraphic)slot));

			add(new CommandRedoAction((SlotGraphic)slot));
		}
	}
}
