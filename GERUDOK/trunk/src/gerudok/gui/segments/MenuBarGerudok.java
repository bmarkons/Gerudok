package gerudok.gui.segments;

import gerudok.actions.AboutAction;
import gerudok.actions.CascadeProjectAction;
import gerudok.actions.DeleteNode;
import gerudok.actions.GridProjectAction;
import gerudok.actions.NewDocumentAction;
import gerudok.actions.NewPageAction;
import gerudok.actions.NewProjectAction;
import gerudok.actions.NewSlotGraphic;
import gerudok.actions.NewSlotText;
import gerudok.actions.OpenProjectAction;
import gerudok.actions.CommandRedoAction;
import gerudok.actions.SaveAsProjectAction;
import gerudok.actions.SaveProjectAction;
import gerudok.actions.TileHorizontallyAction;
import gerudok.actions.TileVerticallyAction;
import gerudok.gui.MainFrameGerudok;
import gerudok.actions.CommandUndoAction;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class MenuBarGerudok extends JMenuBar {

	public MenuBarGerudok() {
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);

		// dodavanje podmenija i menuitem-a

		JMenu newSlot = new JMenu("New Slot");
		ImageIcon iconas = new ImageIcon("images/menu/addslot.png");
		newSlot.setIcon(iconas);
		
		newSlot.add(MainFrameGerudok.getInstance().getActionManager().getNewgraphicslot());
		newSlot.add(MainFrameGerudok.getInstance().getActionManager().getNewtextslot());

		file.add(MainFrameGerudok.getInstance().getActionManager().getNewproject());
		file.add(MainFrameGerudok.getInstance().getActionManager().getNewdocument());
		file.add(MainFrameGerudok.getInstance().getActionManager().getNewpage());
		file.addSeparator();
		file.add(newSlot);

		JMenu project = new JMenu("Project");
		project.setMnemonic(KeyEvent.VK_P);
		// dodavanje podmenija i menu item-a

		project.add(MainFrameGerudok.getInstance().getActionManager().getOpenproject());
		project.addSeparator();
		project.add(MainFrameGerudok.getInstance().getActionManager().getSave());
		project.add(MainFrameGerudok.getInstance().getActionManager().getSaveas());
		project.addSeparator();
		project.addSeparator();
		project.addSeparator();
		project.add(MainFrameGerudok.getInstance().getActionManager().getDeletenode());

		JMenu edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);

		edit.add(MainFrameGerudok.getInstance().getActionManager().getUndo());
		edit.add(MainFrameGerudok.getInstance().getActionManager().getRedo());

		JMenu window = new JMenu("View");
		window.setMnemonic(KeyEvent.VK_V);
		// dodavanje podmenija i menuitem-a
		
		window.add(MainFrameGerudok.getInstance().getActionManager().getCascade());

		window.add(MainFrameGerudok.getInstance().getActionManager().getTilehorizontally());

		window.add(MainFrameGerudok.getInstance().getActionManager().getTilevertically());

		window.add(MainFrameGerudok.getInstance().getActionManager().getGridaction());

		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		// dodavanje podmenija i menuitem-a
		help.add(new AboutAction());

		add(file);
		add(project);
		add(edit);
		add(window);
		add(help);
	}
}