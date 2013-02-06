package ncl.b1037041.gui.ltl;

import java.awt.GridLayout;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import ncl.b1037041.LTL.entites.LTLDefinition;
import ncl.b1037041.gui.parts.LTLItemPanel;
import ncl.b1037041.manager.ImplLTLManager;
import ncl.b1037041.manager.InterfaceLTLManager;

@SuppressWarnings("serial")
public class LTLManagementPanel extends JPanel {
	
	private InterfaceLTLManager ltlManager = new ImplLTLManager();

	public LTLManagementPanel() {
		loadLTLList();
	}
	
	public void loadLTLList() {
		this.removeAll();
		this.repaint();
	
		List<LTLDefinition> definitions = this.ltlManager.getAllLTLDefinition();
		
		// one column
		setLayout(new GridLayout(definitions.size(), 1));
		
		Iterator<LTLDefinition> it = definitions.iterator();
		LTLDefinition definition = null;
		LTLItemPanel itemPanel = null;
		while(it.hasNext()) {
			definition = it.next();
			itemPanel = new LTLItemPanel(definition);
			this.add(itemPanel);
		}
		
		this.validate();		
	}
}
