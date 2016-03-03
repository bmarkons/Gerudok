package gerudok.actions;

import gerudok.gui.MainFrameGerudok;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;

public class CascadeProjectAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		MainFrameGerudok.getInstance().getDesktopPane().setLayout(null);
		JInternalFrame[] frames = MainFrameGerudok.getInstance().getDesktopPane().getAllFrames();
		for (int i = 0; i < frames.length; i++) {
			JInternalFrame frame = frames[i];
			frame.setSize(new Dimension(400, 300));
			frame.setLocation(40 * i, 40 * i);
			try {
				frame.setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		}
	}

}
