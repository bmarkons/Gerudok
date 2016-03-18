package gerudok.actions;

public class ActionManager {

	private static ActionManager instance = null;

	private AboutAction about;
	private CascadeProjectAction cascade;
	private CommandRedoAction redo;
	private CommandUndoAction undo;
	private DeleteDocumentAction deletedoc;
	private DeleteNode deletenode;
	private DeletePageAction deletepage;
	private DeleteProjectAction deleteproject;
	private DeleteSlot deleteslot;
	private DeleteSlotGraphic deletegraphic;
	private DeleteSlotText deletetext;
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

	private ActionManager() {
		about = new AboutAction();
		cascade = new CascadeProjectAction();
		redo = new CommandRedoAction();
		undo = new CommandUndoAction();
		deletedoc = new DeleteDocumentAction();
		deletenode = new DeleteNode();
		deletepage = new DeletePageAction();
		deleteproject = new DeleteProjectAction();
		deleteslot = new DeleteSlot();
		deletegraphic = new DeleteSlotGraphic();
		deletetext = new DeleteSlotText();
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

	public DeleteDocumentAction getDeletedoc() {
		return deletedoc;
	}

	public DeleteNode getDeletenode() {
		return deletenode;
	}

	public DeletePageAction getDeletepage() {
		return deletepage;
	}

	public DeleteProjectAction getDeleteproject() {
		return deleteproject;
	}

	public DeleteSlot getDeleteslot() {
		return deleteslot;
	}

	public DeleteSlotGraphic getDeletegraphic() {
		return deletegraphic;
	}

	public DeleteSlotText getDeletetext() {
		return deletetext;
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
		if (instance == null)
			instance = new ActionManager();
		return instance;
	}

}
