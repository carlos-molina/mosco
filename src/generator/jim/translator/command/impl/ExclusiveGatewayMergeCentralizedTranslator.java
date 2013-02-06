package generator.jim.translator.command.impl;

import simulator.jim.choreographies.entites.ExclusiveGatewayMerge;
import generator.jim.beans.ParentInfo;
import generator.jim.composite.AbstractBlock;
import generator.jim.composite.InterfaceComponent;
import generator.jim.convert.AbstractConvertor;
import generator.jim.convert.StoreCentralized;
import generator.jim.entites.InlineSkip;
import generator.jim.translator.command.interfaces.AbstractExclusiveGatewayMergeTranslator;

public class ExclusiveGatewayMergeCentralizedTranslator extends AbstractExclusiveGatewayMergeTranslator {

	public ExclusiveGatewayMergeCentralizedTranslator() {}

	public ExclusiveGatewayMergeCentralizedTranslator(ExclusiveGatewayMerge exclusiveGatewayMerge) {
		super(exclusiveGatewayMerge);
	}

	
	@Override
	public void translate() {
		//only translate it once
		if(!super.translated) {
			super.translated = true;
			
			ParentInfo info = super.getExclusiveGatewayMerge().getContainerInfo();
			if(info != null) {				
				//AbstractBlock block = info.getContainer().getBlockInCertainProcess();
				AbstractBlock block = StoreCentralized.getStoreCentralized().getCertainBlock(info.getContainer());
				InterfaceComponent skip = new InlineSkip();
				block.getBlockChild(info.getPathIndex()).addChild(skip);
			}
			
			super.getExclusiveGatewayMerge().getOutgoing().getTargetRef().accept(AbstractConvertor.getInitializedConvertor().getTranslatorFactory()).translate();
		}
	}
}
