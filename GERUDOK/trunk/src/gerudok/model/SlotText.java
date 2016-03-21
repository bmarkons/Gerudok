package gerudok.model;

import javax.swing.tree.MutableTreeNode;


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

	public Object readResolve() {

		//((SlotTextView) slotView).getTextArea().setText(this.text);
		addObserver((Page)getParent());
		return this;
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(MutableTreeNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUserObject(Object object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFromParent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		// TODO Auto-generated method stub
		
	}

}
