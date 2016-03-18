package gerudok.gui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.tree.DefaultTreeCellRenderer;

import gerudok.actions.ActionManager;
import gerudok.controller.JTreeControllerGerudok;
import gerudok.gui.windowParts.JTreeGerudok;
import gerudok.gui.windowParts.MenuBarGerudok;
import gerudok.gui.windowParts.StatusBarGerudok;
import gerudok.gui.windowParts.ToolBarGerudok;
import gerudok.model.JTreeModelGerudok;
import gerudok.view.TreeCellRendered;
import gerudok.view.TreeEditor;

public class MainFrameGerudok extends JFrame {
	private static final long serialVersionUID = 2022795997717084907L;

	private static MainFrameGerudok instance = null;
	
	private ActionManager manager = null;
	private JTreeGerudok tree = null;
	private JDesktopPane desktopPane = null;
	private MenuBarGerudok menuBar = null;
	private StatusBarGerudok statusBar = null;
	private ToolBarGerudok toolBar = null;

	private MainFrameGerudok() {
		setTitle("GeRuDok T1.1");
		
		// instanciranje ActionManager-a
		manager = ActionManager.getInstance();
		
		// podesavanje velicine i pozicije prozora
		Toolkit kit = Toolkit.getDefaultToolkit();
		int height = kit.getScreenSize().height;
		int width = kit.getScreenSize().width;
		setSize((int) (width * 0.6), (int) (height * 0.8));
		setLocationRelativeTo(null);

		// Postavljanje ikone
		Image img = kit.getImage("images/logo.png");
		setIconImage(img);

		// dodavanje komponenti prozora

		// MENUBAR
		menuBar = new MenuBarGerudok();
		setJMenuBar(menuBar);

		// TOOLBAR
		toolBar = new ToolBarGerudok();
		add(toolBar, BorderLayout.NORTH);

		// SPLIT PANE (JTREE + desktopPane)
		tree = initTree();
		JScrollPane sp = new JScrollPane(tree);
		desktopPane = new JDesktopPane();
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sp,
				desktopPane);
		add(splitPane, BorderLayout.CENTER);

		// STATUSBAR
		statusBar = new StatusBarGerudok();
		add(statusBar, BorderLayout.SOUTH);

		// Lansiranje :)
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// nuzno pozivanje setVisible zbog
		// mogucnosti izracunavanja lokacije divider-a u splitPane-u
		setVisible(true);
		splitPane.setDividerLocation(0.2);
		//this.mCommandManager = new CommandManager();
	}

	// Inicijalizacija JTreeGerudok
	private JTreeGerudok initTree() {
		JTreeGerudok tree = new JTreeGerudok();
		tree.setModel(new JTreeModelGerudok());
		tree.addTreeSelectionListener(new JTreeControllerGerudok());
		tree.setCellEditor(new TreeEditor(tree, new DefaultTreeCellRenderer()));
		tree.setCellRenderer(new TreeCellRendered());
		tree.setEditable(true);

		return tree;
	}

	public static MainFrameGerudok getInstance() {
		if (instance == null)
			instance = new MainFrameGerudok();
		return instance;
	}

	public JTreeGerudok getTree() {
		return tree;
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public MenuBarGerudok getMenu() {
		return menuBar;
	}

	public StatusBarGerudok getStatusBar() {
		return statusBar;
	}

	public ToolBarGerudok getToolBar() {
		return toolBar;
	}
	
	public ActionManager getActionManager() {
		return manager;
	}
}
