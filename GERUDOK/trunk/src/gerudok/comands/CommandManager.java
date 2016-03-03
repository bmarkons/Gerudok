package gerudok.comands;

import gerudok.actions.CommandRedoAction;
import gerudok.actions.CommandUndoAction;

import java.io.Serializable;
import java.util.ArrayList;

public class CommandManager implements Serializable {
	private static final long serialVersionUID = 520246163128615091L;
	
	private ArrayList<Command> commands = new ArrayList<Command>();
	private int currentCommand = 0;
	private CommandUndoAction mUndo = new CommandUndoAction();
	private CommandRedoAction mRedo = new CommandRedoAction();

	public CommandUndoAction getUndo() {
		return this.mUndo;
	}

	public CommandRedoAction getRedo() {
		return this.mRedo;
	}

	public void addCommand(Command command) {
		while (currentCommand < commands.size())
			commands.remove(currentCommand);
		commands.add(command);
		doCommand();
	}

	public void doCommand() {
		if (currentCommand < commands.size()) {
			commands.get(currentCommand++).doCommand();
			getUndo().setEnabled(true);
		}
		if (currentCommand == commands.size()) {
			getRedo().setEnabled(false);
		}
	}

	public void undoCommand() {
		if (currentCommand > 0) {
			getRedo().setEnabled(true);
			commands.get(--currentCommand).undoCommand();
		}
		if (currentCommand == 0) {
			getUndo().setEnabled(false);
		}
	}
}
