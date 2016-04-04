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

public class SlotGraphicView extends SlotView {
	private static final long serialVersionUID = -5207430261641543334L;

	public SlotGraphicView(Slot slot, boolean isDialog) {
		super(slot);

		addFocusListener(new FocusListener() {
			
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
		});
		
		if (!isDialog) {
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
					if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
						SlotGraphicDialog dialog = new SlotGraphicDialog((SlotGraphic) SlotGraphicView.this.getSlot());
						dialog.setVisible(true);
					}
				}
			});
		}
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
	public void update(Observable o, Object arg) {
		SwingUtilities.updateComponentTreeUI(MainFrameGerudok.getInstance().getTree());
		repaint();
	}
}
