package gerudok.actions;

import gerudok.model.SlotText;
import gerudok.view.SlotTextView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class SaveTextAction implements ActionListener {

	private SlotText slot = null;
	// private TextSlotView slotView = null;

	public SaveTextAction(SlotText slot) {
		this.slot = slot;
		// this.slotView = (TextSlotView) slot.getSlotView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextArea textArea = ((SlotTextView) slot.getSlotView()).getTextArea();
		String txt = textArea.getText();
		slot.setText(txt);
		JOptionPane.showMessageDialog(textArea, "Text saved!", slot.getName(), JOptionPane.INFORMATION_MESSAGE);
	}
}
