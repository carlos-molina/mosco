package generator.jim.translator.command.impl;

import java.util.Iterator;

import generator.jim.beans.ParentInfo;
import generator.jim.composite.AbstractBlock;
import generator.jim.composite.InterfaceComponent;
import generator.jim.convert.AbstractConvertor;
import generator.jim.convert.StoreDecentralized;
import generator.jim.entites.InlineSkip;
import generator.jim.translator.command.interfaces.AbstractExclusiveGatewayMergeTranslator;
import simulator.jim.choreographies.entites.ExclusiveGatewayMerge;

public class ExclusiveGatewayMergeDecentralizedTranslator extends AbstractExclusiveGatewayMergeTranslator {

	public ExclusiveGatewayMergeDecentralizedTranslator() {}

	public ExclusiveGatewayMergeDecentralizedTranslator(ExclusiveGatewayMerge exclusiveGatewayMerge) {
		super(exclusiveGatewayMerge);
	}

	
	@Override
	public void translate() {
		//only translate it once
		if(!super.translated) {
			super.translated = true;
			
			ParentInfo info = super.getExclusiveGatewayMerge().getContainerInfo();
			if(info != null) {
				//Iterator<String> it = Store.getAllProctypeNames().iterator();
				Iterator<String> it = StoreDecentralized.getStoreDecentralized().getAllProctypeNames().iterator();
				String s = null;
				while(it.hasNext()) {
					s = it.next();
					//AbstractBlock block = info.getContainer().getBlockInCertainProcess(s);
					AbstractBlock block = StoreDecentralized.getStoreDecentralized().getCertainBlock(info.getContainer(), s);
					InterfaceComponent skip = new InlineSkip();
					block.getBlockChild(info.getPathIndex()).addChild(skip);
				}
			}
			
			super.getExclusiveGatewayMerge().getOutgoing().getTargetRef().accept(AbstractConvertor.getInitializedConvertor().getTranslatorFactory()).translate();
		}
	}
}
