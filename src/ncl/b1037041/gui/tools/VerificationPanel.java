package ncl.b1037041.gui.tools;

import generator.jim.ltl.creator.ImplCompilerCreator;
import generator.jim.ltl.creator.InterfaceModelCreator;
import generator.jim.ltl.creator.InterfaceVerifierCreator;
import generator.jim.ltl.creator.ImplLTLCreator;
import generator.jim.ltl.creator.ImplModelCreator;
import generator.jim.ltl.creator.ImplVerifierCreator;

import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JComboBox;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;

import ncl.b1037041.LTL.entites.BPMNChoreography;
import ncl.b1037041.LTL.entites.LTLInstance;
import ncl.b1037041.exception.AlertException;
import ncl.b1037041.gui.BPMN2PROMELAWindow;
import ncl.b1037041.gui.parts.BPMNImageFrame;
import ncl.b1037041.gui.parts.LTLSelectPanel;
import ncl.b1037041.manager.ImplLTLManager;
import ncl.b1037041.manager.InterfaceLTLManager;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class VerificationPanel extends JPanel {
	
	private static final Pattern NO_ERROR_PATTERN = Pattern.compile(
			"State-vector \\d+ byte, depth reached \\d+, errors: 0");
	
	private InterfaceLTLManager ltlManager = new ImplLTLManager();
	
	private JComboBox combo_bpmnSelector;
	private JPanel panel_properties;
	private JButton button_properties;
	private JCheckBox select_all;
	
	List<LTLSelectPanel> panels = new ArrayList<LTLSelectPanel>();

	public VerificationPanel() {
		setLayout(null);
		
		List<BPMNChoreography> allBPMN = this.ltlManager.getAllBPMN();
		Vector<BPMNChoreography> bpmns = new Vector<BPMNChoreography>(allBPMN);
		
		combo_bpmnSelector = new JComboBox(bpmns);
		combo_bpmnSelector.setBounds(10, 10, 221, 21);
		add(combo_bpmnSelector);
		
		button_properties = new JButton("LTLs");
		button_properties.setToolTipText("View all LTL porperties");
		button_properties.setBounds(241, 9, 112, 23);
		button_properties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				panels.clear();
				select_all.setSelected(false);
				panel_properties.removeAll();
				panel_properties.repaint();
				BPMNChoreography selectedBPMN = getSelectedBPMN();
				
				List<LTLInstance> instances = ltlManager.getConfigedInstances(selectedBPMN.getId());
				if(instances.size() == 0) {
					BPMN2PROMELAWindow.centerShowshowMessageDialog(
							"No configured LTL for " + selectedBPMN.getName() + ", setup first",
							"Hint");
				}
				else {
					System.out.println(selectedBPMN.getId());
					panel_properties.setLayout(new GridLayout(instances.size(), 1));
					
					Iterator<LTLInstance> it = instances.iterator();
					LTLInstance instance = null;
					LTLSelectPanel select_panel = null;					
					while(it.hasNext()) {
						instance = it.next();
						select_panel = new LTLSelectPanel(getSelf(), instance);
						panels.add(select_panel);
						panel_properties.add(select_panel);
					}
					
					panel_properties.validate();
					validate();
				}
			}
		});
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
		
		JButton button_validate = new JButton("Validate");
		button_validate.setBackground(Color.GREEN);
		button_validate.setBounds(572, 9, 93, 23);
		button_validate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Iterator<LTLSelectPanel> it = panels.iterator();
				LTLSelectPanel panel = null;
				while(it.hasNext()) {
					panel = it.next();
					if(panel.isSelected()) {						
						try {
							// model
							InterfaceModelCreator modelCreator = new ImplModelCreator(
									panel.getInstance().getBpmn(), new ImplLTLCreator(panel.getInstance().getSpecificFormula()));
							String model = modelCreator.create();
							System.out.println(model);
							// compiler
							new ImplCompilerCreator().create();
							// verify
							InterfaceVerifierCreator verifierCreator = new ImplVerifierCreator();
							String resule = verifierCreator.create();
							System.out.println(resule);
							Matcher matcher = NO_ERROR_PATTERN.matcher(resule);
							if(matcher.find()) {
								panel.changeSymbol(true);
							}
							else {
								panel.changeSymbol(false);
							}
							// Remove all files
							File folder = new File("./");
							if(folder.isDirectory()) {
								File[] files = folder.listFiles();
								for(int i=0;i<files.length;i++) {
									if(files[i].isFile() && files[i].getName().startsWith("pan") || files[i].getName().endsWith(".trail")) {
										files[i].delete();
									}
								}				
							}
						} catch (AlertException e) {
							e.printStackTrace();
						} 			
					}
				}
			}
		});
		add(button_validate);
		
		panel_properties = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane(panel_properties);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 41, 770, 489);
		add(scrollPane);

		scrollPane.setViewportView(panel_properties);
		
		select_all = new JCheckBox("(Di)select");
		select_all.setBounds(481, 9, 85, 23);
		select_all.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {			
				Iterator<LTLSelectPanel> it = panels.iterator();
				LTLSelectPanel panel = null;
				while(it.hasNext()) {
					panel = it.next();
					if(select_all.isSelected()) {
						panel.doSelect();
					}
					else {
						panel.doDiselect();
					}
				}
			}
		});
		add(select_all);
	}

	public BPMNChoreography getSelectedBPMN() {
		return (BPMNChoreography)this.combo_bpmnSelector.getSelectedItem();
	}
	
	public JButton getPropertiesButton() {
		return this.button_properties;
	}
	
	private VerificationPanel getSelf() {
		return this;
	}
}
