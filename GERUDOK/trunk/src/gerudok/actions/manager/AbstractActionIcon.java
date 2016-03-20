package gerudok.actions.manager;

import java.awt.Dimension;
import java.awt.Image;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import gerudok.gui.MainFrameGerudok;

@SuppressWarnings("serial")
public abstract class AbstractActionIcon extends AbstractAction{
	
	private static final String PATH = "images/";
	protected static ResourceBundle rb = MainFrameGerudok.getInstance().getResourceBundle();
	
	protected Icon iconGetter(String iconPath) {
		final Dimension size = new Dimension(24, 24);

		Icon i = new ImageIcon(new ImageIcon(PATH+iconPath).getImage()
				.getScaledInstance(size.height, size.width, Image.SCALE_SMOOTH));
		return i;
	}
	
}
