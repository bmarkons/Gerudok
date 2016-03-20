package gerudok.gui.segments;

import gerudok.actions.manager.ActionManager;

import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class ToolBarGerudok extends JToolBar {

	public ToolBarGerudok() {
		super(JToolBar.HORIZONTAL);
		//setBackground(Color.decode("#eeeeee"));
		setFloatable(false);
		
		add(ActionManager.getInstance().getNewproject());

		add(ActionManager.getInstance().getNewdocument());

		add(ActionManager.getInstance().getNewpage());

		add(ActionManager.getInstance().getNewgraphicslot());
		
		add(ActionManager.getInstance().getNewtextslot());
		
		add(ActionManager.getInstance().getDeletenode());
		
		addSeparator();

		add(ActionManager.getInstance().getOpenproject());

		add(ActionManager.getInstance().getSave());
		
		add(ActionManager.getInstance().getSaveas());

		addSeparator();

		add(ActionManager.getInstance().getCascade());

		add(ActionManager.getInstance().getTilehorizontally());

		add(ActionManager.getInstance().getTilevertically());

		add(ActionManager.getInstance().getGridaction());

		addSeparator();

		add(ActionManager.getInstance().getAbout());
		
	}
}
