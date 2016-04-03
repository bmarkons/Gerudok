package gerudok.view;

import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.text.StyledEditorKit;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import gerudok.gui.MainFrameGerudok;
import gerudok.model.Slot;
import gerudok.model.SlotText;

public class SlotTextView extends SlotView {
	private static final long serialVersionUID = 3150036223105443249L;

	private JTextPane textArea = null;
	private TextSlotToolbar toolbar = null;

	public SlotTextView(Slot slot) {
		super(slot);

		// skrolabilni deo za tekst
		textArea = new JTextPane();
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane, BorderLayout.CENTER);
		textArea.setContentType("text/html");
		
		
		textArea.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				((SlotText)slot).setText(textArea.getText());	
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				DefaultTreeModel m = (DefaultTreeModel) MainFrameGerudok.getInstance().getTree().getModel();
				TreeNode[] n = m.getPathToRoot(slot);
				
				
				MainFrameGerudok.getInstance().getTree().scrollPathToVisible(new TreePath(n));
				MainFrameGerudok.getInstance().getTree().setSelectionPath(new TreePath(n));		
				SwingUtilities.updateComponentTreeUI(MainFrameGerudok.getInstance()
						.getTree());
			}
		});
		
		toolbar = new TextSlotToolbar();
		add(toolbar, BorderLayout.EAST);
	}

	public JEditorPane getTextArea() {
		return textArea;
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
			underline.setIcon(new ImageIcon(
					"images/toolbar_slotview/underline.jpg"));
			underline.addActionListener(new StyledEditorKit.UnderlineAction());
			add(underline);
			
			//buduca implementacija velicine fonta sancagay
//			JButton font = new JButton();
//			font.setToolTipText("Font");
//			font.setIcon(new ImageIcon(
//					"images/toolbar_slotview/underline.jpg"));
//			font.addActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					new StyledEditorKit.FontSizeAction("myaction-", 20).actionPerformed(e);
//					
//				}
//			});
//			add(font);
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		textArea.setText(((SlotText)slot).getText());
	}
}
