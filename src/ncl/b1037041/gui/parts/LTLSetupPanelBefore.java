package ncl.b1037041.gui.parts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import ncl.b1037041.LTL.entites.ChoreographyMessageInfo;
import ncl.b1037041.LTL.entites.LTLInstance;
import ncl.b1037041.gui.BPMN2PROMELAWindow;
import ncl.b1037041.gui.tools.SetupPanel;
import ncl.b1037041.manager.ImplLTLManager;
import ncl.b1037041.manager.InterfaceLTLManager;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class LTLSetupPanelBefore extends JPanel {
	
	private InterfaceLTLManager ltlManager = new ImplLTLManager();
	private LTLInstance instance_local;
	private Map<String, JComboBox> varMap = new HashMap<String, JComboBox>();
	
	public LTLSetupPanelBefore(final SetupPanel contentPanel, LTLInstance instance) {
		this.instance_local = instance;
		
		setLayout(null);
		this.setPreferredSize(new Dimension(759, 120));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 10, 644, 104);
		add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(500, 80));
		scrollPane.setViewportView(panel);
		
		List<ChoreographyMessageInfo> infoList = ltlManager.getMessageInfo(instance.getBpmn().getId());
		Vector<ChoreographyMessageInfo> infoItems = new Vector<ChoreographyMessageInfo>(infoList);
		
		// $V0$ is always followed by either $V1$ or $V2$ but not both
		String description = instance.getDefinition().getDescription();
		
		Pattern pattern = Pattern.compile("(.*?)(?:@V\\d+@)");
		Pattern variablePattern = Pattern.compile("@V\\d+@");
		
		Matcher matcher = pattern.matcher(description);
		while(matcher.find()) {
			String group = matcher.group();
			Matcher variableMatcher = variablePattern.matcher(group);
			String var = null;
			if(variableMatcher.find()) {
				var = variableMatcher.group();
				JComboBox combo_var = new JComboBox(infoItems);
				varMap.put(var, combo_var);
			}
			String plainText = group.replaceAll(variablePattern.toString(), "");
			
			JLabel label = new JLabel(plainText);
			JComboBox comboBox = varMap.get(var);
			
			panel.add(label);
			panel.add(comboBox);
		}
		
		Pattern suffixPattern = Pattern.compile("(.*)(?:@V\\d+@)");
		String suffix = description.replaceAll(suffixPattern.toString(), "");
		JLabel label = new JLabel(suffix);
		panel.add(label);

		// Add button
		final JButton button_add = new JButton("Add");
		button_add.setBackground(Color.GREEN);
		button_add.setToolTipText("Add LTL property to BPMN");
		button_add.setBounds(664, 10, 85, 46);
		button_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {		
				String formula = instance_local.getDefinition().getFormula();
				String description = instance_local.getDefinition().getDescription();
				Iterator<String> it = varMap.keySet().iterator();
				String var = null;
				while(it.hasNext()) {
					var = it.next();
					JComboBox combo = varMap.get(var);
					description = description.replaceAll(var, ((ChoreographyMessageInfo)combo.getSelectedItem()).getMessage());
					formula = formula.replaceAll(var, ((ChoreographyMessageInfo)combo.getSelectedItem()).getLtlSymbol());
				}
				ltlManager.updateLTLInstance(instance_local.getId(), formula, description);
				BPMN2PROMELAWindow.centerShowshowMessageDialog(
						"LTL instance has been added to " + instance_local.getBpmn().getName(),
						"Success");
				contentPanel.getPropertiesButton().doClick();
			}
		});
		add(button_add);
		
		// Delete button
		JButton button_delete = new JButton("Delete");
		button_delete.setToolTipText("Delete LTL property");
		button_delete.setBackground(Color.RED);
		button_delete.setBounds(664, 68, 84, 46);
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

		panel.validate();
		
		this.validate();
	}
}
