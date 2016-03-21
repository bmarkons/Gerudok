package gerudok.gui.segments;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gerudok.actions.manager.ActionManager;
import gerudok.gui.MainFrameGerudok;

@SuppressWarnings("serial")
public class MenuBarGerudok extends JMenuBar {
	
	private JMenu file;
	private JMenu newSlot;
	private JMenu project;
	private JMenu edit;
	private JMenu view;
	private JMenu language;
	private JMenu lookandfeel;
	private JMenu help;
	
	private ResourceBundle rb = MainFrameGerudok.getInstance().getResourceBundle();
	
	public MenuBarGerudok() {
		file = new JMenu(rb.getString("File"));
		file.setMnemonic(KeyEvent.VK_F);

		// dodavanje podmenija i menuitem-a

		newSlot = new JMenu(rb.getString("NewSlot"));
		ImageIcon iconas = new ImageIcon("images/menu/addslot.png");
		newSlot.setIcon(iconas);
		
		newSlot.add(ActionManager.getInstance().getNewgraphicslot());
		newSlot.add(ActionManager.getInstance().getNewtextslot());
		
		file.add(ActionManager.getInstance().getOpenproject());
		file.addSeparator();
		
		file.add(ActionManager.getInstance().getSave());
		file.add(ActionManager.getInstance().getSaveas());
		file.addSeparator();
		
		file.add(ActionManager.getInstance().getNewproject());
		file.add(ActionManager.getInstance().getNewdocument());
		file.add(ActionManager.getInstance().getNewpage());
		file.addSeparator();
		file.add(newSlot);
		
		file.addSeparator();
		file.add(ActionManager.getInstance().getQuitaction());

		//project = new JMenu(rb.getString("Project"));
		//project.setMnemonic(KeyEvent.VK_P);
		
		// dodavanje podmenija i menu item-a

		edit = new JMenu(rb.getString("Edit"));
		edit.setMnemonic(KeyEvent.VK_E);

		edit.add(ActionManager.getInstance().getUndo());
		edit.add(ActionManager.getInstance().getRedo());
		edit.addSeparator();
		edit.add(ActionManager.getInstance().getDeletenode());
		
		
		view = new JMenu(rb.getString("View"));
		view.setMnemonic(KeyEvent.VK_V);
		// dodavanje podmenija i menuitem-a
		
		view.add(ActionManager.getInstance().getCascade());

		view.add(ActionManager.getInstance().getTilehorizontally());

		view.add(ActionManager.getInstance().getTilevertically());

		view.add(ActionManager.getInstance().getGridaction());
		
		view.addSeparator();
		
		language = new JMenu(rb.getString("Language"));
		
		JCheckBoxMenuItem en = new JCheckBoxMenuItem("English");
		en.setSelected(true);
		en.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("en","US"));
				MainFrameGerudok.getInstance().changeLanguage();
			}
		});
		JCheckBoxMenuItem sr = new JCheckBoxMenuItem("Srpski");
		sr.addActionListener(new ActionListener() {
			
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrameGerudok.getInstance().getLocale().setDefault(new Locale("sr","RS"));
				MainFrameGerudok.getInstance().changeLanguage();
			}
		});
		JCheckBoxMenuItem sr_cyr = new JCheckBoxMenuItem("Српски");
		sr_cyr.addActionListener(new ActionListener() {
			
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrameGerudok.getInstance().getLocale().setDefault(new Locale("sr","RS","Cyrl"));
				MainFrameGerudok.getInstance().changeLanguage();
			}
		});
		JCheckBoxMenuItem hu = new JCheckBoxMenuItem("Magyar");
		hu.addActionListener(new ActionListener() {
			
			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrameGerudok.getInstance().getLocale().setDefault(new Locale("hu","HU"));
				MainFrameGerudok.getInstance().changeLanguage();
			}
		});
		
		ButtonGroup group = new ButtonGroup();
		group.add(en);
		group.add(sr);
		group.add(sr_cyr);
		group.add(hu);
		
		language.add(en);
		language.add(sr);
		language.add(sr_cyr);
		language.add(hu);
		
		view.add(language);
		
		
		lookandfeel = new JMenu(rb.getString("Theme"));
		JCheckBoxMenuItem alu = new JCheckBoxMenuItem(rb.getString("Modern"));
		alu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel");
					JFrame frame = MainFrameGerudok.getInstance();
                    SwingUtilities.updateComponentTreeUI(frame);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		JCheckBoxMenuItem sys = new JCheckBoxMenuItem(rb.getString("System"));
		sys.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					JFrame frame = MainFrameGerudok.getInstance();
                    SwingUtilities.updateComponentTreeUI(frame);
                    
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		JCheckBoxMenuItem cp = new JCheckBoxMenuItem(rb.getString("Classic"));
		cp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
					JFrame frame = MainFrameGerudok.getInstance();
                    SwingUtilities.updateComponentTreeUI(frame);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		JCheckBoxMenuItem nimbus = new JCheckBoxMenuItem(rb.getString("Nimbus"));
		nimbus.setSelected(true);
		nimbus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					try {
						UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					}
					JFrame frame = MainFrameGerudok.getInstance();
                    SwingUtilities.updateComponentTreeUI(frame);
				
			}
		});
		
		ButtonGroup group1 = new ButtonGroup();
		group1.add(alu);
		group1.add(sys);
		group1.add(cp);
		group1.add(nimbus);
		
		lookandfeel.add(alu);
		lookandfeel.add(sys);
		lookandfeel.add(cp);
		lookandfeel.add(nimbus);
		
		view.add(lookandfeel);
		
		help = new JMenu(rb.getString("Help"));
		help.setMnemonic(KeyEvent.VK_H);
		// dodavanje podmenija i menuitem-a
		help.add(ActionManager.getInstance().getAbout());

		add(file);
		//add(project);
		add(edit);
		add(view);
		add(help);
	}
	
	public void changeLanguage(){
		rb = MainFrameGerudok.getInstance().getResourceBundle();
		
		file.setText(rb.getString("File"));
		newSlot.setText(rb.getString("NewSlot"));;
		//project.setText(rb.getString("Project"));;
		edit.setText(rb.getString("Edit"));;
		view.setText(rb.getString("View"));;
		language.setText(rb.getString("Language"));
		lookandfeel.setText(rb.getString("Theme"));
		lookandfeel.getItem(0).setText(rb.getString("Modern"));
		lookandfeel.getItem(1).setText(rb.getString("System"));
		lookandfeel.getItem(2).setText(rb.getString("Classic"));
		lookandfeel.getItem(3).setText(rb.getString("Nimbus"));
		help.setText(rb.getString("Help"));;
	}
}