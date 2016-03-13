package gerudok.actions;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public abstract class AbstractActionIcon extends AbstractAction{
	
	private static final String PATH = "images/";

	protected Icon iconGetter(String iconPath) {
		final Dimension size = new Dimension(24, 24);

		Icon i = new ImageIcon(new ImageIcon(PATH+iconPath).getImage()
				.getScaledInstance(size.height, size.width, Image.SCALE_SMOOTH));
		return i;
	}
	
}
