package gerudok.gui.windowParts;

import gerudok.actions.AboutAction;
import gerudok.actions.CascadeProjectAction;
import gerudok.actions.DeleteDocumentAction;
import gerudok.actions.DeleteNode;
import gerudok.actions.DeletePageAction;
import gerudok.actions.DeleteProjectAction;
import gerudok.actions.DeleteSlot;
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
		
		newSlot.add(new NewSlotGraphic());
		newSlot.add(new NewSlotText());

		file.add(new NewProjectAction());
		file.add(new NewDocumentAction());
		file.add(new NewPageAction());
		file.addSeparator();
		file.add(newSlot);

		JMenu project = new JMenu("Project");
		project.setMnemonic(KeyEvent.VK_P);
		// dodavanje podmenija i menu item-a

		project.add(new OpenProjectAction());
		project.addSeparator();
		project.add(new SaveProjectAction());
		project.add(new SaveAsProjectAction());
		project.addSeparator();
		project.add(new DeleteProjectAction());
		project.add(new DeleteDocumentAction());
		project.add(new DeletePageAction());
		project.addSeparator();
		project.add(new DeleteSlot());
		project.addSeparator();
		project.add(new DeleteNode());

		JMenu edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);

		//JMenuItem undo = new JMenuItem("Undo");
		//ImageIcon iconundo = new ImageIcon("images/menu/undo.png");
		//undo.setIcon(iconundo);
		//undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
		//		ActionEvent.CTRL_MASK));
		//undo.addActionListener(new CommandUndoAction());

		JMenuItem redo = new JMenuItem("Redo");
		ImageIcon iconredo = new ImageIcon("images/menu/redo.png");
		redo.setIcon(iconredo);
		redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
				ActionEvent.CTRL_MASK));
		redo.addActionListener(new CommandRedoAction());

		edit.add(new CommandUndoAction());
		edit.add(new CommandRedoAction());

		JMenu window = new JMenu("View");
		window.setMnemonic(KeyEvent.VK_V);
		// dodavanje podmenija i menuitem-a
		
		window.add(new CascadeProjectAction());

		window.add(new TileHorizontallyAction());

		window.add(new TileVerticallyAction());

		window.add(new GridProjectAction());

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