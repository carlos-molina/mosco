package ncl.b1037041.gui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;

import ncl.b1037041.access.entites.User;
import ncl.b1037041.exception.AlertException;
import ncl.b1037041.manager.ImplLTLManager;
import ncl.b1037041.manager.InterfaceLTLManager;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	
	private InterfaceLTLManager ltlManager = new ImplLTLManager();
	
	private BPMN2PROMELAWindow local_window;
	
	private JTextField textField_username;
	private JPasswordField passwordField;

	public MainPanel(BPMN2PROMELAWindow window) {
		this.local_window = window;
		setLayout(null);
		
		JLabel label_username = new JLabel("Username");
		label_username.setFont(new Font("Arial", Font.PLAIN, 24));
		label_username.setBounds(212, 146, 110, 42);
		add(label_username);
		
		JLabel label_password = new JLabel("Password");
		label_password.setFont(new Font("Arial", Font.PLAIN, 24));
		label_password.setBounds(212, 198, 110, 42);
		add(label_password);
		
		textField_username = new JTextField();
		textField_username.setBounds(329, 146, 217, 42);
		textField_username.setFont(new Font("Arial", Font.PLAIN, 20));		
		textField_username.setColumns(10);
		add(textField_username);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(329, 198, 217, 42);
		passwordField.setFont(new Font("Arial", Font.PLAIN, 20));	
		add(passwordField);
		
		JButton button_login = new JButton("Login");
		button_login.setBounds(310, 261, 140, 42);
		button_login.setFont(new Font("Arial", Font.PLAIN, 20));
		button_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					User user = ltlManager.getLoginUser(textField_username.getText(), passwordField.getText());
					local_window.initialize(user.getType());
				} catch (AlertException e) {
					BPMN2PROMELAWindow.centerShowshowMessageDialog(
							e.getMessage(), "failure");
				}	
				
			}
		});
		add(button_login);	
	}
}
