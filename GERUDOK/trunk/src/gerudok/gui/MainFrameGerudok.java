package gerudok.gui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import gerudok.actions.manager.ActionManager;
import gerudok.gui.segments.MenuBarGerudok;
import gerudok.gui.segments.StatusBarGerudok;
import gerudok.gui.segments.ToolBarGerudok;
import gerudok.model.Workspace;
import gerudok.tree.listener.JTreeControllerGerudok;
import gerudok.tree.view.TreeCellRendered;
import gerudok.tree.view.TreeEditor;
import gerudok.view.WorkspaceView;
import gerudok.tree.view.TreePopUp;

public class MainFrameGerudok extends JFrame implements ClipboardOwner{
	private static final long serialVersionUID = 2022795997717084907L;

	private static MainFrameGerudok instance = null;
	
	private JTree tree = null;
	private WorkspaceView workspaceView = null;
	private MenuBarGerudok menuBar = null;
	private StatusBarGerudok statusBar = null;
	private ToolBarGerudok toolBar = null;

	private ResourceBundle rb = null;
	
	private MainFrameGerudok() {
		super();
	}
	
	private void initMainFrameGerudok() {
		
		rb = ResourceBundle.getBundle("gerudok.locale.gerudok", Locale.getDefault());

		setTitle(rb.getString("Title"));

		// podesavanje velicine i pozicije prozora
		Toolkit kit = Toolkit.getDefaultToolkit();
		int height = kit.getScreenSize().height;
		int width = kit.getScreenSize().width;
		setSize((int) (width * 0.6), (int) (height * 0.8));
		setLocationRelativeTo(null);
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				MainFrameGerudok frame = (MainFrameGerudok) e.getComponent();
 				int check = JOptionPane.showConfirmDialog(frame, rb.getString("Closing"),
 						rb.getString("CloseTitle"), JOptionPane.YES_NO_OPTION);
				if (check != JOptionPane.YES_OPTION) {
					frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				} else {
					frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				}
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

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

		// SPLIT PANE (WorkspaceView + JTREE)
		workspaceView = new WorkspaceView();
		workspaceView.setBackground(getBackground());
		tree = initTree(workspaceView);
		JScrollPane sp = new JScrollPane(tree);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sp, workspaceView);
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
		// this.mCommandManager = new CommandManager();
	}

	// Inicijalizacija JTreeGerudok
	private JTree initTree(WorkspaceView workspaceView) {
		Workspace root = new Workspace();
		root.addObserver(workspaceView);
		JTree tree = new JTree(root);
		tree.setModel(new DefaultTreeModel(root));	/*****/
		tree.addTreeSelectionListener(new JTreeControllerGerudok());
		tree.setCellEditor(new TreeEditor(tree, new DefaultTreeCellRenderer()));
		tree.setCellRenderer(new TreeCellRendered());
		tree.setComponentPopupMenu(new TreePopUp());
		tree.setEditable(true);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		return tree;
	}

	public static MainFrameGerudok getInstance() {
		if (instance == null)
		{
			instance = new MainFrameGerudok();
			instance.initMainFrameGerudok();
		}

		return instance;
	}

	public JTree getTree() {
		return tree;
	}

	public ResourceBundle getResourceBundle() {
		return rb;
	}
	
	public WorkspaceView getWorkspaceView() {
		return workspaceView;
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
	
	public void changeLanguage() {
		rb = ResourceBundle.getBundle("gerudok.locale.gerudok", Locale.getDefault());
		setTitle(rb.getString("Title"));
		ActionManager.getInstance().changeLanguage();
		menuBar.changeLanguage();
		statusBar.changeLanguage();
	}

	@Override
	public void lostOwnership(Clipboard arg0, Transferable arg1) {
		// TODO Auto-generated method stub
		
	}
	
	private Clipboard clipboard=new Clipboard("Main clipboard");
	
	public Clipboard getClipboard() {
		return clipboard;
	}
}
