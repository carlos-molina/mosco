package generator.jim.entites;

import java.util.Iterator;

import generator.jim.composite.AbstractBlock;
import generator.jim.composite.InterfaceComponent;
import generator.jim.tools.Configuration;

public class BlockProctype extends AbstractBlock {
	
	private static String INIT_FORM = Configuration.getInstance().getValue("init.type");
	
	public static final String INIT_ACTIVE = "active ";
	
	private static final String DEFINE_PROCTYPE = "proctype ";
	private static final String DEFINE_START = "() {";
	private static final String DEFINE_END = "}";
	private String processName;

	public String getProcessName() {
		return processName;
	}

	public BlockProctype(String processName) {
		super((INIT_ACTIVE.trim().equals(INIT_FORM) ? (INIT_ACTIVE + DEFINE_PROCTYPE) : DEFINE_PROCTYPE) + processName + DEFINE_START, DEFINE_END);
		this.processName = processName;
	}
	
	@Override
	public void removeEmptyIf() {
		Iterator<InterfaceComponent> it = this.getChildren().iterator();
		BlockBlank c = null;
		while(it.hasNext()) {
			c = (BlockBlank)it.next();
			c.removeEmptyIf();
		}
	}
}
