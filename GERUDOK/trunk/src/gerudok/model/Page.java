package gerudok.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import gerudok.view.PageView;

public class Page extends Observable implements MutableTreeNode, Serializable, Observer {
	private static final long serialVersionUID = -8832891846966542402L;

	Document parent = null;
	String name = null;
	ArrayList<Slot> slots = new ArrayList<Slot>();
	String slotsNum = "";
	transient PageView pageView = null;

	public Page(Document parent) {
		super();
		this.parent = parent;
		addObserver(parent);
	}

	public ArrayList<Slot> getSlots() {
		return this.slots;
	}

	public Object readResolve() {
		pageView = new PageView(this.name);
		addObserver(parent);
		return this;
	}

	public PageView getPageView() {
		return pageView;
	}

	public void setPageView(PageView pageView) {
		this.pageView = pageView;
	}

	public void addSlot(Slot slot) {
		slots.add(slot);
		if (slot.getName() == null)
			slot.setName("slot - " + slots.size());
		slotsNum = "(" + slots.size() + ")";
		// dogodila se modifikacija stranice
		setChanged();
		notifyObservers();
	}

	public void deleteSlot(Slot slot) {
		slots.remove(slot);
		slotsNum = "(" + slots.size() + ")";
		// dogodila se modifikacija stranice
		setChanged();
		notifyObservers();
	}

	public void setName(String name) {
		this.name = name;
		if (pageView != null)
			pageView.setName(name);
		// dogodila se modifikacija projekta
		setChanged();
		notifyObservers();
	}

	public String getName() {
		return this.name;
	}

	public String toString() {
		return name + slotsNum;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Enumeration<Slot> children() {
		return (Enumeration<Slot>) slots;
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return slots.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return slots.size();
	}

	@Override
	public int getIndex(TreeNode node) {
		return slots.indexOf(node);
	}

	@Override
	public TreeNode getParent() {
		return parent;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public void update(Observable o, Object arg) {
		// Prosledjuje obavestenje o promeni ka parent-u
		setChanged();
		notifyObservers();
	}

	public void notifyObservers(Object arg) {
		setChanged();
		super.notifyObservers(arg);
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
