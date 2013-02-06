package generator.jim.translator.command.impl;

import java.util.Iterator;

import simulator.jim.choreographies.entites.ExclusiveGatewaySplit;
import simulator.jim.choreographies.entites.SequenceFlow;
import simulator.jim.choreographies.interfaces.InterfaceLogicalParent;
import generator.jim.beans.ParentInfo;
import generator.jim.composite.AbstractBlock;
import generator.jim.convert.AbstractConvertor;
import generator.jim.convert.StoreDecentralized;
import generator.jim.entites.BlockColon;
import generator.jim.entites.BlockIf;
import generator.jim.translator.command.interfaces.AbstractExclusiveGatewaySplitTranslator;

public class ExclusiveGatewaySplitDecentralizedTranslator extends AbstractExclusiveGatewaySplitTranslator {

	public ExclusiveGatewaySplitDecentralizedTranslator() {}

	public ExclusiveGatewaySplitDecentralizedTranslator(ExclusiveGatewaySplit exclusiveGatewaySplit) {
		super(exclusiveGatewaySplit);
	}

	@Override
	public void translate() {
		ParentInfo info = super.getExclusiveGatewaySplit().getContainerInfo();

		InterfaceLogicalParent container = info.getContainer();

		//Iterator<String> it = Store.getAllProctypeNames().iterator();
		Iterator<String> it = StoreDecentralized.getStoreDecentralized().getAllProctypeNames().iterator();
		String processName = null;
		while (it.hasNext()) {
			processName = it.next();
			// for each process, generate a if block
			BlockIf ifBlock = new BlockIf();
			// add path to the if block
			Iterator<SequenceFlow> it_colon = super.getExclusiveGatewaySplit().getOutgoings().iterator();
			while (it_colon.hasNext()) {
				it_colon.next();
				BlockColon colon = new BlockColon();
				ifBlock.addChild(colon);
			}
			// store them in a map
			//this.getExclusiveGatewaySplit().getIfMap().put(processName, ifBlock);
			StoreDecentralized.getStoreDecentralized().putIfBlock(this.getExclusiveGatewaySplit(), processName, ifBlock);
			// get the block of container, where should we add it
			//AbstractBlock block = f.getBlockInCertainProcess(processName);
			AbstractBlock block = StoreDecentralized.getStoreDecentralized().getCertainBlock(container, processName);
			// add it at the correct spot
			block.getBlockChild(info.getPathIndex()).addChild(ifBlock);
		}

		Iterator<SequenceFlow> it_flow = super.getExclusiveGatewaySplit().getOutgoings().iterator();
		SequenceFlow flow = null;
		while (it_flow.hasNext()) {
			flow = it_flow.next();
			flow.getTargetRef().accept(AbstractConvertor.getInitializedConvertor().getTranslatorFactory()).translate();
		}
	}
}
