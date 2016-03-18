package gerudok.view;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Scrollable;

import net.miginfocom.swing.MigLayout;

public class DocumentPanel extends JPanel implements Scrollable{
	private static final long serialVersionUID = 7634404415396395048L;

	private ArrayList<PageView> pageViews = new ArrayList<PageView>();

	public DocumentPanel() {
		super();
		setLayout(new MigLayout());
	}

	public ArrayList<PageView> getPageViews() {
		return pageViews;
	}

	//Metode interfejsa Scrollable za podesavanje brzine skrolovanja
	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return null;
	}

	//skrol na strelice
	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		return 50;
	}

	//skrol tockicem
	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
		return 50;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

}
