package gerudok.commands;

import gerudok.model.GraphicSlotElement;
import gerudok.model.SlotGraphic;

public class AddCommand extends Command {
	private static final long serialVersionUID = -2281089816895541668L;
	
	SlotGraphic model;
	GraphicSlotElement element = null;

	public AddCommand(SlotGraphic model, GraphicSlotElement element) {
		this.model = model;
		this.element = element;
	}

	@Override
	public void doCommand() {
		model.addGraphicSlotElement(element);
	}

	@Override
	public void undoCommand() {
		model.removeGraphicSlotElement(element);
	}

}