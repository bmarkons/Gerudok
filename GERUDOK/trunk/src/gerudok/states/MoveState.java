package gerudok.states;

import java.awt.Point;
import java.awt.event.MouseEvent;

import gerudok.model.GraphicSlotElement;
import gerudok.model.SlotGraphic;
import gerudok.view.SlotGraphicView;
import gerudok.view.SlotView;

public class MoveState extends State{
	
	private SlotView sv;
	private Point start;
	
	public MoveState(SlotView view) {
		super(view);
		sv = view;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		SlotGraphicView sgv;

		if (sv instanceof SlotGraphicView)
			sgv = (SlotGraphicView) sv;
		else
			return;
		
		Point position = e.getPoint();
		int elementIndex = ((SlotGraphic) sgv.getSlot()).getElementAtPosition(position);
		if (elementIndex == -1) {
			sgv.getStateManager().setSelectState();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		SlotGraphicView sgv;

		if (sv instanceof SlotGraphicView)
			sgv = (SlotGraphicView) sv;
		else
			return;
		
		int elementSelected = ((SlotGraphic)sgv.getSlot()).getElementAtPosition(e.getPoint());
		
		if(elementSelected!=-1){
			start = ((GraphicSlotElement) sgv.getSlot().getChildAt(elementSelected)).getPosition();
		}

		for(GraphicSlotElement element : sgv.getSelectionModel().getSelected()) {
			
			element.getPosition().translate((int)(e.getX()-start.getX()),(int)(e.getY()-start.getY()));
	
		}
		sgv.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		SlotGraphicView sgv;

		if (sv instanceof SlotGraphicView)
			sgv = (SlotGraphicView) sv;
		else
			return;
		
		sgv.getStateManager().setSelectState();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
