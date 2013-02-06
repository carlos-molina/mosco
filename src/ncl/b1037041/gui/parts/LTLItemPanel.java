package ncl.b1037041.gui.parts;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ncl.b1037041.LTL.entites.LTLDefinition;
import ncl.b1037041.gui.BPMN2PROMELAWindow;
import ncl.b1037041.manager.ImplLTLManager;
import ncl.b1037041.manager.InterfaceLTLManager;

@SuppressWarnings("serial")
public class LTLItemPanel extends JPanel {

	private InterfaceLTLManager ltlManager = new ImplLTLManager();
	private LTLDefinition definition_local;

	public LTLItemPanel(LTLDefinition definition) {
		this.definition_local = definition;

		setLayout(null);
		// without this line, the scroll bar will never show.
		this.setPreferredSize(new Dimension(750, 120));

		JLabel label_description = new JLabel("Description");
		label_description.setFont(new Font("Arial", Font.PLAIN, 16));
		label_description.setBounds(10, 24, 84, 28);
		add(label_description);

		JTextArea area_description = new JTextArea();
		area_description.setBackground(new Color(230, 230, 250));
		area_description.setEditable(false);
		area_description.setFont(new Font("Arial", Font.PLAIN, 14));
		area_description.setLineWrap(true);
		area_description.setText(this.definition_local.getDescription());

		JScrollPane scrollPane = new JScrollPane(area_description);
		scrollPane.setBounds(93, 10, 550, 60);
		add(scrollPane);

		JLabel label_formula = new JLabel("Formula");
		label_formula.setFont(new Font("Arial", Font.PLAIN, 16));
		label_formula.setBounds(10, 80, 84, 28);
		add(label_formula);

		JTextField field_formula = new JTextField();
		field_formula.setBackground(new Color(230, 230, 250));
		field_formula.setEditable(false);
		field_formula.setFont(new Font("Arial", Font.PLAIN, 14));
		field_formula.setColumns(10);
		field_formula.setBounds(93, 80, 431, 28);
		field_formula.setText(this.definition_local.getFormula());
		add(field_formula);
				
		JLabel label_nickname = new JLabel("Nickname");
		label_nickname.setFont(new Font("Arial", Font.PLAIN, 16));
		label_nickname.setBounds(534, 80, 70, 28);
		add(label_nickname);

		JTextField field_nickname = new JTextField();
		field_nickname.setBackground(new Color(230, 230, 250));
		field_nickname.setFont(new Font("Arial", Font.PLAIN, 14));
		field_nickname.setEditable(false);
		field_nickname.setColumns(10);
		field_nickname.setBounds(611, 80, 129, 28);
		field_nickname.setText(this.definition_local.getNickname());
		add(field_nickname);

		JButton deleteButton = new JButton("DELETE");
		deleteButton.setBackground(Color.RED);
		deleteButton.setBounds(653, 10, 87, 60);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int response = BPMN2PROMELAWindow.centerShowshowConfirmDialog(
						"Are you sure you want to delete the LTL prototype?",
						"");
				if (response == JOptionPane.YES_OPTION) {
					ltlManager.removeLTLDefinition(definition_local.getId());
					BPMN2PROMELAWindow.centerShowshowMessageDialog(
							"This LTL prototype has been deleted",
							"Success");
					BPMN2PROMELAWindow.click_LTL_formula();
				}				
			}
		});
		add(deleteButton);


		this.validate();
	}
}
