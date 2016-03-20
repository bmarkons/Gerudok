package gerudok.main;

import javax.swing.UIManager;

import gerudok.gui.MainFrameGerudok;

public class GerudokMain {

	public static void main(String[] args) {
		try {
			//UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel");
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}

		MainFrameGerudok mainFrame = MainFrameGerudok.getInstance();
		mainFrame.setVisible(true);
		
	}

}
