package generator.jim.translator.command.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ncl.b1037041.LTL.entites.ChoreographyMessageInfo;
import ncl.b1037041.manager.ImplLTLManager;
import ncl.b1037041.manager.InterfaceLTLManager;

import simulator.jim.choreographies.entites.ChoreographyTask;
import simulator.jim.choreographies.entites.Participant;
import simulator.jim.choreographies.entites.StartEvent;
import generator.jim.composite.InterfaceComponent;
import generator.jim.convert.AbstractConvertor;
import generator.jim.convert.StoreDecentralized;
import generator.jim.entites.BlockAtomic;
import generator.jim.entites.BlockBlank;
import generator.jim.entites.BlockInit;
import generator.jim.entites.BlockProctype;
import generator.jim.entites.InlineChannel;
import generator.jim.entites.InlineComment;
import generator.jim.entites.InlineDefinition;
import generator.jim.entites.InlineMtype;
import generator.jim.entites.InlineRun;
import generator.jim.tools.Configuration;
import generator.jim.translator.command.interfaces.AbstractStartEventTranslator;

public class StartEventDecentralizedTranslator extends AbstractStartEventTranslator {
	
	private InterfaceLTLManager ltlManager = new ImplLTLManager();

	public StartEventDecentralizedTranslator() {}
	
	public StartEventDecentralizedTranslator(StartEvent startEvent) {
		super(startEvent);
	}

	
	@Override
	public void translate() {
		//Init LTL variables
		if("enable".equals(Configuration.getInstance().getValue("generation.LTL"))) {
			super.initLTLVariables();
		}
		
		//Initialize the content, e.g. message, channel and process(proctype) blocks
		/*
		 * Add all messages
		 */
		InterfaceComponent t = new InlineDefinition("#define TRUE 1");
		AbstractConvertor.getInitializedConvertor().getRoot().getMessageBlock().addChild(t);
		InterfaceComponent f = new InlineDefinition("#define FALSE 0");
		AbstractConvertor.getInitializedConvertor().getRoot().getMessageBlock().addChild(f);
		
		int chorId = AbstractConvertor.getInitializedConvertor().getContext().getId();
		List<ChoreographyMessageInfo> messages = ltlManager.getMessageInfo(chorId);
		Iterator<ChoreographyMessageInfo> it_m = messages.iterator();
		ChoreographyMessageInfo message = null;
		while(it_m.hasNext()) {
			message = it_m.next();
			InterfaceComponent b = new InlineDefinition("bool " + message.getBoolMessage() + " = FALSE;");
			AbstractConvertor.getInitializedConvertor().getRoot().getMessageBlock().addChild(b);
			InterfaceComponent l = new InlineDefinition("#define " + message.getLtlSymbol() + " (" + message.getBoolMessage() + " == TRUE)");
			AbstractConvertor.getInitializedConvertor().getRoot().getMessageBlock().addChild(l);
		}
		
		InterfaceComponent msgComment = new InlineComment(Configuration.getInstance().getValue("comment.messages"));
		AbstractConvertor.getInitializedConvertor().getRoot().getMessageBlock().addChild(msgComment);
		
		InterfaceComponent mtype = new InlineMtype(AbstractConvertor.getInitializedConvertor().getContext().getEvents());		
		AbstractConvertor.getInitializedConvertor().getRoot().getMessageBlock().addChild(mtype);

		/*
		 * Add all proctypes
		 */
		InterfaceComponent ProComment = new InlineComment(Configuration.getInstance().getValue("comment.proctypes"));
		AbstractConvertor.getInitializedConvertor().getRoot().getProcessBlock().addChild(ProComment);
		
		Set<String> participantNames = AbstractConvertor.getInitializedConvertor().getContext().getParticipants();
		Iterator<String> it = participantNames.iterator();
		String pName = null;
		while(it.hasNext()) {
			pName = it.next();
			BlockProctype proctype = new BlockProctype(pName);
			//Store.putProctype(pName, proctype);
			StoreDecentralized.getStoreDecentralized().putProctype(pName, proctype);
			AbstractConvertor.getInitializedConvertor().getRoot().getProcessBlock().addChild(proctype);
			InterfaceComponent blank = new BlockBlank();
			proctype.addChild(blank);
		}

		/*
		 * Add all channels
		 */
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
			BlockAtomic atomic = new BlockAtomic();
			init.addChild(atomic);
			Iterator<String> it_pName = StoreDecentralized.getStoreDecentralized().getAllProctypeNames().iterator();
			String proName = null;
			while(it_pName.hasNext()) {
				proName = it_pName.next();
				InlineRun run = new InlineRun(proName);
				atomic.addChild(run);
			}
		}

		super.getStartEvent().getOutgoing().getTargetRef().accept(AbstractConvertor.getInitializedConvertor().getTranslatorFactory()).translate();
	}
}
