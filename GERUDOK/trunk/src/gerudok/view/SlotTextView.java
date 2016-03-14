package gerudok.view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import gerudok.actions.SaveTextAction;
import gerudok.model.Slot;
import gerudok.model.SlotText;

public class SlotTextView extends SlotView {
	private static final long serialVersionUID = 3150036223105443249L;

	private JTextArea textArea = null;
	private TextSlotToolbar toolbar = null;

	public SlotTextView(Slot slot) {
		super(slot);

		// skrolabilni deo za tekst
		textArea = new JTextArea("");
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane, BorderLayout.CENTER);

		// Dugme za pamcenje unetog teksta
		JButton saveBtn = new JButton("Save text");
		saveBtn.addActionListener(new SaveTextAction((SlotText)slot));
		add(saveBtn, BorderLayout.SOUTH);
		toolbar = new TextSlotToolbar();
		add(toolbar, BorderLayout.EAST);
	}

	public JTextArea getTextArea() {
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
			add(bold);

			JButton italic = new JButton();
			italic.setToolTipText("Italic Text");
			italic.setIcon(new ImageIcon("images/toolbar_slotview/italic.jpg"));
			add(italic);

			JButton underline = new JButton();
			underline.setToolTipText("Underline Text");
			underline.setIcon(new ImageIcon(
					"images/toolbar_slotview/underline.jpg"));
			add(underline);
		}

	}
}
