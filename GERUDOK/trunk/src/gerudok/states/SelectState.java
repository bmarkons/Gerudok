package gerudok.states;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import gerudok.model.GraphicSlotElement;
import gerudok.model.SlotGraphic;
import gerudok.view.SlotGraphicView;
import gerudok.view.SlotGraphicView.Handle;
import gerudok.view.SlotView;

public class SelectState extends State{

	SlotView sv;
	private int elementInMotion = -1;
	private Handle handleInMotion = null;
	
	public SelectState(SlotView view) {
		super(view);
		sv = view;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		SlotGraphicView sgv;
		
		if(sv instanceof SlotGraphicView)
			sgv = (SlotGraphicView)sv;
		else
			return;
		
		if(!e.isControlDown()){
			sgv.getSelectionModel().removeAllFromSelectionList();
			}
			
			Point position = e.getPoint();
			if (e.getButton()==MouseEvent.BUTTON1){
				//provera se da li je korisnik uhvatio neki od hand-lova
				//trebace nam kad krenemo raditi resize elemenata
				
				handleInMotion = sgv.getDeviceAndHandleForPoint(position);
				if(handleInMotion == null){
					elementInMotion = ((SlotGraphic)sgv.getSlot()).getElementAtPosition(position);
					if(elementInMotion != -1){
						//pogodjen je element, ukoliko je selektovan treba ga ukloniti iz liste,
						//ako nije treba ga dodati u listu
						GraphicSlotElement element=(GraphicSlotElement) sgv.getSlot().getChildAt(elementInMotion);
						
						if (sgv.getSelectionModel().isElementSelected(element)){
							sgv.getSelectionModel().removeFromSelectionList(element);
						}else{
							sgv.getSelectionModel().addToSelectionList(element);
						}	
						
						sv.repaint();
						
					}else{
						//nije pogodjen nijedan element
						sv.repaint();
					}
				}	
			}
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		SlotGraphicView sgv;

		if (sv instanceof SlotGraphicView)
			sgv = (SlotGraphicView) sv;
		else
			return;
		
		if(sgv.getSelectionModel().getSelectionList().isEmpty())
			sgv.getStateManager().setLassoSelectState();
		else
			sgv.getStateManager().setMoveState();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point2D point = e.getPoint();
		((SlotGraphicView)sv).setMouseCursor(point);
	}

}
