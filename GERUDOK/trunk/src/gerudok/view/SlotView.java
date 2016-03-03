package gerudok.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import gerudok.actions.CommandRedoAction;
import gerudok.actions.CommandUndoAction;
import gerudok.comands.CommandManager;
import gerudok.model.GraphicSlotElement;
import gerudok.model.Slot;
import gerudok.model.SlotGraphic;
import gerudok.model.SlotText;
import gerudok.view.painters.ElementPainter;
import gerudok.statepattern.StateManager;

public class SlotView extends JPanel implements FocusListener, MouseListener, ActionListener {
	private static final long serialVersionUID = 4449718288145521263L;

	public enum SlotType {
		GRAPHIC, TEXT
	};

	private CommandManager commandManager = new CommandManager();
	Slot slot;
	String name = null;
	SlotType type = null;
	JTextArea textArea = null;

	GraphicSlotToolbar slotToolBar = null;
	TextSlotToolbar tslotToolBar = null;

	StateManager stateManager = null;

	public SlotView(Slot slot) {
		super(new BorderLayout());
		this.slot = slot;
		name = slot.getName();

		// bolje konfigurisati...
		setPreferredSize(new Dimension(PageView.PAGE_WIDTH - 5, PageView.PAGE_HEIGHT / 3));
		addFocusListener(this);
		addMouseListener(this);

		if (slot instanceof SlotText) {
			type = SlotType.TEXT;
			textArea = new JTextArea("");
			JScrollPane scrollPane = new JScrollPane(textArea);
			add(scrollPane, BorderLayout.CENTER);
			JButton saveBtn = new JButton("Save text");
			// Dugme za pamcenje unetog teksta
			saveBtn.addActionListener(this);
			add(saveBtn, BorderLayout.SOUTH);

			tslotToolBar = new TextSlotToolbar();
			add(tslotToolBar, BorderLayout.EAST);

		} else if (slot instanceof SlotGraphic) {
			type = SlotType.GRAPHIC;
			setBorder(BorderFactory.createLineBorder(Color.BLACK));

			slotToolBar = new GraphicSlotToolbar();
			add(slotToolBar, BorderLayout.EAST);

			stateManager = new StateManager(this);

		}
	}

	public CommandManager getCommandManager() {
		return commandManager;
	}

	public void setCommandManager(CommandManager commandManager) {
		this.commandManager = commandManager;
	}

	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	public String getName() {
		return name;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public SlotType getType() {
		return type;
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (type == SlotType.TEXT)
			textArea.requestFocus();
		else {
			setBorder(BorderFactory.createLineBorder(Color.BLUE));
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (type == SlotType.GRAPHIC) {
			setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.requestFocus();

		if (type == SlotType.GRAPHIC) {
			// Ukoliko je graficki slot dodaj odredjeni element
			stateManager.getCurrentState().mouseClicked(e);
			// repaint();
		}
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
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SlotText slotTxt = (SlotText) slot;
		slotTxt.setText(textArea.getText());
		JOptionPane.showMessageDialog(textArea, "Text saved!", slotTxt.getName(), JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (type == SlotType.GRAPHIC) {
			Graphics2D g2 = (Graphics2D) g;
			ArrayList<GraphicSlotElement> elements = ((SlotGraphic) slot).getElements();
			for (GraphicSlotElement element : elements) {
				ElementPainter painter = element.getPainter();
				painter.paint(g2);
			}
		}

	}

	public class TextSlotToolbar extends JToolBar {
		private static final long serialVersionUID = 1L;

		public TextSlotToolbar() {
			super(JToolBar.VERTICAL);
			setFloatable(false);
			setCursor(getCursor());
			JButton bold = new JButton();
			bold.setToolTipText("Bold Text");
			bold.setIcon(new ImageIcon("images/toolbar_slotview/bold.jpg"));
			bold.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub

				}
			});
			add(bold);

			JButton italic = new JButton();
			italic.setToolTipText("Italic Text");
			italic.setIcon(new ImageIcon("images/toolbar_slotview/italic.jpg"));
			italic.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub

				}
			});
			add(italic);

			JButton underline = new JButton();
			underline.setToolTipText("Underline Text");
			underline.setIcon(new ImageIcon("images/toolbar_slotview/underline.jpg"));
			underline.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub

				}
			});
			add(underline);
		}

	}

	public class GraphicSlotToolbar extends JToolBar {
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
					Image img = kit.getImage("images/toolbar_slotview/star.png");
					Cursor cursor = kit.createCustomCursor(img, new Point(0, 0), "Star");
					getParent().setCursor(cursor);
				}
			});
			add(starBtn);

			JButton smileyBtn = new JButton();
			smileyBtn.setToolTipText("Smiley");
			smileyBtn.setIcon(new ImageIcon("images/toolbar_slotview/smiley.png"));
			smileyBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					stateManager.setSmileyState();
					// Promena izgleda kursora
					Toolkit kit = Toolkit.getDefaultToolkit();
					Image img = kit.getImage("images/toolbar_slotview/smiley.png");
					Cursor cursor = kit.createCustomCursor(img, new Point(0, 0), "Smiley");
					getParent().setCursor(cursor);
				}
			});
			add(smileyBtn);

			JButton frowneyBtn = new JButton();
			frowneyBtn.setToolTipText("Smiley");
			frowneyBtn.setIcon(new ImageIcon("images/toolbar_slotview/frowney.png"));
			frowneyBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					stateManager.setFrowneyState();
					// Promena izgleda kursora
					Toolkit kit = Toolkit.getDefaultToolkit();
					Image img = kit.getImage("images/toolbar_slotview/frowney.png");
					Cursor cursor = kit.createCustomCursor(img, new Point(0, 0), "Frowney");
					getParent().setCursor(cursor);
				}
			});
			add(frowneyBtn);

			JButton selectBtn = new JButton();
			selectBtn.setToolTipText("Select");
			selectBtn.setIcon(new ImageIcon("images/toolbar_slotview/select.png"));
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
			undoBtn.addActionListener(new CommandUndoAction((SlotGraphic)slot));
			add(undoBtn);
			
			JButton redoBtn = new JButton();
			redoBtn.setToolTipText("Redo");
			redoBtn.setIcon(new ImageIcon("images/toolbar_slotview/redo.png"));
			redoBtn.addActionListener(new CommandRedoAction((SlotGraphic)slot));
			add(redoBtn);
		}
	}
}
