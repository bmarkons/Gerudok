package gerudok.gui.windowParts;

import gerudok.actions.AboutAction;
import gerudok.actions.CascadeProjectAction;
import gerudok.actions.DeleteDocumentAction;
import gerudok.actions.DeleteNode;
import gerudok.actions.DeletePageAction;
import gerudok.actions.DeleteProjectAction;
import gerudok.actions.DeleteSlot;
import gerudok.actions.DeleteSlotText;
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
import gerudok.actions.UnderConstructionAction;
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
		JMenuItem newProject = new JMenuItem("New project");
		newProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));
		newProject.setMnemonic(KeyEvent.VK_N);
		ImageIcon iconnp = new ImageIcon("images/menu/addproj.png");
		newProject.setIcon(iconnp);
		newProject.addActionListener(new NewProjectAction());

		JMenuItem newDoc = new JMenuItem("New document");
		newDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				ActionEvent.CTRL_MASK));
		newDoc.setMnemonic(KeyEvent.VK_D);
		ImageIcon iconnd = new ImageIcon("images/menu/adddoc.png");
		newDoc.setIcon(iconnd);
		newDoc.addActionListener(new NewDocumentAction());

		JMenuItem newPage = new JMenuItem("New page");
		newPage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.CTRL_MASK));
		newPage.setMnemonic(KeyEvent.VK_P);
		ImageIcon iconnpa = new ImageIcon("images/menu/addpage.png");
		newPage.setIcon(iconnpa);
		newPage.addActionListener(new NewPageAction());

		JMenuItem newGslot = new JMenuItem("New graphic slot");
		newGslot.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
				ActionEvent.CTRL_MASK));
		newGslot.setMnemonic(KeyEvent.VK_G);
		ImageIcon iconngs = new ImageIcon("images/menu/newgslot.png");
		newGslot.setIcon(iconngs);
		newGslot.addActionListener(new NewSlotGraphic());

		JMenuItem newTslot = new JMenuItem("New text slot");
		newTslot.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
				ActionEvent.CTRL_MASK));
		newTslot.setMnemonic(KeyEvent.VK_T);
		ImageIcon iconnts = new ImageIcon("images/menu/newtxt.png");
		newTslot.setIcon(iconnts);
		newTslot.addActionListener(new NewSlotText());

		JMenu newSlot = new JMenu("New Slot");
		ImageIcon iconas = new ImageIcon("images/menu/addslot.png");
		newSlot.setIcon(iconas);
		newSlot.add(newGslot);
		newSlot.add(newTslot);

		file.add(newProject);
		file.add(newDoc);
		file.add(newPage);
		file.addSeparator();
		file.add(newSlot);

		JMenu project = new JMenu("Project");
		project.setMnemonic(KeyEvent.VK_P);
		// dodavanje podmenija i menuitem-a
		JMenuItem openProj = new JMenuItem("Open project");
		ImageIcon iconopenp = new ImageIcon("images/menu/openproj.png");
		openProj.setIcon(iconopenp);
		openProj.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		openProj.addActionListener(new OpenProjectAction());

		JMenuItem save = new JMenuItem("Save");
		ImageIcon iconsave = new ImageIcon("images/menu/save.png");
		save.setIcon(iconsave);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		save.addActionListener(new SaveProjectAction());

		JMenuItem saveas = new JMenuItem("Save As");
		ImageIcon iconsaveas = new ImageIcon("images/menu/save.png");
		saveas.setIcon(iconsaveas);
		saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,
				ActionEvent.CTRL_MASK));
		saveas.addActionListener(new SaveAsProjectAction());

		JMenuItem deleteProject = new JMenuItem("Delete project");
		deleteProject.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.SHIFT_MASK));
		ImageIcon icondp = new ImageIcon("images/menu/delproj.png");
		deleteProject.setIcon(icondp);
		deleteProject.addActionListener(new DeleteProjectAction());

		JMenuItem deleteDoc = new JMenuItem("Delete document");
		deleteDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				ActionEvent.SHIFT_MASK));
		ImageIcon icondd = new ImageIcon("images/menu/deldoc.png");
		deleteDoc.setIcon(icondd);
		deleteDoc.addActionListener(new DeleteDocumentAction());

		JMenuItem deletePage = new JMenuItem("Delete page");
		deletePage.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.SHIFT_MASK));
		ImageIcon icondpa = new ImageIcon("images/menu/delpage.png");
		deletePage.setIcon(icondpa);
		deletePage.addActionListener(new DeletePageAction());

		JMenuItem deleteTslot = new JMenuItem("Delete text slot");
		deleteTslot.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,
				ActionEvent.SHIFT_MASK));
		ImageIcon icondts = new ImageIcon("images/menu/deltxt.png");
		deleteTslot.setIcon(icondts);
		deleteTslot.addActionListener(new DeleteSlotText());

		JMenuItem delSlot = new JMenuItem("Delete Slot");
		delSlot.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.SHIFT_MASK));
		ImageIcon iconds = new ImageIcon("images/menu/delslot.png");
		delSlot.setIcon(iconds);
		delSlot.addActionListener(new DeleteSlot());

		JMenuItem deleteAll = new JMenuItem("Universal Delete");
		deleteAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		ImageIcon icondaa = new ImageIcon("images/menu/deleteAll.png");
		deleteAll.setIcon(icondaa);
		deleteAll.addActionListener(new DeleteNode());

		project.add(openProj);
		project.addSeparator();
		project.add(save);
		project.add(saveas);
		project.addSeparator();
		project.add(deleteProject);
		project.add(deleteDoc);
		project.add(deletePage);
		project.addSeparator();
		project.add(delSlot);
		project.addSeparator();
		project.add(deleteAll);

		JMenu edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);

		JMenuItem undo = new JMenuItem("Undo");
		ImageIcon iconundo = new ImageIcon("images/menu/undo.png");
		undo.setIcon(iconundo);
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				ActionEvent.CTRL_MASK));
		undo.addActionListener(new CommandUndoAction());

		JMenuItem redo = new JMenuItem("Redo");
		ImageIcon iconredo = new ImageIcon("images/menu/redo.png");
		redo.setIcon(iconredo);
		redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
				ActionEvent.CTRL_MASK));
		redo.addActionListener(new CommandRedoAction());

		JMenuItem closeallproj = new JMenuItem("Close all projects");
		ImageIcon iconcap = new ImageIcon("images/menu/delallproj.png");
		closeallproj.setIcon(iconcap);
		closeallproj.addActionListener(new UnderConstructionAction());

		JMenuItem closealldoc = new JMenuItem("Close all documents");
		ImageIcon iconcad = new ImageIcon("images/menu/delalldoc.png");
		closealldoc.setIcon(iconcad);
		closealldoc.addActionListener(new UnderConstructionAction());

		JMenuItem deleteallpages = new JMenuItem("Close all pages");
		ImageIcon icondap = new ImageIcon("images/menu/delallpage.png");
		deleteallpages.setIcon(icondap);
		deleteallpages.addActionListener(new UnderConstructionAction());

		JMenuItem deleteallslots = new JMenuItem("Close all slots");
		ImageIcon icondas = new ImageIcon("images/menu/delallslot.png");
		deleteallslots.setIcon(icondas);
		deleteallslots.addActionListener(new UnderConstructionAction());

		edit.add(undo);
		edit.add(redo);
		edit.addSeparator();
		edit.add(closeallproj);
		edit.add(closealldoc);
		edit.add(deleteallpages);
		edit.add(deleteallslots);

		JMenu window = new JMenu("View");
		window.setMnemonic(KeyEvent.VK_V);
		// dodavanje podmenija i menuitem-a
		JMenuItem cascadeHorizontally = new JMenuItem("Cascade");
		cascadeHorizontally.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_C, ActionEvent.ALT_MASK));
		cascadeHorizontally.setMnemonic(KeyEvent.VK_C);
		ImageIcon iconch = new ImageIcon("images/menu/ch.png");
		cascadeHorizontally.setIcon(iconch);
		cascadeHorizontally.addActionListener(new CascadeProjectAction());
		window.add(cascadeHorizontally);

		JMenuItem tileHorizontally = new JMenuItem("Tile Horizontally");
		tileHorizontally.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
				ActionEvent.ALT_MASK));
		tileHorizontally.setMnemonic(KeyEvent.VK_H);
		ImageIcon iconth = new ImageIcon("images/menu/th.png");
		tileHorizontally.setIcon(iconth);
		tileHorizontally.addActionListener(new TileHorizontallyAction());
		window.add(tileHorizontally);

		JMenuItem tileVertically = new JMenuItem("Tile Vertically");
		tileVertically.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.ALT_MASK));
		tileVertically.setMnemonic(KeyEvent.VK_V);
		ImageIcon icontv = new ImageIcon("images/menu/tv.png");
		tileVertically.setIcon(icontv);
		tileVertically.addActionListener(new TileVerticallyAction());
		window.add(tileVertically);

		JMenuItem gridlayout = new JMenuItem("Grid Layout");
		gridlayout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
				ActionEvent.ALT_MASK));
		gridlayout.setMnemonic(KeyEvent.VK_G);
		ImageIcon icongrid = new ImageIcon("images/menu/grid.png");
		gridlayout.setIcon(icongrid);
		gridlayout.addActionListener(new GridProjectAction());
		window.add(gridlayout);

		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		// dodavanje podmenija i menuitem-a
		JMenuItem about = new JMenuItem("About GeRuDok");
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				ActionEvent.ALT_MASK));
		about.setMnemonic(KeyEvent.VK_A);
		ImageIcon iconabout = new ImageIcon("images/menu/about.png");
		about.setIcon(iconabout);
		about.addActionListener(new AboutAction());
		help.add(about);

		add(file);
		add(project);
		add(edit);
		add(window);
		add(help);
	}
}