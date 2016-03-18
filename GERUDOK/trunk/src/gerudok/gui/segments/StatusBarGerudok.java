package gerudok.gui.segments;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class StatusBarGerudok extends JPanel {
	private static final long serialVersionUID = -9168377466538098360L;

	public StatusBarGerudok() {
		super(new BorderLayout());

		//setBackground(Color.decode("#bbbbbb"));
		setPreferredSize(new Dimension(200, 20));

		JLabel status1 = new JLabel("Your application is running. ");
		JLabel status2 = new JLabel("Language: English", SwingConstants.CENTER);
		JLabel status3 = new JLabel("Ready");

		JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);

		add(sep, BorderLayout.SOUTH);
		add(status1, BorderLayout.EAST);
		add(status2, BorderLayout.CENTER);
		add(status3, BorderLayout.WEST);
		add(sep, BorderLayout.NORTH);
	}

}
