package ncl.b1037041.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.*;

import ncl.b1037041.gui.file.UploadBPMNPanel;
import ncl.b1037041.gui.help.AboutFrame;
import ncl.b1037041.gui.ltl.LTLFormulaPanel;
import ncl.b1037041.gui.ltl.LTLStatisticsPanel;
import ncl.b1037041.gui.tools.GenerationPanel;
import ncl.b1037041.gui.tools.SetupPanel;
import ncl.b1037041.gui.tools.VerificationPanel;

import java.awt.event.ActionEvent;

public class BPMN2PROMELAWindow {

	private static final JFrame mainFrame = new JFrame();
	private final JMenuBar menuBar = new JMenuBar();
	private final MainPanel mainPanel = new MainPanel(this);
	private static final JMenuItem m_file_upload = new JMenuItem("Upload ChoreFile");
	private static final JMenuItem m_ltl_formula = new JMenuItem("Add/Del LTLs");
	private static final JMenuItem m_ltl_statistics = new JMenuItem("Statistics");
	private static final JMenuItem m_tools_generation = new JMenuItem("ProModel Generation");
	private static final JMenuItem m_tools_setup = new JMenuItem("LTL Vars Instantiation");
	private static final JMenuItem m_tools_verification = new JMenuItem("ProModel Verification");
	
	private final Action action_exit = new ExitAction();
	private final Action action_upload = new UploadAction();
	private final Action action_LTL_formula = new FormulaAction();
	private final Action action_LTL_statistics = new StatisticsAction();
	private final Action action_tools_generation = new GenerationAction();
	private final Action action_tools_setup_properties = new SetupAction();
	private final Action action_tools_verification = new VerificationAction();
	private final Action action_help_about = new AboutAction();

