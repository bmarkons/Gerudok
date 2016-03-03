package gerudok.model;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.SwingUtilities;
import javax.swing.tree.TreeNode;

import gerudok.gui.MainFrameGerudok;
import gerudok.view.SlotView;

public class SlotGraphic extends Slot {
	private static final long serialVersionUID = -5018847687408131521L;

	ArrayList<GraphicSlotElement> elements = new ArrayList<GraphicSlotElement>();
	
	public SlotGraphic(Page parent) {
		super(parent);
	}
	
	public ArrayList<GraphicSlotElement> getElements(){
		return elements;
	}
	
	public void addGraphicSlotElement(GraphicSlotElement element){
		element.setName(element.getName() + (elements.size()+1));
		elements.add(element);
		slotChanged();
		SwingUtilities.updateComponentTreeUI(MainFrameGerudok.getInstance().getTree());
		this.slotView.repaint();
	}
	
	public void removeElement(GraphicSlotElement element) {
		elements.remove(element);
		slotChanged();
		SwingUtilities.updateComponentTreeUI(MainFrameGerudok.getInstance().getTree());
		this.slotView.repaint();
	}
	
	public Object readResolve(){
		slotView = new SlotView(this);
		addObserver(parent);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Enumeration<GraphicSlotElement> children() {
		return (Enumeration<GraphicSlotElement>) elements;
	}
	
	@Override
	public boolean getAllowsChildren() {
		return true;
	}
	
	@Override
	public TreeNode getChildAt(int childIndex) {
		return elements.get(childIndex);
	}
	
	@Override
	public int getChildCount() {
		return elements.size();
	}
	
	@Override
	public int getIndex(TreeNode node) {
		return elements.indexOf(node);
	}
	
	@Override
	public boolean isLeaf() {
		return false;
	}

	
}
