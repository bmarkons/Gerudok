package gerudok.actions;

import gerudok.model.SlotText;
import gerudok.view.SlotTextView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;

public class SaveTextAction implements ActionListener {

	private SlotTextView slotView = null;

	public SaveTextAction(SlotTextView slotView) {
		this.slotView = slotView;
		// this.slotView = (TextSlotView) slot.getSlotView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JEditorPane textArea = this.slotView.getTextArea();
		String txt = textArea.getText();
		((SlotText)slotView.getSlot()).setText(txt);
		JOptionPane.showMessageDialog(textArea, "Text saved!", slotView.getName(), JOptionPane.INFORMATION_MESSAGE);
	}
}
