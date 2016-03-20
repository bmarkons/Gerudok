package gerudok.gui.segments;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import gerudok.gui.MainFrameGerudok;

public class StatusBarGerudok extends JPanel {
	private static final long serialVersionUID = -9168377466538098360L;
	
	JLabel status1;
	JLabel status2;
	JLabel status3;
	
	private ResourceBundle rb = MainFrameGerudok.getInstance().getResourceBundle();
	
	public StatusBarGerudok() {
		super(new BorderLayout());

		//setBackground(Color.decode("#bbbbbb"));
		setPreferredSize(new Dimension(200, 20));

		status1 = new JLabel(rb.getString("SBMessage1"));
		status2 = new JLabel(rb.getString("SBMessage2"), SwingConstants.CENTER);
		status3 = new JLabel(rb.getString("SBMessage3"));

		JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);

		add(sep, BorderLayout.SOUTH);
		add(status1, BorderLayout.EAST);
		add(status2, BorderLayout.CENTER);
		add(status3, BorderLayout.WEST);
		add(sep, BorderLayout.NORTH);
	}
	
	public void changeLanguage() {
		rb = MainFrameGerudok.getInstance().getResourceBundle();
		
		status1.setText(rb.getString("SBMessage1"));
		status2.setText(rb.getString("SBMessage2"));
		status3.setText(rb.getString("SBMessage3"));
	}
	
}
