package gerudok.view;

import gerudok.model.Document;
import gerudok.model.Page;
import gerudok.model.Project;
import gerudok.model.Slot;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;

public class TreeEditor extends DefaultTreeCellEditor implements
		ActionListener, TreeCellEditor {

	private Object stavka = null;
	private JTextField edit = null;

	public TreeEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
		super(arg0, arg1);
	}

	public Component getTreeCellEditorComponent(JTree arg0, Object arg1,
			boolean arg2, boolean arg3, boolean arg4, int arg5) {

		// super.getTreeCellEditorComponent(arg0,arg1,arg2,arg3,arg4,arg5);
		stavka = arg1;

		edit = new JTextField(arg1.toString());
		edit.addActionListener(this);
		return edit;
	}

	public boolean isCellEditable(EventObject arg0) {
		if (arg0 instanceof MouseEvent) {
			if (((MouseEvent) arg0).getClickCount() == 3) {
				return true;
			}
			return false;
		}
		return true;
	}

	public void actionPerformed(ActionEvent e) {
		String newName = e.getActionCommand();

		if (stavka instanceof Project) {
			Project project = (Project) stavka;

			project.setName(newName);

			project.getProjectView().setTitle(newName);
		}
		if (stavka instanceof Document) {
			Document document = (Document) stavka;

			document.setName(newName);

			DocumentView view = document.getDocumentView();
			view.setName(newName);
			JTabbedPane tabbedPane = (JTabbedPane) view.getParent();
			tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), newName);
		}
		if (stavka instanceof Page) {
			Page page = (Page) stavka;

			page.setName(newName);

			PageView view = page.getPageView();
			view.setName(newName);
		}
		if (stavka instanceof Slot) {
			Slot slot = (Slot) stavka;

			slot.setName(newName);

			SlotView view = slot.getSlotView();
			view.setName(newName);
		}

		// Otkomentarisati ovaj deo koda na kraju, i obavezno testirati
		try {
			tree.stopEditing();
		} catch (Exception e1) {
		}
	}
}