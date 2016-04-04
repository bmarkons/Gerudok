package gerudok.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import gerudok.gui.MainFrameGerudok;
import gerudok.gui.dialogs.SlotTextDialog;
import gerudok.model.Slot;
import gerudok.model.SlotText;

public class SlotTextView extends SlotView {
	private static final long serialVersionUID = 3150036223105443249L;

	private JTextPane textArea = null;

	public SlotTextView(Slot slot, boolean isDialog) {
		super(slot);

		// skrolabilni deo za tekst
		textArea = new JTextPane();
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane, BorderLayout.CENTER);
		textArea.setContentType("text/html");
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		if (!isDialog) {
			textArea.setEditable(false);
			textArea.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
					setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}

				@Override
				public void focusGained(FocusEvent e) {
					DefaultTreeModel m = (DefaultTreeModel) MainFrameGerudok.getInstance().getTree().getModel();
					TreeNode[] n = m.getPathToRoot(slot);

					MainFrameGerudok.getInstance().getTree().scrollPathToVisible(new TreePath(n));
					MainFrameGerudok.getInstance().getTree().setSelectionPath(new TreePath(n));
					SwingUtilities.updateComponentTreeUI(MainFrameGerudok.getInstance().getTree());

					setBorder(BorderFactory.createLineBorder(Color.BLUE));
				}
			});
			textArea.addMouseListener(new MouseListener() {

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
					if (SwingUtilities.isLeftMouseButton(e)
							&& !(SlotTextView.this.getParent() instanceof SlotTextDialog)) {
						if (e.getClickCount() == 2) {
							SlotTextDialog dialog = new SlotTextDialog((SlotText) SlotTextView.this.getSlot());
							dialog.setVisible(true);
						}
					}
				}
			});
		}
	}

	public JEditorPane getTextArea() {
		return textArea;
	}

	@Override
	public void update(Observable o, Object arg) {
		textArea.setText(((SlotText) slot).getText());
	}
}
