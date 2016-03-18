package gerudok.commands;

import gerudok.model.GraphicSlotElement;
import gerudok.model.SlotGraphic;

public class DeleteCommand extends Command {
	private static final long serialVersionUID = -8106512743079627654L;

	private SlotGraphic model;
	private GraphicSlotElement element = null;

	public DeleteCommand(SlotGraphic model, GraphicSlotElement element) {
		this.model = model;
		this.element = element;
	}

	@Override
	public void doCommand() {
		model.removeGraphicSlotElement(element);
	}

	@Override
	public void undoCommand() {
		model.addGraphicSlotElement(element);

	}

}
