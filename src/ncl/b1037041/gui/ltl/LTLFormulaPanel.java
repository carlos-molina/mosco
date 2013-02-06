package ncl.b1037041.gui.ltl;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class LTLFormulaPanel extends JPanel {
	
	private JPanel definition_panel;
	private JPanel management_panel;

	public LTLFormulaPanel() {
		this.removeAll();
		this.repaint();
		
		this.setLayout(new BorderLayout());
		
		//top definition panel
		definition_panel = new LTLDefinitionPanel();					
		//bottom management panel
		management_panel = new LTLManagementPanel();
		
		//add a scroll bar
		JScrollPane scrollPane = new JScrollPane(management_panel);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getViewport().add(management_panel);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, definition_panel, scrollPane);
		this.add(splitPane, BorderLayout.CENTER);
		splitPane.setDividerLocation(150);
		splitPane.setDividerSize(10);
	
		this.validate();
	}
	
	public JPanel getDefinitionPanel() {
		return this.definition_panel;
	}
	
	public JPanel getManagementPanel() {
		return this.management_panel;
	}
}
