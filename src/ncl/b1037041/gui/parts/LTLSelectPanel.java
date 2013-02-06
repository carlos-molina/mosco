package ncl.b1037041.gui.parts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import ncl.b1037041.LTL.entites.LTLInstance;
import ncl.b1037041.gui.BPMN2PROMELAWindow;
import ncl.b1037041.gui.tools.VerificationPanel;
import ncl.b1037041.manager.ImplLTLManager;
import ncl.b1037041.manager.InterfaceLTLManager;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LTLSelectPanel extends JPanel {
	
	private InterfaceLTLManager ltlManager = new ImplLTLManager();
	private LTLInstance instance_local;
	private VerificationPanel contentPanel;
	
	private JTextArea area_description;
	private JCheckBox checkBox_select;
	private JTextField textField;

	public LTLSelectPanel(final VerificationPanel contentPanel, LTLInstance instance) {
		this.instance_local = instance;
		this.contentPanel = contentPanel;
		
		setLayout(null);
		this.setPreferredSize(new Dimension(751, 65));
		
		checkBox_select = new JCheckBox();
		checkBox_select.setBounds(6, 23, 21, 23);
		add(checkBox_select);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 5, 557, 56);
		add(scrollPane);

		area_description = new JTextArea();
		area_description.setFont(new Font("Arial", Font.PLAIN, 14));
		area_description.setLineWrap(true);
		area_description.setText(this.instance_local.getSpecificDescription());
		scrollPane.setViewportView(area_description);
		
		JButton button_detail = new JButton("Detail");
		button_detail.setToolTipText("View detail of the property");
		button_detail.setBounds(597, 4, 69, 25);
		button_detail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		add(button_detail);
		
		JButton button_delete = new JButton("Delete");
		button_delete.setBackground(Color.RED);
		button_delete.setToolTipText("Delete property");
		button_delete.setBounds(597, 36, 69, 25);
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {		
				int response = BPMN2PROMELAWindow.centerShowshowConfirmDialog(
						"Are you sure you want to delete the LTL prototype?",
						"");
				if (response == JOptionPane.YES_OPTION) {
					ltlManager.deleteLTLInstance(instance_local.getId());
					BPMN2PROMELAWindow.centerShowshowMessageDialog(
							"This LTL prototype has been deleted",
							"Success");
					contentPanel.getPropertiesButton().doClick();
				}
			}
		});
		add(button_delete);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setFont(new Font("Arial", Font.PLAIN, 16));
		textField.setBounds(676, 6, 65, 55);
		textField.setText("Wait...");
		textField.setColumns(10);
		add(textField);
	}

	public boolean isSelected() {
		return this.checkBox_select.isSelected();
	}
	
	public void doSelect() {
		this.checkBox_select.setSelected(true);
	}
	
	public void doDiselect() {
		this.checkBox_select.setSelected(false);
	}
	
	public LTLInstance getInstance() {
		return this.instance_local;
	}
	
	public void changeSymbol(boolean pass) {
		if(pass) {
			textField.setBackground(Color.GREEN);
			textField.setText("Passed");
		}
		else {
			textField.setBackground(Color.RED);	
			textField.setText("Violated");
		}
		textField.repaint();
		this.repaint();
	}
}
