package ncl.b1037041.gui.tools;

import generator.jim.convert.AbstractConvertor;
import generator.jim.convert.InterfaceConvertor;
import generator.jim.tools.LTLSymbolGenerator;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;

import ncl.b1037041.LTL.entites.BPMNChoreography;
import ncl.b1037041.LTL.entites.LTLDefinition;
import ncl.b1037041.bpmn.choreographies.Context;
import ncl.b1037041.gui.BPMN2PROMELAWindow;
import ncl.b1037041.gui.parts.BPMNImageFrame;
import ncl.b1037041.gui.parts.LTLChoosePanel;
import ncl.b1037041.manager.ImplLTLManager;
import ncl.b1037041.manager.InterfaceLTLManager;

@SuppressWarnings("serial")
public class GenerationPanel extends JPanel {
	
	private InterfaceLTLManager ltlManager = new ImplLTLManager();
	
	private JTextArea area_promela_model;
	private JComboBox combo_bpmnSelector;

	public GenerationPanel() {
		setLayout(null);
		
		List<BPMNChoreography> allBPMN = this.ltlManager.getAllBPMN();
		Vector<BPMNChoreography> bpmns = new Vector<BPMNChoreography>(allBPMN);
		
		combo_bpmnSelector = new JComboBox(bpmns);
		combo_bpmnSelector.setBounds(10, 10, 221, 21);
		add(combo_bpmnSelector);
		
		// Generate model
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setToolTipText("Generate PROMELA model");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {		
				BPMNChoreography selectedBPMN = getSelectedBPMN();
				ltlManager.deleteAllInfoOfBPMN(selectedBPMN.getId());
				try {
					Context context = Context.init(selectedBPMN);
					LTLSymbolGenerator symbolGenerator = new LTLSymbolGenerator();
					Set<String> messages = context.getEvents();
					Iterator<String> it = messages.iterator();
					while(it.hasNext()) {
						String message = it.next();
						String ltlSymbol = symbolGenerator.getNextSymbol();
						String boolMessage = message + "Rcv";
						ltlManager.addBPMNMessageInfo(selectedBPMN.getId(), message, ltlSymbol, boolMessage);
					}
					
					InterfaceConvertor convertor = AbstractConvertor.getConvertor(context);
					String promelaContent = convertor.convert();
					area_promela_model.setText("");
					area_promela_model.setText(promelaContent);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnGenerate.setBounds(241, 9, 112, 23);
		add(btnGenerate);
		
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

		area_promela_model = new JTextArea();
		area_promela_model.setBounds(10, 53, 685, 58);
		area_promela_model.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JScrollPane scrollPane_model = new JScrollPane(area_promela_model);
		scrollPane_model.setBounds(10, 41, 465, 489);
		add(scrollPane_model);
		
		JLabel lblNewLabel = new JLabel("LTL properties");
		lblNewLabel.setBounds(566, 1, 135, 42);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		add(lblNewLabel);
		
		JPanel LTL_panel = new JPanel();
		
		JScrollPane scrollPane_LTL = new JScrollPane(LTL_panel);
		scrollPane_LTL.setBounds(485, 41, 305, 489);
		scrollPane_LTL.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_LTL.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_LTL.setViewportView(LTL_panel);
		add(scrollPane_LTL);
		
		List<LTLDefinition> definitions = this.ltlManager.getAllLTLDefinition();
		// one column
		LTL_panel.setLayout(new GridLayout(definitions.size(), 1));
		
		Iterator<LTLDefinition> it = definitions.iterator();
		LTLDefinition definition = null;
		LTLChoosePanel itemPanel = null;
		while(it.hasNext()) {
			definition = it.next();
			itemPanel = new LTLChoosePanel(this, definition);
			LTL_panel.add(itemPanel);
		}
		
		LTL_panel.validate();	
		this.validate();
	}
	
	public BPMNChoreography getSelectedBPMN() {
		return (BPMNChoreography)this.combo_bpmnSelector.getSelectedItem();
	}
}
