package gerudok.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import gerudok.model.Page;
import net.miginfocom.swing.MigLayout;

public class PageView extends JPanel implements FocusListener {
	private static final long serialVersionUID = 7320070701561579492L;

	public static final int PAGE_HEIGHT = 1400;
	public static final int PAGE_WIDTH = 1100;

	Page page;
	String name = null;
	ArrayList<SlotView> slotViews = new ArrayList<SlotView>();

	public PageView(String name) {
		super(new MigLayout());
		this.name = name;
		setPreferredSize(new Dimension(PAGE_WIDTH, PAGE_HEIGHT));
		TitledBorder border = BorderFactory.createTitledBorder(name);
		border.setTitleColor(Color.BLACK);
		setBorder(border);
		addFocusListener(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		TitledBorder border = BorderFactory.createTitledBorder(name);
		border.setTitleColor(Color.BLUE);
		setBorder(border);
	}
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public void addSlotView(SlotView view) {
		slotViews.add(view);
		add(view, "wrap");
		validate();
		// Skroluj na dodati slot.
		// NAPOMENA: nije konzistentna funkcionalnost.
		view.scrollRectToVisible(new Rectangle(view.getLocation()));
	}

	public void removeSlotView(SlotView view) {
		slotViews.remove(view);
		remove(view);
		validate();
	}

	@Override
	public void focusGained(FocusEvent e) {
		TitledBorder border = BorderFactory.createTitledBorder(name);
		border.setTitleColor(Color.BLUE);
		setBorder(border);
	}

	@Override
	public void focusLost(FocusEvent e) {
		TitledBorder border = BorderFactory.createTitledBorder(name);
		border.setTitleColor(Color.BLACK);
		setBorder(border);
	}
}
