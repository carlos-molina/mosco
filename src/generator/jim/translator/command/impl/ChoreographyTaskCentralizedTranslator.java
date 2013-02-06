package generator.jim.translator.command.impl;

import java.util.Iterator;

import ncl.b1037041.LTL.entites.ChoreographyMessageInfo;
import ncl.b1037041.manager.ImplLTLManager;
import ncl.b1037041.manager.InterfaceLTLManager;

import generator.jim.beans.ParentInfo;
import generator.jim.composite.AbstractBlock;
import generator.jim.composite.AbstractInline;
import generator.jim.convert.AbstractConvertor;
import generator.jim.convert.StoreCentralized;
import generator.jim.entites.InlineGoto;
import generator.jim.entites.InlineMessageReceiving;
import generator.jim.entites.InlineMessageSending;
import generator.jim.translator.command.interfaces.AbstractChoreographyTaskTranslator;
import simulator.jim.choreographies.entites.ChoreographyTask;

public class ChoreographyTaskCentralizedTranslator extends AbstractChoreographyTaskTranslator {
	
	private InterfaceLTLManager ltlManager = new ImplLTLManager();

	public ChoreographyTaskCentralizedTranslator() {}

	public ChoreographyTaskCentralizedTranslator(ChoreographyTask choreographyTask) {
		super(choreographyTask);
	}

	
	@Override
	public void translate() {
		if(!super.passed) {
			super.passed = true;
			ParentInfo info = super.getChoreographyTask().getContainerInfo();
			
			//Get the container's block
			//AbstractBlock block = info.getContainer().getBlockInCertainProcess();
			AbstractBlock block = StoreCentralized.getStoreCentralized().getCertainBlock(info.getContainer());
			
			//set sending			
			if(super.getChoreographyTask().getLoopLabelSending() != null) {
				block.getBlockChild(info.getPathIndex()).addChild(super.getChoreographyTask().getLoopLabelSending());
			}
			String message = super.getChoreographyTask().getMessageFlowRef().getMessageRef().getName();
			ChoreographyMessageInfo messageInfo = this.ltlManager.getInfoByMessage(AbstractConvertor.getInitializedConvertor().getContext().getId(), message);
			InlineMessageSending sending = new InlineMessageSending(super.getChannel(), messageInfo);
			block.getBlockChild(info.getPathIndex()).addChild(sending);
			
			//set receiving
			/*if(super.getChoreographyTask().getLoopLabelReceiving() != null) {
				block.getBlockChild(info.getPathIndex()).addChild(super.getChoreographyTask().getLoopLabelReceiving());
			}*/
			InlineMessageReceiving receiving = sending.getCorrespondingMessageReceiving();
			block.getBlockChild(info.getPathIndex()).addChild(receiving);
			
			super.getChoreographyTask().getOutgoing().getTargetRef().accept(AbstractConvertor.getInitializedConvertor().getTranslatorFactory()).translate();
		}
		
		else {
			Iterator<ParentInfo> it = super.getChoreographyTask().getContainerInfoLoops().iterator();
			ParentInfo t = null;
			while(it.hasNext()) {
				t = it.next();
				//AbstractBlock container_block = t.getContainer().getBlockInCertainProcess();
				AbstractBlock container_block = StoreCentralized.getStoreCentralized().getCertainBlock(t.getContainer());
				
				AbstractInline gotoSending = new InlineGoto(super.getChoreographyTask().getLoopLabelSending());
				container_block.getBlockChild(t.getPathIndex()).addChild(gotoSending);

				/*AbstractInline gotoreceiving = new InlineGoto(super.getChoreographyTask().getLoopLabelReceiving());
				container_block.getBlockChild(t.getPathIndex()).addChild(gotoreceiving);*/
			}
		}
	}
}
