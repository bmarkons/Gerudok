package gerudok.view;

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
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import gerudok.gui.MainFrameGerudok;
import gerudok.gui.dialogs.SlotGraphicDialog;
import gerudok.model.GraphicSlotElement;
import gerudok.model.Slot;
import gerudok.model.SlotGraphic;
import gerudok.view.painters.ElementPainter;

public class SlotGraphicView extends SlotView implements FocusListener {
	private static final long serialVersionUID = -5207430261641543334L;

	// private GraphicSlotToolbar toolBar = null;
	// private StateManager stateManager = null;

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
				if(SwingUtilities.isLeftMouseButton(e) && !(SlotGraphicView.this.getParent() instanceof SlotGraphicDialog)){
					if(e.getClickCount() == 2){
						SlotGraphicDialog dialog = new SlotGraphicDialog((SlotGraphic)SlotGraphicView.this.getSlot(),SlotGraphicView.this.getSize());
						dialog.setVisible(true);
					}
				}
			}
		});
		
		setBackground(Color.WHITE);
	}

	public SlotGraphicView(Slot slot, boolean Dialog) {
		super(slot);

		addFocusListener(this);

		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setBackground(Color.WHITE);
		
		// stateManager = new StateManager(this);

		// toolBar = new GraphicSlotToolbar();
		// add(toolBar, BorderLayout.EAST);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		ArrayList<GraphicSlotElement> elements = ((SlotGraphic) slot).getElements();
		for (GraphicSlotElement element : elements) {
			ElementPainter painter = element.getPainter();
			painter.paint(g2);
		}
	}

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
