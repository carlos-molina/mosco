package generator.jim.translator.command.impl;

import java.util.Iterator;

import ncl.b1037041.LTL.entites.ChoreographyMessageInfo;
import ncl.b1037041.manager.ImplLTLManager;
import ncl.b1037041.manager.InterfaceLTLManager;

import simulator.jim.choreographies.entites.ChoreographyTask;
import simulator.jim.choreographies.entites.Participant;
import generator.jim.beans.ParentInfo;
import generator.jim.composite.AbstractBlock;
import generator.jim.composite.AbstractInline;
import generator.jim.convert.AbstractConvertor;
import generator.jim.convert.StoreDecentralized;
import generator.jim.entites.BlockAtomic;
import generator.jim.entites.InlineGoto;
import generator.jim.entites.InlineMessageReceiving;
import generator.jim.entites.InlineMessageSending;
import generator.jim.translator.command.interfaces.AbstractChoreographyTaskTranslator;

public class ChoreographyTaskDecentralizedTranslator extends AbstractChoreographyTaskTranslator {
	
	private InterfaceLTLManager ltlManager = new ImplLTLManager();

	public ChoreographyTaskDecentralizedTranslator() {}

	public ChoreographyTaskDecentralizedTranslator(ChoreographyTask choreographyTask) {
		super(choreographyTask);
	}

	
	@Override
	public void translate() {
		if(!super.passed) {
			super.passed = true;
			ParentInfo info = super.getChoreographyTask().getContainerInfo();
			
			//set sending
			//AbstractBlock block_send = info.getContainer().getBlockInCertainProcess(super.getChoreographyTask().getInitiatingParticipantRef().getName());
			AbstractBlock block_send = StoreDecentralized.getStoreDecentralized().getCertainBlock(info.getContainer(), super.getChoreographyTask().getInitiatingParticipantRef().getName());
			if(super.getChoreographyTask().getLoopLabelSending() != null) {
				block_send.getBlockChild(info.getPathIndex()).addChild(super.getChoreographyTask().getLoopLabelSending());
			}
			String message = super.getChoreographyTask().getMessageFlowRef().getMessageRef().getName();
			ChoreographyMessageInfo messageInfo = this.ltlManager.getInfoByMessage(AbstractConvertor.getInitializedConvertor().getContext().getId(), message);			
			InlineMessageSending sending = new InlineMessageSending(super.getChannel(), messageInfo);
			block_send.getBlockChild(info.getPathIndex()).addChild(sending);
			
			//set receiving
			//AbstractBlock block_receive = info.getContainer().getBlockInCertainProcess(((Participant)(super.getChoreographyTask().getParticipants().toArray()[0])).getName());
			AbstractBlock block_receive = StoreDecentralized.getStoreDecentralized().getCertainBlock(info.getContainer(), ((Participant)(super.getChoreographyTask().getParticipants().toArray()[0])).getName());
			if(super.getChoreographyTask().getLoopLabelReceiving() != null) {
				block_receive.getBlockChild(info.getPathIndex()).addChild(super.getChoreographyTask().getLoopLabelReceiving());
			}
			InlineMessageReceiving receiving = sending.getCorrespondingMessageReceiving();
			BlockAtomic atomic = new BlockAtomic();
			atomic.addChild(receiving);
			block_receive.getBlockChild(info.getPathIndex()).addChild(atomic);
			
			super.getChoreographyTask().getOutgoing().getTargetRef().accept(AbstractConvertor.getInitializedConvertor().getTranslatorFactory()).translate();
		}
		
		else {
			Iterator<ParentInfo> it = super.getChoreographyTask().getContainerInfoLoops().iterator();
			ParentInfo t = null;
			while(it.hasNext()) {
				t = it.next();
				//AbstractBlock block_send = t.getContainer().getBlockInCertainProcess(super.getChoreographyTask().getInitiatingParticipantRef().getName());
				AbstractBlock block_send = StoreDecentralized.getStoreDecentralized().getCertainBlock(t.getContainer(), super.getChoreographyTask().getInitiatingParticipantRef().getName());
				AbstractInline gotoSending = new InlineGoto(super.getChoreographyTask().getLoopLabelSending());
				block_send.getBlockChild(t.getPathIndex()).addChild(gotoSending);
				
				//AbstractBlock block_receive = t.getContainer().getBlockInCertainProcess(((Participant)(this.getChoreographyTask().getParticipants().toArray()[0])).getName());
				AbstractBlock block_receive = StoreDecentralized.getStoreDecentralized().getCertainBlock(t.getContainer(), ((Participant)(super.getChoreographyTask().getParticipants().toArray()[0])).getName());
				AbstractInline gotoreceiving = new InlineGoto(super.getChoreographyTask().getLoopLabelReceiving());
				block_receive.getBlockChild(t.getPathIndex()).addChild(gotoreceiving);
			}
		}
	}
}
