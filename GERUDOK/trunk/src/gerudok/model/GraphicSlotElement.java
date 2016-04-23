package gerudok.model;

import java.awt.Dimension;
import java.awt.Point;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Observable;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import gerudok.view.painters.ElementPainter;

public class GraphicSlotElement extends Observable implements MutableTreeNode, Serializable {
	private static final long serialVersionUID = 3610303136965099422L;

	private SlotGraphic parent = null;
	private String name = null;
	private Point position;
	private Dimension size;
	private ElementPainter painter = null;

	public GraphicSlotElement(SlotGraphic parent, Point position) {
		this.parent = parent;
		this.position = position;
	}
	
	public String toString() {
		return this.name;
	}
	
	public void setSize(Dimension size) {
		this.size = size;
	}
	
	public Dimension getSize() {
		return size;
	}
	
	public void setPainter(ElementPainter painter) {
		this.painter = painter;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point getPosition() {
		return this.position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public ElementPainter getPainter() {
		return this.painter;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children() {
		return null;
	}

	@Override
	public boolean getAllowsChildren() {
		return false;
	}

	@Override
	public TreeNode getChildAt(int arg0) {
		return null;
	}

	@Override
	public int getChildCount() {
		return 0;
	}

	@Override
	public int getIndex(TreeNode arg0) {
		return 0;
	}

	@Override
	public TreeNode getParent() {
		return this.parent;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		
		
	}

	@Override
	public void remove(int index) {
		
		
	}

	@Override
	public void remove(MutableTreeNode node) {
		
		
	}

	@Override
	public void removeFromParent() {
		
		this.parent=null;
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		
		this.parent=((SlotGraphic)newParent);
	}

	@Override
	public void setUserObject(Object object) {
		
		if ((object instanceof String))
	    {
	      this.name = ((String)object);
	      this.parent.notifyObservers("update_project");
	    }
	}
}
