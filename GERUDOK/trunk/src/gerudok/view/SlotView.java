package gerudok.view;

import java.awt.BorderLayout;

import java.awt.Dimension;

import javax.swing.JPanel;

import gerudok.model.Slot;

public abstract class SlotView extends JPanel {
	private static final long serialVersionUID = 4449718288145521263L;

	Slot slot;
	String name = null;

	public SlotView(Slot slot) {
		super(new BorderLayout());
		this.slot = slot;
		name = slot.getName();

		setPreferredSize(new Dimension(PageView.PAGE_WIDTH - 5,
				PageView.PAGE_HEIGHT / 3));
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
}
