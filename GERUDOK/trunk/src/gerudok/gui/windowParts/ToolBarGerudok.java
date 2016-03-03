package gerudok.gui.windowParts;

import gerudok.actions.AboutAction;
import gerudok.actions.CascadeProjectAction;
import gerudok.actions.DeleteDocumentAction;
import gerudok.actions.DeletePageAction;
import gerudok.actions.DeleteProjectAction;
import gerudok.actions.DeleteSlot;
import gerudok.actions.NewDocumentAction;
import gerudok.actions.NewPageAction;
import gerudok.actions.NewProjectAction;
import gerudok.actions.NewSlotGraphic;
import gerudok.actions.NewSlotText;
import gerudok.actions.OpenProjectAction;
import gerudok.actions.SaveAsProjectAction;
import gerudok.actions.GridProjectAction;
import gerudok.actions.TileHorizontallyAction;
import gerudok.actions.TileVerticallyAction;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class ToolBarGerudok extends JToolBar {

	public ToolBarGerudok() {
		super(JToolBar.HORIZONTAL);
		setBackground(Color.decode("#eeeeee"));
		setFloatable(false);

		JButton newProjBtn = new JButton();
		newProjBtn.setToolTipText("New project (CTRL+N)");
		newProjBtn.setIcon(new ImageIcon("images/toolbar/addproj.png"));
		newProjBtn.addActionListener(new NewProjectAction());
		add(newProjBtn);

		JButton newDocBtn = new JButton();
		newDocBtn.setToolTipText("New document (CTRL+D)");
		newDocBtn.setIcon(new ImageIcon("images/toolbar/adddoc.png"));
		newDocBtn.addActionListener(new NewDocumentAction());
		add(newDocBtn);

		JButton newPageBtn = new JButton();
		newPageBtn.setToolTipText("New page (CTRL+P)");
		newPageBtn.setIcon(new ImageIcon("images/toolbar/addpage.png"));
		newPageBtn.addActionListener(new NewPageAction());
		add(newPageBtn);

		JButton newGSBtn = new JButton();
		newGSBtn.setToolTipText("New graphic slot (CTRL+G)");
		newGSBtn.setIcon(new ImageIcon("images/toolbar/newgslot.png"));
		newGSBtn.addActionListener(new NewSlotGraphic());
		add(newGSBtn);

		JButton newTSBtn = new JButton();
		newTSBtn.setToolTipText("New text slot (CTRL+T)");
		newTSBtn.setIcon(new ImageIcon("images/toolbar/newtxt.png"));
		newTSBtn.addActionListener(new NewSlotText());
		add(newTSBtn);

		addSeparator();

		JButton openproj = new JButton();
		openproj.setToolTipText("Open project (CTRL+O)");
		openproj.setIcon(new ImageIcon("images/toolbar/openproj.png"));
		openproj.addActionListener(new OpenProjectAction());
		add(openproj);

		JButton saveas = new JButton();
		saveas.setToolTipText("Save As (CTRL+F1)");
		saveas.setIcon(new ImageIcon("images/toolbar/save.png"));
		saveas.addActionListener(new SaveAsProjectAction());
		add(saveas);

		addSeparator();

		JButton delProjBtn = new JButton();
		delProjBtn.setToolTipText("Delete project (Shift+P)");
		delProjBtn.setIcon(new ImageIcon("images/toolbar/delproj.png"));
		delProjBtn.addActionListener(new DeleteProjectAction());
		add(delProjBtn);

		JButton delDocBtn = new JButton();
		delDocBtn.setToolTipText("Delete document (Shift+D)");
		delDocBtn.setIcon(new ImageIcon("images/toolbar/deldoc.png"));
		delDocBtn.addActionListener(new DeleteDocumentAction());
		add(delDocBtn);

		JButton delPageBtn = new JButton();
		delPageBtn.setToolTipText("Delete page (Shift+P)");
		delPageBtn.setIcon(new ImageIcon("images/toolbar/delpage.png"));
		delPageBtn.addActionListener(new DeletePageAction());
		add(delPageBtn);

		JButton delSlot = new JButton();
		delSlot.setToolTipText("Delete slot");
		delSlot.setIcon(new ImageIcon("images/toolbar/delslot.png"));
		delSlot.addActionListener(new DeleteSlot());
		add(delSlot);

		addSeparator();

		JButton newCHBtn = new JButton();
		newCHBtn.setToolTipText("Cascade horizontally (ALT+C)");
		newCHBtn.setIcon(new ImageIcon("images/toolbar/ch.png"));
		newCHBtn.addActionListener(new CascadeProjectAction());
		add(newCHBtn);

		JButton newTHBtn = new JButton();
		newTHBtn.setToolTipText("Tile horizontally (ALT+H)");
		newTHBtn.setIcon(new ImageIcon("images/toolbar/th.png"));
		newTHBtn.addActionListener(new TileHorizontallyAction());
		add(newTHBtn);

		JButton newTVBtn = new JButton();
		newTVBtn.setToolTipText("Tile vertically (ALT+V)");
		newTVBtn.setIcon(new ImageIcon("images/toolbar/tv.png"));
		newTVBtn.addActionListener(new TileVerticallyAction());
		add(newTVBtn);

		JButton newGridBtn = new JButton();
		newGridBtn.setToolTipText("Grid layout (ALT+G)");
		newGridBtn.setIcon(new ImageIcon("images/toolbar/grid.png"));
		newGridBtn.addActionListener(new GridProjectAction());
		add(newGridBtn);

		addSeparator();

		JButton aboutBtn = new JButton();
		aboutBtn.setToolTipText("About GeRuDok (ALT+A)");
		aboutBtn.setIcon(new ImageIcon("images/toolbar/about.png"));
		aboutBtn.addActionListener(new AboutAction());
		add(aboutBtn);

	}
}
