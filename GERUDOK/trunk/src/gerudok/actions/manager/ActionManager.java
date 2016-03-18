package gerudok.actions.manager;

import gerudok.actions.AboutAction;
import gerudok.actions.CascadeProjectAction;
import gerudok.actions.CommandRedoAction;
import gerudok.actions.CommandUndoAction;
import gerudok.actions.DeleteNode;
import gerudok.actions.DisplayProject;
import gerudok.actions.GridProjectAction;
import gerudok.actions.NewDocumentAction;
import gerudok.actions.NewPageAction;
import gerudok.actions.NewProjectAction;
import gerudok.actions.NewSlotGraphic;
import gerudok.actions.NewSlotText;
import gerudok.actions.OpenProjectAction;
import gerudok.actions.RenameNode;
import gerudok.actions.SaveAsProjectAction;
import gerudok.actions.SaveProjectAction;
import gerudok.actions.TileHorizontallyAction;
import gerudok.actions.TileVerticallyAction;

public class ActionManager {

	private AboutAction about;
	private CascadeProjectAction cascade;
	private CommandRedoAction redo;
	private CommandUndoAction undo;
	private DeleteNode deletenode;
	private DisplayProject displayproject;
	private GridProjectAction gridaction;
	private NewDocumentAction newdocument;
	private NewPageAction newpage;
	private NewProjectAction newproject;
	private NewSlotGraphic newgraphicslot;
	private NewSlotText newtextslot;
	private OpenProjectAction openproject;
	private RenameNode renamenode;
	private SaveAsProjectAction saveas;
	private SaveProjectAction save;
	private TileHorizontallyAction tilehorizontally;
	private TileVerticallyAction tilevertically;
	
	private static ActionManager instance = null;
	
	private ActionManager() {
		about = new AboutAction();
		cascade = new CascadeProjectAction();
		redo = new CommandRedoAction();
		undo = new CommandUndoAction();
		deletenode = new DeleteNode();
		displayproject = new DisplayProject();
		gridaction = new GridProjectAction();
		newdocument = new NewDocumentAction();
		newpage = new NewPageAction();
		newproject = new NewProjectAction();
		newgraphicslot = new NewSlotGraphic();
		newtextslot = new NewSlotText();
		openproject = new OpenProjectAction();
		renamenode = new RenameNode();
		saveas = new SaveAsProjectAction();
		save = new SaveProjectAction();
		tilehorizontally = new TileHorizontallyAction();
		tilevertically = new TileVerticallyAction();
	}

	public AboutAction getAbout() {
		return about;
	}

	public CascadeProjectAction getCascade() {
		return cascade;
	}

	public CommandRedoAction getRedo() {
		return redo;
	}

	public CommandUndoAction getUndo() {
		return undo;
	}

	public DeleteNode getDeletenode() {
		return deletenode;
	}

	public DisplayProject getDisplayproject() {
		return displayproject;
	}

	public GridProjectAction getGridaction() {
		return gridaction;
	}

	public NewDocumentAction getNewdocument() {
		return newdocument;
	}

	public NewPageAction getNewpage() {
		return newpage;
	}

	public NewProjectAction getNewproject() {
		return newproject;
	}

	public NewSlotGraphic getNewgraphicslot() {
		return newgraphicslot;
	}

	public NewSlotText getNewtextslot() {
		return newtextslot;
	}

	public OpenProjectAction getOpenproject() {
		return openproject;
	}

	public RenameNode getRenamenode() {
		return renamenode;
	}

	public SaveAsProjectAction getSaveas() {
		return saveas;
	}

	public SaveProjectAction getSave() {
		return save;
	}

	public TileHorizontallyAction getTilehorizontally() {
		return tilehorizontally;
	}

	public TileVerticallyAction getTilevertically() {
		return tilevertically;
	}
	
	public static ActionManager getInstance() {
		if(instance == null)
			instance = new ActionManager();
		
		return instance;
	}
}
