package gerudok.model;

import gerudok.view.SlotView;

public class SlotText extends Slot {
	private static final long serialVersionUID = 3641546233772099557L;

	private String text = null;
	
	public SlotText(Page parent) {
		super(parent);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		slotChanged();
	}
	
	public Object readResolve(){
		slotView = new SlotView(this);
		slotView.getTextArea().setText(this.text);
		addObserver(parent);
		return this;
	}

}
