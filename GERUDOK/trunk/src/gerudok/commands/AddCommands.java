package gerudok.commands;

import java.util.List;

import gerudok.model.GraphicSlotElement;
import gerudok.model.SlotGraphic;
import gerudok.view.SlotGraphicView;

public class AddCommands extends Command {
	private static final long serialVersionUID = -2281089816895541668L;

	private SlotGraphicView view;
	private List<GraphicSlotElement> elements = null;

	public AddCommands(SlotGraphicView view, List<GraphicSlotElement> elements) {
		this.view = view;
		this.elements = elements;
	}

	@Override
	public void doCommand() {
		for (GraphicSlotElement element : elements) {
			((SlotGraphic)view.getSlot()).addGraphicSlotElement(element);
			view.getSelectionModel().addToSelectionList(element);
		}
	}

	@Override
	public void undoCommand() {
		for (GraphicSlotElement element : elements) {
			((SlotGraphic)view.getSlot()).removeGraphicSlotElement(element);
			view.getSelectionModel().removeFromSelectionList(element);
		}
	}

}
