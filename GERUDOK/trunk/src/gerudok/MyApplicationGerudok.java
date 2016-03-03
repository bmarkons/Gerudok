package gerudok;

import javax.swing.UIManager;

import gerudok.gui.MainFrameGerudok;

public class MyApplicationGerudok {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel");
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		MainFrameGerudok mainFrame = MainFrameGerudok.getInstance();
		mainFrame.setVisible(true);
	}

}
