package gerudok.actions.manager;

import java.util.ResourceBundle;

import gerudok.actions.AboutAction;
import gerudok.actions.CascadeProjectAction;
import gerudok.actions.CommandRedoAction;
import gerudok.actions.CommandUndoAction;
import gerudok.actions.CopyAction;
import gerudok.actions.CutAction;
import gerudok.actions.DeleteDialogAction;
import gerudok.actions.DeleteNode;
import gerudok.actions.DisplayProject;
import gerudok.actions.GridProjectAction;
import gerudok.actions.ImportDocumentAction;
import gerudok.actions.NewDocumentAction;
import gerudok.actions.NewPageAction;
import gerudok.actions.NewProjectAction;
import gerudok.actions.NewSlotGraphic;
import gerudok.actions.NewSlotText;
import gerudok.actions.OpenProjectAction;
import gerudok.actions.PasteAction;
import gerudok.actions.QuitAction;
import gerudok.actions.RenameNode;
import gerudok.actions.SaveAsProjectAction;
import gerudok.actions.SaveProjectAction;
import gerudok.actions.TileHorizontallyAction;
import gerudok.actions.TileVerticallyAction;
import gerudok.gui.MainFrameGerudok;

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
	private QuitAction quit;

	private CopyAction copy;
	private CutAction cut;
	private PasteAction paste;

	private ImportDocumentAction importDoc;
	
	private DeleteDialogAction deleteDialog;

	private static ActionManager instance = null;

	private ActionManager() {
		about = new AboutAction(AbstractActionIcon.small);
		cascade = new CascadeProjectAction(AbstractActionIcon.small);
		redo = new CommandRedoAction(AbstractActionIcon.small);
		undo = new CommandUndoAction(AbstractActionIcon.small);
		deletenode = new DeleteNode(AbstractActionIcon.small);
		displayproject = new DisplayProject();
		gridaction = new GridProjectAction(AbstractActionIcon.small);
		newdocument = new NewDocumentAction(AbstractActionIcon.small);
		newpage = new NewPageAction(AbstractActionIcon.small);
		newproject = new NewProjectAction(AbstractActionIcon.small);
		newgraphicslot = new NewSlotGraphic(AbstractActionIcon.small);
		newtextslot = new NewSlotText(AbstractActionIcon.small);
		openproject = new OpenProjectAction(AbstractActionIcon.small);
		renamenode = new RenameNode();
		saveas = new SaveAsProjectAction(AbstractActionIcon.small);
		save = new SaveProjectAction(AbstractActionIcon.small);
		tilehorizontally = new TileHorizontallyAction(AbstractActionIcon.small);
		tilevertically = new TileVerticallyAction(AbstractActionIcon.small);
		quit = new QuitAction(AbstractActionIcon.small);

		copy = new CopyAction(AbstractActionIcon.small);
		cut = new CutAction(AbstractActionIcon.small);
		paste = new PasteAction(AbstractActionIcon.small);

		importDoc = new ImportDocumentAction(AbstractActionIcon.small);
		
		deleteDialog = new DeleteDialogAction(AbstractActionIcon.small);
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

	public QuitAction getQuitaction() {
		return quit;
	}

	public CopyAction getCopyaction() {
		return copy;
	}

	public CutAction getCutaction() {
		return cut;
	}

	public PasteAction getPasteaction() {
		return paste;
	}

	public ImportDocumentAction getImportAction() {
		return importDoc;
	}
	
	public DeleteDialogAction getDeleteDialog() {
		return deleteDialog;
	}

	public void changeLanguage() {
		ResourceBundle rb = MainFrameGerudok.getInstance().getResourceBundle();

		about.putValue(AbstractActionIcon.NAME, rb.getString("About"));
		about.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("AboutH"));

		cascade.putValue(AbstractActionIcon.NAME, rb.getString("Cascade"));
		cascade.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("CascadeH"));

		redo.putValue(AbstractActionIcon.NAME, rb.getString("Redo"));
		redo.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("RedoH"));

		undo.putValue(AbstractActionIcon.NAME, rb.getString("Undo"));
		undo.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("UndoH"));

		deletenode.putValue(AbstractActionIcon.NAME, rb.getString("Delete"));
		deletenode.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("DeleteH"));

		gridaction.putValue(AbstractActionIcon.NAME, rb.getString("Grid"));
		gridaction.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("GridH"));

		newdocument.putValue(AbstractActionIcon.NAME, rb.getString("NewDocument"));
		newdocument.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("NewDocumentH"));

		newpage.putValue(AbstractActionIcon.NAME, rb.getString("NewPage"));
		newpage.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("NewPageH"));

		newproject.putValue(AbstractActionIcon.NAME, rb.getString("NewProject"));
		newproject.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("NewProjectH"));

		newgraphicslot.putValue(AbstractActionIcon.NAME, rb.getString("NewGSlot"));
		newgraphicslot.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("NewGSlotH"));

		newtextslot.putValue(AbstractActionIcon.NAME, rb.getString("NewTSlot"));
		newtextslot.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("NewTSlotH"));

		openproject.putValue(AbstractActionIcon.NAME, rb.getString("Open"));
		openproject.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("OpenH"));

		saveas.putValue(AbstractActionIcon.NAME, rb.getString("SaveAs"));
		saveas.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("SaveAsH"));

		save.putValue(AbstractActionIcon.NAME, rb.getString("Save"));
		save.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("SaveH"));

		tilehorizontally.putValue(AbstractActionIcon.NAME, rb.getString("TH"));
		tilehorizontally.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("THH"));

		tilevertically.putValue(AbstractActionIcon.NAME, rb.getString("TV"));
		tilevertically.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("TVH"));

		quit.putValue(AbstractActionIcon.NAME, rb.getString("Quit"));
		quit.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("QuitH"));

		copy.putValue(AbstractActionIcon.NAME, rb.getString("Copy"));
		copy.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("CopyH"));

		cut.putValue(AbstractActionIcon.NAME, rb.getString("Cut"));
		cut.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("CutH"));

		paste.putValue(AbstractActionIcon.NAME, rb.getString("Paste"));
		paste.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("PasteH"));

		importDoc.putValue(AbstractActionIcon.NAME, rb.getString("Import"));
		importDoc.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("ImportH"));
		
		deleteDialog.putValue(AbstractActionIcon.NAME, rb.getString("Delete"));
		deleteDialog.putValue(AbstractActionIcon.SHORT_DESCRIPTION, rb.getString("DeleteH"));
	}

	public static ActionManager getInstance() {
		if (instance == null)
			instance = new ActionManager();

		return instance;
	}
}
