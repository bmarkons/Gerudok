package gerudok.gui.segments;

import gerudok.gui.MainFrameGerudok;

import java.awt.Color;

import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class ToolBarGerudok extends JToolBar {

	public ToolBarGerudok() {
		super(JToolBar.HORIZONTAL);
		setBackground(Color.decode("#eeeeee"));
		setFloatable(false);

		add(MainFrameGerudok.getInstance().getActionManager().getNewproject());

		add(MainFrameGerudok.getInstance().getActionManager().getNewdocument());

		add(MainFrameGerudok.getInstance().getActionManager().getNewpage());

		add(MainFrameGerudok.getInstance().getActionManager().getNewgraphicslot());
		
		add(MainFrameGerudok.getInstance().getActionManager().getNewtextslot());
		
		add(MainFrameGerudok.getInstance().getActionManager().getDeletenode());
		
		addSeparator();

		add(MainFrameGerudok.getInstance().getActionManager().getOpenproject());

		add(MainFrameGerudok.getInstance().getActionManager().getSave());
		
		add(MainFrameGerudok.getInstance().getActionManager().getSaveas());

		addSeparator();

		add(MainFrameGerudok.getInstance().getActionManager().getCascade());

		add(MainFrameGerudok.getInstance().getActionManager().getTilehorizontally());

		add(MainFrameGerudok.getInstance().getActionManager().getTilevertically());

		add(MainFrameGerudok.getInstance().getActionManager().getGridaction());

		addSeparator();

		add(MainFrameGerudok.getInstance().getActionManager().getAbout());
		
	}
}
