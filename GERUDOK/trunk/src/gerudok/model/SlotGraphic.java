package gerudok.model;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.SwingUtilities;
import javax.swing.tree.TreeNode;

import gerudok.gui.MainFrameGerudok;
import gerudok.view.SlotGraphicView;

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

		element.setName(element.getName() + (graphicElements.size() + 1));
		graphicElements.add(element);

		// Dogodila se promena u slotu - obavestiti stranicu koja ga sadrzi
		slotChanged();

		//Osvezavanje prikaza stabla i ponovno iscrtavanje odgovarajuceg slotView-a
		SwingUtilities.updateComponentTreeUI(MainFrameGerudok.getInstance().getTree());
		this.slotView.repaint();

	}

	public void removeGraphicSlotElement(GraphicSlotElement element) {
		
		graphicElements.remove(element);
		
		// Dogodila se promena u slotu - obavestiti stranicu koja ga sadrzi
		slotChanged();
		
		//Osvezavanje prikaza stabla i ponovno iscrtavanje odgovarajuceg slotView-a
		SwingUtilities.updateComponentTreeUI(MainFrameGerudok.getInstance().getTree());
		this.slotView.repaint();
	}

	public Object readResolve() {
		slotView = new SlotGraphicView(this);
		addObserver((Page)getParent());
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
		return false;
	}
}