	public BPMN2PROMELAWindow() {
		System.out.println("Launch!");
		initialize(-1);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize(int login) {
		if(login == -1) {
			mainFrame.setResizable(false);
			mainFrame.setTitle("BPMN2PROMELA Verifier");
			// Put it at the center of screen
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			int screenWidth = screenSize.width;    
			int screenHeight = screenSize.height;
			int frameWidth = 800;
			int frameHeight = 600;		
			mainFrame.setBounds((screenWidth-frameWidth)/2, (screenHeight-frameHeight)/2,
					frameWidth, frameHeight);
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			mainFrame.setJMenuBar(menuBar);
			menuBar.setVisible(false);
			// Set the content panel
			mainPanel.setLayout(new BorderLayout());
			mainFrame.setContentPane(mainPanel);
		}

		// set menu bar and remove all content on main panel
		if(login != -1) {
			this.login(login);
		}	
	}
	
	private void login(int type) {
		// Set the menu bar		
		menuBar.setVisible(true);
		
		// File (Upload, Exit)
		JMenu menu_0 = new JMenu("File");
		menuBar.add(menu_0);

		m_file_upload.addActionListener(action_upload);
		menu_0.add(m_file_upload);
		
		JMenuItem m_0_1 = new JMenuItem("Exit");	
		menu_0.add(m_0_1);
		m_0_1.addActionListener(action_exit);
		
		if(type == 0) {
			// LTL (Formula)
			JMenu menu_1 = new JMenu("LTL Mngmt");
			menuBar.add(menu_1);

			m_ltl_formula.addActionListener(action_LTL_formula);
			menu_1.add(m_ltl_formula);
			
			m_ltl_statistics.addActionListener(action_LTL_statistics);
			menu_1.add(m_ltl_statistics);
		}

		// Tools (Generation, Setup Properties, Verification)
		JMenu menu_2 = new JMenu("Tools");
		menuBar.add(menu_2);

		m_tools_generation.addActionListener(action_tools_generation);
		menu_2.add(m_tools_generation);
		
		m_tools_setup.addActionListener(action_tools_setup_properties);
		menu_2.add(m_tools_setup);
		
		m_tools_verification.addActionListener(action_tools_verification);
		menu_2.add(m_tools_verification);
		
		// Help (About BPMN2PROMELA)
		JMenu menu_3 = new JMenu("Help");
		menuBar.add(menu_3);
		
		JMenuItem m_3_0 = new JMenuItem("About BPMN2PROMELA");
		m_3_0.addActionListener(action_help_about);
		menu_3.add(m_3_0);
		
		mainPanel.removeAll();
		System.out.println("LOGIN success");
	}

	/**
	 * File --> Upload
	 * Upload BPMN choreography.
	 */
	@SuppressWarnings("serial")
	private class UploadAction extends AbstractAction {
		public UploadAction() {}
		public void actionPerformed(ActionEvent e) {
			System.out.println("Upload pressed!");
			mainPanel.removeAll();
			mainPanel.add(new UploadBPMNPanel(), BorderLayout.CENTER);
			mainPanel.validate();
		}
	}
	
	/**
	 * File --> Exit.
	 */
	@SuppressWarnings("serial")
	private class ExitAction extends AbstractAction {
		public ExitAction() {}
		public void actionPerformed(ActionEvent e) {
			System.out.println("Exit pressed!");
			System.exit(0);
		}
	}
	
	/**
	 * LTL --> Formula
	 * LTL formula definition and management.
	 */
	@SuppressWarnings("serial")
	private class FormulaAction extends AbstractAction {
		public FormulaAction() {}
		public void actionPerformed(ActionEvent e) {
			System.out.println("Formula pressed!");
			mainPanel.removeAll();
			mainPanel.add(new LTLFormulaPanel(), BorderLayout.CENTER);
			mainPanel.validate();
		}
	}
	
	/**
	 * LTL --> Statistics
	 * LTL usage statistics.
	 */
	@SuppressWarnings("serial")
	private class StatisticsAction extends AbstractAction {
		public StatisticsAction() {}
		public void actionPerformed(ActionEvent e) {
			System.out.println("Statistics pressed!");
			mainPanel.removeAll();
			mainPanel.add(new LTLStatisticsPanel(), BorderLayout.CENTER);
			mainPanel.validate();
		}
	}
	
	/**
	 * Tools --> Generation
	 * Generate PROMELA model
	 */
	@SuppressWarnings("serial")
	private class GenerationAction extends AbstractAction {
		public GenerationAction() {}
		public void actionPerformed(ActionEvent e) {
			System.out.println("Generation pressed!");
			mainPanel.removeAll();
			mainPanel.add(new GenerationPanel(), BorderLayout.CENTER);
			mainPanel.validate();
		}
	}
	
	/**
	 * Tools --> Setup Properties
	 * Generate PROMELA model
	 */
	@SuppressWarnings("serial")
	private class SetupAction extends AbstractAction {
		public SetupAction() {}
		public void actionPerformed(ActionEvent e) {
			System.out.println("Setup pressed!");
			mainPanel.removeAll();
			mainPanel.add(new SetupPanel(), BorderLayout.CENTER);
			mainPanel.validate();
		}
	}
	
	/**
	 * Tools --> Verification
	 * Verify PROMELA model with LTL
	 */
	@SuppressWarnings("serial")
	private class VerificationAction extends AbstractAction {
		public VerificationAction() {}
		public void actionPerformed(ActionEvent e) {
			System.out.println("Verification pressed!");
			mainPanel.removeAll();
			mainPanel.add(new VerificationPanel(), BorderLayout.CENTER);
			mainPanel.validate();
		}
	}
	
	/**
	 * Help --> About
	 * About the application
	 */
	@SuppressWarnings("serial")
	private class AboutAction extends AbstractAction {
		public AboutAction() {}
		public void actionPerformed(ActionEvent e) {
			System.out.println("About pressed!");
			AboutFrame about = new AboutFrame();
			// place it at the center of main frame
			centerShowFrame(about);
		}
	}
	
	/********* simulate click action of each menu (begin) **********/
	public static void click_file_upload() {
		m_file_upload.doClick();
	}
	
	public static void click_LTL_formula() {
		m_ltl_formula.doClick();
	}
	
	public static void click_LTL_statistics() {
		m_ltl_statistics.doClick();
	}
	
	public static void click_tools_generation() {
		m_tools_generation.doClick();
	}
	
	public static void click_tools_setup() {
		m_tools_setup.doClick();
	}

	public static void click_tools_verification() {
		m_tools_verification.doClick();
	}
	/********* simulate click action of each menu (end) **********/
	
	/**
	 * Show frame at the center of main frame
	 */
	public static void centerShowFrame(JFrame frame) {
		int aboutWidth = frame.getWidth();
		int aboutHeight = frame.getHeight();
		int aboutX = mainFrame.getBounds().x + (mainFrame.getBounds().width - aboutWidth)/2;
		int aboutY = mainFrame.getBounds().y + (mainFrame.getBounds().height - aboutHeight)/2;
		frame.setBounds(aboutX, aboutY, aboutWidth, aboutHeight);
		frame.setVisible(true);	
	}
	
	/**
	 * Show ConfirmDialog at the center of main frame
	 */
	public static int centerShowshowConfirmDialog(String question, String title) {
		int response = JOptionPane.showConfirmDialog(mainFrame, question, title, JOptionPane.YES_NO_OPTION);
		return response;
	}
	
	/**
	 * Show MessageDialog at the center of main frame
	 */
	public static void centerShowshowMessageDialog(String message, String title) {
		JOptionPane.showMessageDialog(mainFrame, message, title, 1);
	}
	
	/**
	 * Show File chooser at the center of main frame
	 */
	public static int centerFileChooserDialog(JFileChooser chooser_file) {
		int result = chooser_file.showOpenDialog(mainFrame);
		return result;
	}
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BPMN2PROMELAWindow window = new BPMN2PROMELAWindow();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}