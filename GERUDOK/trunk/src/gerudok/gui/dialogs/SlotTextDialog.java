package gerudok.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JToolBar;
import javax.swing.text.StyledEditorKit;

import gerudok.gui.MainFrameGerudok;
import gerudok.model.SlotText;
import gerudok.view.SlotTextView;
import gerudok.view.SlotView;

public class SlotTextDialog extends JDialog {
	private static final long serialVersionUID = -6660328054235775838L;

	private SlotTextView view = null;
	private TextSlotToolbar toolbar = null;

	public SlotTextDialog(SlotText slot) {
		super(MainFrameGerudok.getInstance(), "Edit " + slot.getName(), true);
		//setLocationRelativeTo(MainFrameGerudok.getInstance());
		setLayout(new BorderLayout());
		setSize(SlotView.SLOT_DIM);
		
		this.view = new SlotTextView(slot, true);
		view.getTextArea().setText(slot.getText());
		toolbar = new TextSlotToolbar();
		
		add(toolbar, BorderLayout.WEST);
		add(view, BorderLayout.CENTER);
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				((SlotText)slot).setText(view.getTextArea().getText());			
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private class TextSlotToolbar extends JToolBar {
		private static final long serialVersionUID = 1L;

		public TextSlotToolbar() {
			super(JToolBar.VERTICAL);
			setFloatable(false);
			setCursor(getCursor());
			JButton bold = new JButton();
			bold.setToolTipText("Bold Text");
			bold.setIcon(new ImageIcon("images/toolbar_slotview/bold.jpg"));
			bold.addActionListener(new StyledEditorKit.BoldAction());
			add(bold);

			JButton italic = new JButton();
			italic.setToolTipText("Italic Text");
			italic.setIcon(new ImageIcon("images/toolbar_slotview/italic.jpg"));
			italic.addActionListener(new StyledEditorKit.ItalicAction());
			add(italic);

			JButton underline = new JButton();
			underline.setToolTipText("Underline Text");
			underline.setIcon(new ImageIcon("images/toolbar_slotview/underline.jpg"));
			underline.addActionListener(new StyledEditorKit.UnderlineAction());
			add(underline);

			// buduca implementacija velicine fonta sancagay
			// JButton font = new JButton();
			// font.setToolTipText("Font");
			// font.setIcon(new ImageIcon(
			// "images/toolbar_slotview/underline.jpg"));
			// font.addActionListener(new ActionListener() {
			//
			// @Override
			// public void actionPerformed(ActionEvent e) {
			// new StyledEditorKit.FontSizeAction("myaction-",
			// 20).actionPerformed(e);
			//
			// }
			// });
			// add(font);
		}

	}
}
