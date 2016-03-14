package gerudok.gui.windowParts;

import gerudok.actions.AboutAction;
import gerudok.actions.CascadeProjectAction;
import gerudok.actions.DeleteDocumentAction;
import gerudok.actions.DeletePageAction;
import gerudok.actions.DeleteProjectAction;
import gerudok.actions.DeleteSlot;
import gerudok.actions.NewDocumentAction;
import gerudok.actions.NewPageAction;
import gerudok.actions.NewProjectAction;
import gerudok.actions.NewSlotGraphic;
import gerudok.actions.NewSlotText;
import gerudok.actions.OpenProjectAction;
import gerudok.actions.SaveAsProjectAction;
import gerudok.actions.GridProjectAction;
import gerudok.actions.TileHorizontallyAction;
import gerudok.actions.TileVerticallyAction;

import java.awt.Color;

import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class ToolBarGerudok extends JToolBar {

	public ToolBarGerudok() {
		super(JToolBar.HORIZONTAL);
		setBackground(Color.decode("#eeeeee"));
		setFloatable(false);

		add(new NewProjectAction());

		add(new NewDocumentAction());

		add(new NewPageAction());

		add(new NewSlotGraphic());
		
		add(new NewSlotText());

		addSeparator();

		add(new OpenProjectAction());

		add(new SaveAsProjectAction());

		addSeparator();

		add(new DeleteProjectAction());

		add(new DeleteDocumentAction());

		add(new DeletePageAction());

		add(new DeleteSlot());

		addSeparator();

		add(new CascadeProjectAction());

		add(new TileHorizontallyAction());

		add(new TileVerticallyAction());

		add(new GridProjectAction());

		addSeparator();

		//JButton aboutBtn = new JButton();
		//aboutBtn.setToolTipText("About GeRuDok (ALT+A)");
		//aboutBtn.setIcon(new ImageIcon("images/toolbar/about.png"));
		//aboutBtn.addActionListener(new AboutAction());
		//add(aboutBtn);
		add(new AboutAction());
		
	}
}
