package generator.jim.convert;

import java.util.*;

import simulator.jim.choreographies.entites.ExclusiveGatewaySplit;
import simulator.jim.choreographies.entites.StartEvent;
import simulator.jim.choreographies.interfaces.InterfaceLogicalParent;

import generator.jim.composite.AbstractBlock;
import generator.jim.entites.BlockIf;
import generator.jim.entites.BlockProctype;

public class StoreCentralized {

	private static final StoreCentralized storeC = new StoreCentralized();
	
	private BlockProctype mainProcess;
	private final Map<ExclusiveGatewaySplit, BlockIf> ifMap = new HashMap<ExclusiveGatewaySplit, BlockIf>();
	
	private StoreCentralized() {}
	
	public static StoreCentralized getStoreCentralized() {
		return storeC;
	}
	
	
	public void setMainProctype(BlockProctype proctype) {
		this.mainProcess = proctype;
	}	
	private BlockProctype getMainProctype() {
		return this.mainProcess;
	}
	public String getMainProctypeName() {
		return this.getMainProctype().getProcessName();
	}
	
	public void putIfBlock(ExclusiveGatewaySplit key, BlockIf value) {
		this.ifMap.put(key, value);
	}	
	private BlockIf getIfBlock(ExclusiveGatewaySplit key) {
		return this.ifMap.get(key);
	}
	
	public AbstractBlock getCertainBlock(InterfaceLogicalParent parent) {
		if(parent instanceof ExclusiveGatewaySplit) {
			return this.getIfBlock((ExclusiveGatewaySplit)parent);
		}
		if(parent instanceof StartEvent) {
			return this.getMainProctype();
		}
		return null;
	}
}
