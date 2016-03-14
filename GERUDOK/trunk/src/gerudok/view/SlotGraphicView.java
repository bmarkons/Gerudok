package gerudok.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import gerudok.actions.CommandRedoAction;
import gerudok.actions.CommandUndoAction;
import gerudok.model.GraphicSlotElement;
import gerudok.model.Slot;
import gerudok.model.SlotGraphic;
import gerudok.statepattern.StateManager;
import gerudok.view.painters.ElementPainter;

public class SlotGraphicView extends SlotView implements FocusListener,
		MouseListener {
	private static final long serialVersionUID = -5207430261641543334L;

	GraphicSlotToolbar toolBar = null;
	StateManager stateManager = null;

	public SlotGraphicView(Slot slot) {
		super(slot);

		addFocusListener(this);
		addMouseListener(this);

		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		toolBar = new GraphicSlotToolbar();
		add(toolBar, BorderLayout.EAST);

		stateManager = new StateManager(this);
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

	private class GraphicSlotToolbar extends JToolBar {
		private static final long serialVersionUID = -4349172055812358634L;

		public GraphicSlotToolbar() {
			super(JToolBar.VERTICAL);
			setFloatable(false);
			setCursor(getCursor());

			JButton starBtn = new JButton();
			starBtn.setToolTipText("Star");
			starBtn.setIcon(new ImageIcon("images/toolbar_slotview/star.png"));
			starBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					stateManager.setStarState();
					// Promena izgleda kursora
					Toolkit kit = Toolkit.getDefaultToolkit();
					Image img = kit
							.getImage("images/toolbar_slotview/star.png");
					Cursor cursor = kit.createCustomCursor(img,
							new Point(0, 0), "Star");
					getParent().setCursor(cursor);
				}
			});
			add(starBtn);

			JButton smileyBtn = new JButton();
			smileyBtn.setToolTipText("Smiley");
			smileyBtn.setIcon(new ImageIcon(
					"images/toolbar_slotview/smiley.png"));
			smileyBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					stateManager.setSmileyState();
					// Promena izgleda kursora
					Toolkit kit = Toolkit.getDefaultToolkit();
					Image img = kit
							.getImage("images/toolbar_slotview/smiley.png");
					Cursor cursor = kit.createCustomCursor(img,
							new Point(0, 0), "Smiley");
					getParent().setCursor(cursor);
				}
			});
			add(smileyBtn);

			JButton frowneyBtn = new JButton();
			frowneyBtn.setToolTipText("Smiley");
			frowneyBtn.setIcon(new ImageIcon(
					"images/toolbar_slotview/frowney.png"));
			frowneyBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					stateManager.setFrowneyState();
					// Promena izgleda kursora
					Toolkit kit = Toolkit.getDefaultToolkit();
					Image img = kit
							.getImage("images/toolbar_slotview/frowney.png");
					Cursor cursor = kit.createCustomCursor(img,
							new Point(0, 0), "Frowney");
					getParent().setCursor(cursor);
				}
			});
			add(frowneyBtn);

			JButton selectBtn = new JButton();
			selectBtn.setToolTipText("Select");
			selectBtn.setIcon(new ImageIcon(
					"images/toolbar_slotview/select.png"));
			selectBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					stateManager.setSelectState();
					getParent().setCursor(getCursor());
				}
			});
			add(selectBtn);

			addSeparator();

			JButton undoBtn = new JButton();
			undoBtn.setToolTipText("Undo");
			undoBtn.setIcon(new ImageIcon("images/toolbar_slotview/undo.png"));
			undoBtn.addActionListener(new CommandUndoAction((SlotGraphic) slot));
			add(undoBtn);

			JButton redoBtn = new JButton();
			redoBtn.setToolTipText("Redo");
			redoBtn.setIcon(new ImageIcon("images/toolbar_slotview/redo.png"));
			redoBtn.addActionListener(new CommandRedoAction((SlotGraphic) slot));
			add(redoBtn);
		}
	}
}
