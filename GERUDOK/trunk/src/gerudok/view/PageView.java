package gerudok.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import gerudok.events.PageEvent;
import gerudok.events.PageEvent.PageEventType;
import gerudok.gui.MainFrameGerudok;
import gerudok.model.Page;
import gerudok.model.SlotGraphic;
import gerudok.model.SlotText;
import net.miginfocom.swing.MigLayout;

public class PageView extends JPanel implements FocusListener, Observer {
	private static final long serialVersionUID = 7320070701561579492L;

	public static final int PAGE_HEIGHT = 1400;
	public static final int PAGE_WIDTH = 1100;

	Page page;
	String name = null;
	ArrayList<SlotView> slotViews = new ArrayList<SlotView>();

	public PageView(Page page) {
		super(new MigLayout());
		this.page = page;
		this.name = page.getName();
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

	// public void setPage(Page page) {
	// this.page = page;
	// }

	public void addSlotView(SlotView view) {
		slotViews.add(view);
		add(view, "wrap");
		validate();
		// Skroluj na dodati slot.
		// NAPOMENA: nije konzistentna funkcionalnost.
		// view.scrollRectToVisible(new Rectangle(view.getLocation()));
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

	@Override
	public void update(Observable o, Object arg) {
		if(arg == null)
			return;
		
		PageEvent eventObject = (PageEvent) arg;
		
		if(eventObject.getType() == PageEventType.ADD_SLOT){
			
			SlotView slotView = null;
			if(eventObject.getSlot() instanceof SlotGraphic)
				slotView = new SlotGraphicView(eventObject.getSlot());
			else if(eventObject.getSlot() instanceof SlotText)
				slotView = new SlotTextView(eventObject.getSlot());
			addSlotView(slotView);
			eventObject.getSlot().addObserver(slotView);
			
		}else if(eventObject.getType() == PageEventType.REMOVE_SLOT){
			
			for(SlotView view : slotViews){
				if(eventObject.getSlot().equals(view.getSlot()))
					removeSlotView(view);
			}
			
		}else if(eventObject.getType() == PageEventType.RENAME_PAGE){
			
			setName(eventObject.getSlot().getName());
			
		}
		
		SwingUtilities.updateComponentTreeUI(MainFrameGerudok.getInstance().getTree());
	}
}
