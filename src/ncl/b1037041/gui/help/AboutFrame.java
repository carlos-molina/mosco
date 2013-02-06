package ncl.b1037041.gui.help;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class AboutFrame extends JFrame {

	private JPanel contentPane;

	public AboutFrame() {
		this.setTitle("About BPMN2PROMELA");
		// "DISPOSE_ON_CLOSE" closes current frame; "EXIT_ON_CLOSE" closes all frames and exit
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(600, 300);
			
		this.contentPane = new JPanel();
		// "null" represents Absolute layout
		this.contentPane.setLayout(null);
		
		this.setContentPane(this.contentPane);
		
		JLabel label_author = new JLabel("Author: Jim.Sun (Jim.Sun1983@hotmail.com)");
		label_author.setFont(new Font("Arial", Font.PLAIN, 18));
		label_author.setBounds(52, 29, 360, 22);
		this.contentPane.add(label_author);
		
		JLabel label_tutor = new JLabel("Tutors:");
		label_tutor.setFont(new Font("Arial", Font.PLAIN, 18));
		label_tutor.setBounds(52, 61, 366, 22);
		contentPane.add(label_tutor);
		
		JLabel label_tutor_1 = new JLabel("Dr. Ellis Solaiman (ellis.solaiman@newcastle.ac.uk)");
		label_tutor_1.setFont(new Font("Arial", Font.PLAIN, 18));
		label_tutor_1.setBounds(52, 93, 411, 22);
		contentPane.add(label_tutor_1);
		
		JLabel label_tutor_2 = new JLabel("Dr. Carlos Molina-jimenez (carlos.molina@newcastle.ac.uk)");
		label_tutor_2.setFont(new Font("Arial", Font.PLAIN, 18));
		label_tutor_2.setBounds(52, 125, 473, 22);
		contentPane.add(label_tutor_2);
		
		JLabel label_school = new JLabel("School of Computing Science");
		label_school.setFont(new Font("Arial", Font.PLAIN, 18));
		label_school.setBounds(175, 176, 235, 22);
		contentPane.add(label_school);
		
		JLabel label_university = new JLabel("Newcastle University, U. K.");
		label_university.setFont(new Font("Arial", Font.PLAIN, 18));
		label_university.setBounds(185, 208, 217, 22);
		contentPane.add(label_university);
	}
}
