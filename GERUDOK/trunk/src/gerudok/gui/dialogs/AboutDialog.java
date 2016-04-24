package gerudok.gui.dialogs;

import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

import gerudok.gui.MainFrameGerudok;

@SuppressWarnings("serial")
public class AboutDialog extends JDialog {

	public AboutDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		
		JLabel background1 = new JLabel(new ImageIcon(MainFrameGerudok.getInstance().getResourceBundle().getString("AboutImage")));
		setResizable(false);
		add(background1);
		
		setSize(770, 470);
		setLocationRelativeTo(parent);
	}
}
