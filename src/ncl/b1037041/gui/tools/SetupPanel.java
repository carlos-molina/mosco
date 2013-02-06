package ncl.b1037041.gui.tools;

import javax.swing.JPanel;

import ncl.b1037041.LTL.entites.BPMNChoreography;
import ncl.b1037041.LTL.entites.ChoreographyMessageInfo;
import ncl.b1037041.LTL.entites.LTLInstance;
import ncl.b1037041.gui.BPMN2PROMELAWindow;
import ncl.b1037041.gui.parts.BPMNImageFrame;
import ncl.b1037041.gui.parts.LTLSetupPanelBefore;
import ncl.b1037041.manager.ImplLTLManager;
import ncl.b1037041.manager.InterfaceLTLManager;
import javax.swing.JComboBox;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class SetupPanel extends JPanel {
	
	private InterfaceLTLManager ltlManager = new ImplLTLManager();
	
	JComboBox combo_bpmnSelector;
	JPanel panel_properties;
	JButton button_properties;

	public SetupPanel() {
		setLayout(null);
		
		List<BPMNChoreography> allBPMN = this.ltlManager.getAllBPMN();
		Vector<BPMNChoreography> bpmns = new Vector<BPMNChoreography>(allBPMN);
		
		combo_bpmnSelector = new JComboBox(bpmns);
		combo_bpmnSelector.setBounds(10, 10, 221, 21);
		add(combo_bpmnSelector);
		
		button_properties = new JButton("Properties");
		button_properties.setToolTipText("View Porperties of the seleceed BPMN");
		button_properties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				panel_properties.removeAll();
				panel_properties.repaint();
				BPMNChoreography selectedBPMN = getSelectedBPMN();
				List<ChoreographyMessageInfo> infoList = ltlManager.getMessageInfo(selectedBPMN.getId());
				if(infoList.size() == 0) {
					BPMN2PROMELAWindow.centerShowshowMessageDialog(
							"No configurable LTL for " + selectedBPMN.getName() + ", generation first",
							"Hint");
				}
				else {
					List<LTLInstance> instances = ltlManager.getNonConfigedInstances(selectedBPMN.getId());
					panel_properties.setLayout(new GridLayout(instances.size(), 1));
					//panel_properties.setLayout(new FlowLayout());
					
					Iterator<LTLInstance> it = instances.iterator();
					LTLInstance instance = null;
					while(it.hasNext()) {
						instance = it.next();
						panel_properties.add(new LTLSetupPanelBefore(getSelf(), instance));
					}
					panel_properties.validate();
				}
			}
		});
		button_properties.setBounds(241, 9, 112, 23);
		add(button_properties);
		
		// Show image of BPMN
		JButton btnViewImage = new JButton("View Image");
		btnViewImage.setToolTipText("View Choreography");
		btnViewImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {			
				BPMNChoreography selectedBPMN = getSelectedBPMN();
				BPMN2PROMELAWindow.centerShowFrame(new BPMNImageFrame(selectedBPMN));
			}
		});
		btnViewImage.setBounds(363, 9, 112, 23);
		add(btnViewImage);
		
		panel_properties = new JPanel();
		panel_properties.setPreferredSize(new Dimension(770, 120));
		
		JScrollPane scrollPane = new JScrollPane(panel_properties);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 41, 770, 499);
		scrollPane.getViewport().add(panel_properties);
		this.add(scrollPane);
		
		this.validate();
	}
	
	public BPMNChoreography getSelectedBPMN() {
		return (BPMNChoreography)this.combo_bpmnSelector.getSelectedItem();
	}
	
	public JButton getPropertiesButton() {
		return this.button_properties;
	}
	
	private SetupPanel getSelf() {
		return this;
	}
}