package gerudok.gui.segments;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import gerudok.actions.manager.ActionManager;

@SuppressWarnings("serial")
public class MenuBarGerudok extends JMenuBar {

	public MenuBarGerudok() {
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);

		// dodavanje podmenija i menuitem-a

		JMenu newSlot = new JMenu("New Slot");
		ImageIcon iconas = new ImageIcon("images/menu/addslot.png");
		newSlot.setIcon(iconas);
		
		newSlot.add(ActionManager.getInstance().getNewgraphicslot());
		newSlot.add(ActionManager.getInstance().getNewtextslot());

		file.add(ActionManager.getInstance().getNewproject());
		file.add(ActionManager.getInstance().getNewdocument());
		file.add(ActionManager.getInstance().getNewpage());
		file.addSeparator();
		file.add(newSlot);

		JMenu project = new JMenu("Project");
		project.setMnemonic(KeyEvent.VK_P);
		// dodavanje podmenija i menu item-a

		project.add(ActionManager.getInstance().getOpenproject());
		project.addSeparator();
		project.add(ActionManager.getInstance().getSave());
		project.add(ActionManager.getInstance().getSaveas());
		project.addSeparator();
		project.addSeparator();
		project.addSeparator();
		project.add(ActionManager.getInstance().getDeletenode());

		JMenu edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);

		edit.add(ActionManager.getInstance().getUndo());
		edit.add(ActionManager.getInstance().getRedo());

		JMenu window = new JMenu("View");
		window.setMnemonic(KeyEvent.VK_V);
		// dodavanje podmenija i menuitem-a
		
		window.add(ActionManager.getInstance().getCascade());

		window.add(ActionManager.getInstance().getTilehorizontally());

		window.add(ActionManager.getInstance().getTilevertically());

		window.add(ActionManager.getInstance().getGridaction());

		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		// dodavanje podmenija i menuitem-a
		help.add(ActionManager.getInstance().getAbout());

		add(file);
		add(project);
		add(edit);
		add(window);
		add(help);
	}
}