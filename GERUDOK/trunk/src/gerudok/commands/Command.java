package gerudok.commands;

import java.io.Serializable;

public abstract class Command implements Serializable{
	private static final long serialVersionUID = -6666360637424259870L;

	public abstract void doCommand();

	public abstract void undoCommand();
}
