package ncl.b1037041.gui.ltl;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;

import ncl.b1037041.exception.AlertException;
import ncl.b1037041.gui.BPMN2PROMELAWindow;
import ncl.b1037041.manager.*;

import java.awt.event.*;

@SuppressWarnings("serial")
public class LTLDefinitionPanel extends JPanel {
	
	private InterfaceLTLManager ltlManager = new ImplLTLManager();
	
	private JTextArea area_description;
	private JTextField field_formula;
	private JTextField field_nickname;
	
	public LTLDefinitionPanel() {
		setLayout(null);
		
		JLabel label_desc = new JLabel("Description");
		label_desc.setFont(new Font("Arial", Font.PLAIN, 16));
		label_desc.setBounds(10, 28, 80, 28);
		add(label_desc);
		
		JLabel label_formula = new JLabel("Formula");
		label_formula.setFont(new Font("Arial", Font.PLAIN, 16));
		label_formula.setBounds(29, 79, 61, 28);
		add(label_formula);
		
		JLabel label_nickname = new JLabel("Nickname");
		label_nickname.setFont(new Font("Arial", Font.PLAIN, 16));
		label_nickname.setBounds(476, 79, 70, 28);
		add(label_nickname);
		
		field_nickname = new JTextField();
		field_nickname.setFont(new Font("Arial", Font.PLAIN, 16));
		field_nickname.setColumns(10);
		field_nickname.setBounds(553, 80, 129, 28);
		add(field_nickname);
			
		field_formula = new JTextField();
		field_formula.setFont(new Font("Arial", Font.PLAIN, 16));
		field_formula.setColumns(10);
		field_formula.setBounds(93, 80, 373, 28);
		add(field_formula);
	
		area_description = new JTextArea();
		area_description.setFont(new Font("Arial", Font.PLAIN, 14));
		area_description.setLineWrap(true);
		
		JScrollPane scrollPane = new JScrollPane(area_description);
		scrollPane.setBounds(93, 10, 687, 60);
		add(scrollPane);
		
		/*************************** operators (start) *****************************/
		final JButton bt_not = new JButton("!");
		bt_not.setToolTipText("not");
		bt_not.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				showButtonSymbol(bt_not);
			}
		});
		bt_not.setBounds(93, 115, 61, 25);
		add(bt_not);
		
		final JButton bt_and = new JButton("&&");
		bt_and.setToolTipText("and");
		bt_and.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showButtonSymbol(bt_and);
			}
		});
		bt_and.setBounds(153, 115, 61, 25);
		add(bt_and);
		
		final JButton bt_or = new JButton("||");
		bt_or.setToolTipText("or");
		bt_or.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showButtonSymbol(bt_or);
			}
		});
		bt_or.setBounds(213, 115, 61, 25);
		add(bt_or);
		
		final JButton bt_implies = new JButton("->");
		bt_implies.setToolTipText("implies");
		bt_implies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showButtonSymbol(bt_implies);
			}
		});
		bt_implies.setBounds(273, 115, 61, 25);
		add(bt_implies);
		
		final JButton bt_equivalent = new JButton("<->");
		bt_equivalent.setToolTipText("equivalent");
		bt_equivalent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showButtonSymbol(bt_equivalent);
			}
		});
		bt_equivalent.setBounds(333, 115, 61, 25);
		add(bt_equivalent);
		
		final JButton bt_always = new JButton("[]");
		bt_always.setToolTipText("always");
		bt_always.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showButtonSymbol(bt_always);
			}
		});
		bt_always.setBounds(393, 115, 61, 25);
		add(bt_always);
		
		final JButton bt_eventually = new JButton("<>");
		bt_eventually.setToolTipText("eventually");
		bt_eventually.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showButtonSymbol(bt_eventually);
			}
		});
		bt_eventually.setBounds(453, 115, 61, 25);
		add(bt_eventually);
		
		final JButton bt_until = new JButton("U");
		bt_until.setToolTipText("until");
		bt_until.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showButtonSymbol(bt_until);
			}
		});
		bt_until.setBounds(513, 115, 61, 25);
		add(bt_until);
		
		final JButton bt_variable = new JButton("@V@");
		bt_variable.setToolTipText("define replaceable variables, e.g. @V2@");
		bt_variable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showButtonSymbol(bt_variable);
			}
		});
		bt_variable.setBounds(573, 115, 61, 25);
		add(bt_variable);
		/*************************** operators (start) *****************************/
		
		final JButton bt_addLTL = new JButton("Add LTL");
		bt_addLTL.setBackground(Color.GREEN);
		bt_addLTL.setBounds(692, 79, 88, 61);
		bt_addLTL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String description = area_description.getText();
				String formula = field_formula.getText();
				String nickname = field_nickname.getText();
				try {
					ltlManager.addLTLForlumaPrototype(description, formula, nickname);
					BPMN2PROMELAWindow.centerShowshowMessageDialog(
							"This prototype has been added.", "Success");
					BPMN2PROMELAWindow.click_LTL_formula();
				} catch (AlertException e) {
					BPMN2PROMELAWindow.centerShowshowMessageDialog(
							e.getMessage(), "failure");
				}
			}
		});		
		add(bt_addLTL);
		
		

		this.validate();
	}
	
	private void showButtonSymbol(JButton button) {
		String curentString = field_formula.getText() == null ? "" : field_formula.getText() + button.getText();
		field_formula.setText("");
		field_formula.setText(curentString);
		field_formula.requestFocus();
	}
}
