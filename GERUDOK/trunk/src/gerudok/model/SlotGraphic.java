package gerudok.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;


public class SlotGraphic extends Slot {
	private static final long serialVersionUID = -5018847687408131521L;

	private ArrayList<GraphicSlotElement> graphicElements = new ArrayList<GraphicSlotElement>();

	public SlotGraphic(Page parent) {
		super(parent);
	}

	public ArrayList<GraphicSlotElement> getElements() {
		return graphicElements;
	}

	public void addGraphicSlotElement(GraphicSlotElement element) {
		graphicElements.add(element);
		if (element.getName() == null) {
			element.setName(element.getName() + graphicElements.size());
		}
		
		setChanged();
		// Dogodila se promena u slotu - obavestiti stranicu koja ga sadrzi
		notifyObservers();

		// Osvezavanje prikaza stabla i ponovno iscrtavanje odgovarajuceg
		// slotView-a
		// SwingUtilities.updateComponentTreeUI(MainFrameGerudok.getInstance().getTree());
		// this.slotView.repaint();
	}

	public void removeGraphicSlotElement(GraphicSlotElement element) {

		graphicElements.remove(element);
		
		setChanged();
		// Dogodila se promena u slotu - obavestiti stranicu koja ga sadrzi
		notifyObservers();

		// Osvezavanje prikaza stabla i ponovno iscrtavanje odgovarajuceg
		// slotView-a
		// SwingUtilities.updateComponentTreeUI(MainFrameGerudok.getInstance().getTree());
		// this.slotView.repaint();
	}

	public Object readResolve() {
		// slotView = new SlotGraphicView(this);
		addObserver((Page) getParent());
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Enumeration<GraphicSlotElement> children() {
		return (Enumeration<GraphicSlotElement>) graphicElements;
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return graphicElements.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return graphicElements.size();
	}

	@Override
	public int getIndex(TreeNode node) {
		return graphicElements.indexOf(node);
	}

	@Override
	public boolean isLeaf() {
		return graphicElements.size() == 0;
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
	
	public int getElementAtPosition(Point point) {
		for(int i=graphicElements.size()-1;i>=0;i--){
			GraphicSlotElement element = graphicElements.get(i);
			if(element.getPainter().getShape().contains(point)){
				return i;
			}
		}
		return -1;
	}	
	
}
