package generator.jim.convert;

import generator.jim.composite.AbstractBlock;
import generator.jim.entites.BlockIf;
import generator.jim.entites.BlockProctype;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import simulator.jim.choreographies.entites.ExclusiveGatewaySplit;
import simulator.jim.choreographies.entites.StartEvent;
import simulator.jim.choreographies.interfaces.InterfaceLogicalParent;

public class StoreDecentralized {

	private static final StoreDecentralized storeD = new StoreDecentralized();
	
	private final Map<String, BlockProctype> processMap = new HashMap<String, BlockProctype>();
	private final Map<ExclusiveGatewaySplit, Map<String, BlockIf>> ifBlockMap = new HashMap<ExclusiveGatewaySplit, Map<String, BlockIf>>();
	
	
	private StoreDecentralized() {}
	
	public static StoreDecentralized getStoreDecentralized() {
		return storeD;
	}
	
	
	private BlockProctype getProctype(String processName) {
		return this.processMap.get(processName);
	}	
	public void putProctype(String processName, BlockProctype proctype) {
		this.processMap.put(processName, proctype);
	}	
	public Set<String> getAllProctypeNames() {
		return this.processMap.keySet();
	}	
	public Collection<BlockProctype> getAllProctypes() {
		return this.processMap.values();
	}
	
	
	public void putIfBlock(ExclusiveGatewaySplit key, String processName, BlockIf value) {		
		if(this.ifBlockMap.get(key) == null) {
			Map<String, BlockIf> notExist = new HashMap<String, BlockIf>();
			//notExist.put(processName, value);
			this.ifBlockMap.put(key, notExist);
		}
		Map<String, BlockIf> exist = this.ifBlockMap.get(key);
		exist.put(processName, value);
	}
	private BlockIf getIfBlock(ExclusiveGatewaySplit key, String processName) {
		return this.ifBlockMap.get(key).get(processName);
	}
	
	
	public AbstractBlock getCertainBlock(InterfaceLogicalParent parent, String processName) {
		if(parent instanceof ExclusiveGatewaySplit) {
			return this.getIfBlock((ExclusiveGatewaySplit)parent, processName);
		}
		if(parent instanceof StartEvent) {
			return this.getProctype(processName);
		}
		return null;
	} 
}
