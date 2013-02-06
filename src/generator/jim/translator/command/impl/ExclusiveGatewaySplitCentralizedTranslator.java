package generator.jim.translator.command.impl;

import java.util.Iterator;

import simulator.jim.choreographies.entites.ExclusiveGatewaySplit;
import simulator.jim.choreographies.entites.SequenceFlow;
import simulator.jim.choreographies.interfaces.InterfaceLogicalParent;
import generator.jim.beans.ParentInfo;
import generator.jim.composite.AbstractBlock;
import generator.jim.convert.AbstractConvertor;
import generator.jim.convert.StoreCentralized;
import generator.jim.entites.BlockColon;
import generator.jim.entites.BlockIf;
import generator.jim.translator.command.interfaces.AbstractExclusiveGatewaySplitTranslator;

public class ExclusiveGatewaySplitCentralizedTranslator extends AbstractExclusiveGatewaySplitTranslator {

	public ExclusiveGatewaySplitCentralizedTranslator() {}

	public ExclusiveGatewaySplitCentralizedTranslator(ExclusiveGatewaySplit exclusiveGatewaySplit) {
		super(exclusiveGatewaySplit);
	}

	
	@Override
	public void translate() {
		ParentInfo info = super.getExclusiveGatewaySplit().getContainerInfo();		
		InterfaceLogicalParent container = info.getContainer();
		if(StoreCentralized.getStoreCentralized().getCertainBlock(container) == null) {
			throw new RuntimeException("Main process has not been initialized.");
		}
		else {
			BlockIf ifBlock = new BlockIf();
			Iterator<SequenceFlow> it_colon = super.getExclusiveGatewaySplit().getOutgoings().iterator();
			while(it_colon.hasNext()) {
				it_colon.next();
				BlockColon colon = new BlockColon();
				ifBlock.addChild(colon);
			}
			//super.getExclusiveGatewaySplit().setIfBlock(ifBlock);
			StoreCentralized.getStoreCentralized().putIfBlock(super.getExclusiveGatewaySplit(), ifBlock);
			//AbstractBlock block = container.getBlockInCertainProcess();
			AbstractBlock block = StoreCentralized.getStoreCentralized().getCertainBlock(container);
			block.getBlockChild(info.getPathIndex()).addChild(ifBlock);
			
			Iterator<SequenceFlow> it_flow = super.getExclusiveGatewaySplit().getOutgoings().iterator();
			SequenceFlow flow = null;
			while(it_flow.hasNext()) {
				flow = it_flow.next();
				flow.getTargetRef().accept(AbstractConvertor.getInitializedConvertor().getTranslatorFactory()).translate();
			}
		}
	}
}
