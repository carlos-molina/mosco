package generator.jim.translator.command.impl;

import java.util.*;

import simulator.jim.choreographies.entites.ChoreographyTask;
import simulator.jim.choreographies.entites.Participant;
import simulator.jim.choreographies.entites.StartEvent;
import generator.jim.composite.InterfaceComponent;
import generator.jim.convert.AbstractConvertor;
import generator.jim.convert.StoreCentralized;
import generator.jim.entites.*;
import generator.jim.tools.Configuration;
import generator.jim.translator.command.interfaces.AbstractStartEventTranslator;

public class StartEventCentralizedTranslator extends AbstractStartEventTranslator {

	public StartEventCentralizedTranslator() {}
	
	public StartEventCentralizedTranslator(StartEvent startEvent) {
		super(startEvent);
	}

	
	@Override
	public void translate() {
		//Init LTL variables
		if("enable".equals(Configuration.getInstance().getValue("generation.LTL"))) {
			super.initLTLVariables();
		}
		
		//Initialize the content, e.g. message, channel and process(proctype) blocks		
		InterfaceComponent msgComment = new InlineComment(Configuration.getInstance().getValue("comment.messages"));
		AbstractConvertor.getInitializedConvertor().getRoot().getMessageBlock().addChild(msgComment);
		
		InterfaceComponent mtype = new InlineMtype(AbstractConvertor.getInitializedConvertor().getContext().getEvents());		
		AbstractConvertor.getInitializedConvertor().getRoot().getMessageBlock().addChild(mtype);
		
		
		// Add only one proctype
		 
		InterfaceComponent ProComment = new InlineComment(Configuration.getInstance().getValue("comment.proctypes"));
		AbstractConvertor.getInitializedConvertor().getRoot().getProcessBlock().addChild(ProComment);
		
		Set<String> participantNames = AbstractConvertor.getInitializedConvertor().getContext().getParticipants();
		Iterator<String> it = participantNames.iterator();
		StringBuffer buffer = new StringBuffer("");
		while(it.hasNext()) {
			buffer.append(it.next());		
		}
		BlockProctype proctype = new BlockProctype(buffer.toString());
		//StoreMainProcess.setMainProcess(proctype);
		StoreCentralized.getStoreCentralized().setMainProctype(proctype);
		AbstractConvertor.getInitializedConvertor().getRoot().getProcessBlock().addChild(proctype);
		InterfaceComponent blank = new BlockBlank();
		proctype.addChild(blank);

		
		// Add all channels
		 
		InterfaceComponent chanComment = new InlineComment(Configuration.getInstance().getValue("comment.channels"));
		AbstractConvertor.getInitializedConvertor().getRoot().getChannelBlock().addChild(chanComment);
		
		Set<ChoreographyTask> tasks = AbstractConvertor.getInitializedConvertor().getContext().getAllTasks();
		Set<InterfaceComponent> ed = new HashSet<InterfaceComponent>();
		Iterator<ChoreographyTask> it_task = tasks.iterator();
		ChoreographyTask c = null;
		while(it_task.hasNext()) {
			c = it_task.next();
			String from = c.getInitiatingParticipantRef().getName();
			String to = ((Participant)(c.getParticipants().toArray()[0])).getName();
			InterfaceComponent channel = InlineChannel.getChannel(from, to);
			if(!ed.contains(channel)) {
				ed.add(channel);
				AbstractConvertor.getInitializedConvertor().getRoot().getChannelBlock().addChild(channel);
			}
		}
		
		/*
		 * Add all init
		 */
		if(!BlockProctype.INIT_ACTIVE.trim().equals(Configuration.getInstance().getValue("init.type"))) {
			BlockInit init = new BlockInit();
			AbstractConvertor.getInitializedConvertor().getRoot().getInitBlock().addChild(init);
			String proName = StoreCentralized.getStoreCentralized().getMainProctypeName();
			InlineRun run = new InlineRun(proName);
			init.addChild(run);
		}
		
		super.getStartEvent().getOutgoing().getTargetRef().accept(AbstractConvertor.getInitializedConvertor().getTranslatorFactory()).translate();
	}
}
