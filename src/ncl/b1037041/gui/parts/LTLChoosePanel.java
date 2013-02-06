package ncl.b1037041.gui.parts;

import java.awt.Dimension;

import javax.swing.JPanel;

import ncl.b1037041.LTL.entites.LTLDefinition;
import ncl.b1037041.gui.BPMN2PROMELAWindow;
import ncl.b1037041.gui.tools.GenerationPanel;
import ncl.b1037041.manager.ImplLTLManager;
import ncl.b1037041.manager.InterfaceLTLManager;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class LTLChoosePanel extends JPanel {
	
	private InterfaceLTLManager ltlManager = new ImplLTLManager();	
	private LTLDefinition definition;
	private JComboBox comboBox_count;
	private JTextArea textArea_description;

	public LTLChoosePanel(final GenerationPanel contextPanel, final LTLDefinition definition) {
		this.definition = definition;
		
		setLayout(null);
		// without this line, the scroll bar will never show.
		this.setPreferredSize(new Dimension(286, 100));
		
		String[] count = { "1", "2", "3" };
		comboBox_count = new JComboBox(count);
		comboBox_count.setBounds(220, 8, 60, 21);
		add(comboBox_count);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 6, 200, 85);
		add(scrollPane);
		
		textArea_description = new JTextArea();
		textArea_description.setBackground(new Color(230, 230, 250));
		textArea_description.setEditable(false);
		textArea_description.setLineWrap(true);
		textArea_description.setText(this.definition.getDescription());
		scrollPane.setViewportView(textArea_description);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBounds(220, 39, 60, 51);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int bpmnId = contextPanel.getSelectedBPMN().getId();
				int count = Integer.parseInt(comboBox_count.getSelectedItem().toString());
				for(int i=0; i<count; i++) {
					ltlManager.addDefinition2BPMN(bpmnId, definition.getId());
				}
				BPMN2PROMELAWindow.centerShowshowMessageDialog(
					count + " instances has been added to " + 
					contextPanel.getSelectedBPMN().getName(), "Success");
			}
		});
		add(btnNewButton);
	}
}
