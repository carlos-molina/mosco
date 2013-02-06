package ncl.b1037041.gui.parts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import ncl.b1037041.LTL.entites.BPMNChoreography;
import ncl.b1037041.LTL.entites.LTLInstance;
import ncl.b1037041.exception.AlertException;
import ncl.b1037041.gui.BPMN2PROMELAWindow;
import ncl.b1037041.manager.ImplLTLManager;
import ncl.b1037041.manager.InterfaceLTLManager;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ChoreographyItemPanel extends JPanel {

	private InterfaceLTLManager ltlManager = new ImplLTLManager();
	private BPMNChoreography chor_local;
	private boolean flag = false;
	private String c_button_name = "Change Name";

	private JTextField textField;

	public ChoreographyItemPanel(BPMNChoreography chor) {
		this.chor_local = chor;
		setLayout(null);
		setPreferredSize(new Dimension(750, 40));

		loadChoreographyItem();
	}

	private void loadChoreographyItem() {
		this.removeAll();
		this.repaint();

		textField = new JTextField();
		textField.setEditable(flag);
		textField.setBounds(10, 10, 191, 21);
		textField.setColumns(10);
		textField.setText(chor_local.getName());
		add(textField);

		final JToggleButton toggleButton_changeName = new JToggleButton(c_button_name);
		toggleButton_changeName.setToolTipText("Change Choreography Name");
		toggleButton_changeName.setBounds(211, 9, 120, 23);
		toggleButton_changeName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				flag = !flag;
				textField.setEditable(flag);
				if(flag) {
					// Change editable
					toggleButton_changeName.setText("Confirm");
				}
				else {
					try {
						ltlManager.updateBPMNName(chor_local.getId(), textField.getText());
						BPMN2PROMELAWindow.centerShowshowMessageDialog(
								"Choreography name has been modified.", "Success");
						// Change button name after modification
						toggleButton_changeName.setText(c_button_name);
					} catch (AlertException e) {
						BPMN2PROMELAWindow.centerShowshowMessageDialog(
								e.getMessage(), "failure");
						BPMNChoreography chor = ltlManager.getBPMN(chor_local.getId());
						textField.setText(chor.getName());
					}					
				}
				toggleButton_changeName.repaint();
			}
		});

		add(toggleButton_changeName);

		JButton btn_viewImage = new JButton("View Image");
		btn_viewImage.setBounds(341, 9, 120, 23);
		btn_viewImage.setToolTipText("View Choreography");
		btn_viewImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {			
				BPMNChoreography chor = ltlManager.getBPMN(chor_local.getId());
				BPMN2PROMELAWindow.centerShowFrame(new BPMNImageFrame(chor));
			}
		});
		add(btn_viewImage);

		JButton button_delete = new JButton("Delete");
		button_delete.setBackground(Color.RED);
		button_delete.setBounds(471, 9, 120, 23);
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {						
				int response = BPMN2PROMELAWindow.centerShowshowConfirmDialog(
						"Are you sure you want to delete the choreography?",
						"");
				if (response == JOptionPane.YES_OPTION) {
					// delete database record
					ltlManager.deleteBPMN(chor_local.getId());
					// delete files in folder
					File file = new File(chor_local.getFilePath());
					if(file != null && file.exists()) {
						file.delete();
					}					
					File image = new File(chor_local.getImagePath());	
					if(image != null && image.exists()) {
						image.delete();
					}

					BPMN2PROMELAWindow.centerShowshowMessageDialog(
							"Choreography has been deleted.",
							"Success");
					BPMN2PROMELAWindow.click_file_upload();
				}		
			}
		});
		add(button_delete);
		
		List<LTLInstance> ltl_instances = ltlManager.getLTLInstances(chor_local.getId());
		List<LTLInstance> ltl_configed_instances = ltlManager.getConfigedInstances(chor_local.getId());
		String count = ltl_configed_instances.size() + "/" + ltl_instances.size();
		JLabel label_count = new JLabel("LTL(s): " + count);
		label_count.setFont(new Font("Arial", Font.PLAIN, 16));
		label_count.setBounds(631, 9, 109, 21);
		add(label_count);
	}
}
