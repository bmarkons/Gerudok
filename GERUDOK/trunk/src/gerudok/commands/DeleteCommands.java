package gerudok.commands;

import java.util.List;

import gerudok.model.GraphicSlotElement;
import gerudok.model.SlotGraphic;
import gerudok.view.SlotGraphicView;

public class DeleteCommands extends Command {
	private static final long serialVersionUID = -8106512743079627654L;

	private SlotGraphicView view;
	private List<GraphicSlotElement> elements = null;

	public DeleteCommands(SlotGraphicView view, List<GraphicSlotElement> elements) {
		this.view = view;
		this.elements = elements;
	}

	@Override
	public void doCommand() {
		for (GraphicSlotElement element : elements) {
			((SlotGraphic)view.getSlot()).removeGraphicSlotElement(element);
			view.getSelectionModel().removeFromSelectionList(element);
		}
	}

	@Override
	public void undoCommand() {
		for (GraphicSlotElement element : elements) {
			((SlotGraphic)view.getSlot()).addGraphicSlotElement(element);
			view.getSelectionModel().addToSelectionList(element);
		}
	}
	
}
