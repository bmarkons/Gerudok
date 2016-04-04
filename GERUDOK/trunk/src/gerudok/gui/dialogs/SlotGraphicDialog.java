package gerudok.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gerudok.actions.CommandRedoAction;
import gerudok.actions.CommandUndoAction;
import gerudok.actions.manager.AbstractActionIcon;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.SlotGraphic;
import gerudok.slot.actions.NewFrowney;
import gerudok.slot.actions.NewSmiley;
import gerudok.slot.actions.NewStar;
import gerudok.slot.actions.SelectAction;
import gerudok.states.StateManager;
import gerudok.view.SlotGraphicView;

import javax.swing.JDialog;
import javax.swing.JToolBar;

public class SlotGraphicDialog extends JDialog {
	private static final long serialVersionUID = 1849432776128841231L;

	private SlotGraphicView view = null;
	private GraphicSlotToolbar toolBar = null;
	private StateManager stateManager = null;

	public SlotGraphicDialog(SlotGraphic slot, Dimension dim) {
		super(MainFrameGerudok.getInstance(), "Edit " + slot.getName(), true);
		setLocationRelativeTo(MainFrameGerudok.getInstance());
		setLayout(new BorderLayout());
		setSize(dim);

		this.view = new SlotGraphicView(slot);
		view.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				stateManager.getCurrentState().mouseClicked(e);
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
		stateManager = new StateManager(view);
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

			add(new CommandUndoAction((SlotGraphic) (view.getSlot()),
					AbstractActionIcon.small));

			add(new CommandRedoAction((SlotGraphic) (view.getSlot()),
					AbstractActionIcon.small));
		}
	}
}
