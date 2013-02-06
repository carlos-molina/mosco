package simulator.jim.choreographies.entites;

import generator.jim.beans.ParentInfo;
import generator.jim.entites.*;
import generator.jim.translator.command.interfaces.InterfaceTranslator;
import generator.jim.translator.factory.interfaces.InterfaceTranslatorFactory;

import java.util.*;

import simulator.jim.choreographies.interfaces.*;
import simulator.jim.path.AddPathAction;
import simulator.jim.path.AddPathCommon;
import simulator.jim.tools.Tool;

public class ChoreographyTask extends AbstractFlowNode implements InterfaceMerger, InterfaceLogicalChild {

	private String name;
	private Participant initiatingParticipantRef;
	//The line which is the original incoming
	private SequenceFlow incoming;
	//All incoming flows from the diagram
	private Set<SequenceFlow> incomings = new HashSet<SequenceFlow>();
	//All loop back incoming flows
	private Set<SequenceFlow> incomingLoops = new HashSet<SequenceFlow>();
	//For a loop back task
	private SequenceFlow currentIncoming;
	private SequenceFlow outgoing;
	private Set<Participant> participants = new HashSet<Participant>();
	private MessageFlow messageFlowRef;
	
	private InlineLoopLabel loopLabelSending;
	private InlineLoopLabel loopLabelReceiving;
	
	private Set<ParentInfo> containerInfoLoops = new HashSet<ParentInfo>();	
	private ParentInfo containerInfo;

	public ChoreographyTask(AddPathAction addPathAction) {
		super(addPathAction);
	}
	
	public ChoreographyTask() {
		this(new AddPathCommon());
	}

	
	public InlineLoopLabel getLoopLabelSending() {
		return loopLabelSending;
	}
	public void setLoopLabelSending(InlineLoopLabel loopLabelSending) {
		this.loopLabelSending = loopLabelSending;
	}
	public InlineLoopLabel getLoopLabelReceiving() {
		return loopLabelReceiving;
	}
	public void setLoopLabelReceiving(InlineLoopLabel loopLabelReceiving) {
		this.loopLabelReceiving = loopLabelReceiving;
	}
	public Set<ParentInfo> getContainerInfoLoops() {
		return containerInfoLoops;
	}
	public void addContainerInfoLoop(ParentInfo containerInfoLoop) {
		this.containerInfoLoops.add(containerInfoLoop);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Participant getInitiatingParticipantRef() {
		return initiatingParticipantRef;
	}
	public void setInitiatingParticipantRef(Participant initiatingParticipantRef) {
		this.initiatingParticipantRef = initiatingParticipantRef;
	}
	public SequenceFlow getIncoming() {
		return incoming;
	}

	@Override
	public void addIncoming(SequenceFlow incoming) {
		//this.incoming = incoming;
		this.incomings.add(incoming);
	}
	@Override
	public void addOutgoing(SequenceFlow outgoing) {
		this.outgoing = outgoing;
	}
	
	@Override
	public SequenceFlow getOutgoing() {
		return this.outgoing;
	}
	@Override
	public Set<SequenceFlow> getIncomings() {
		return this.incomings;
	}
	
	/************************** Setter and Getter start ***************************/
	public Set<Participant> getParticipants() {
		return this.participants;
	}
	public void addParticipant(Participant participant) {
		this.participants.add(participant);
	}
	public void setMessageFlowRef(MessageFlow messageFlowRef) {
		this.messageFlowRef = messageFlowRef;
	}
	public MessageFlow getMessageFlowRef() {
		return this.messageFlowRef;
	}
	/************************** Setter and Getter end ***************************/
	
	public void setCurrentIncoming(SequenceFlow currentIncoming) {
		this.currentIncoming = currentIncoming;
	}
	public boolean isLoopBack() {
		return !this.incomingLoops.isEmpty();
	}

	@Override
	public void goThrough() {
		//Initialize fields and add to path just only once
		if(this.getPassTime() == 0 || this.incomings.size() == 1) {
			Iterator<SequenceFlow> it = this.incomings.iterator();
			SequenceFlow s = null;
			while(it.hasNext()) {
				s = it.next();
				if(s.getSourceRef().getPassTime() == 0) {
					this.incomingLoops.add(s);					
				}
				else {
					this.incoming = s;
					this.incoming.getSourceRef().add2Path(this);
				}
			}
			if(!this.incomingLoops.isEmpty()) {
				this.loopLabelSending = InlineLoopLabel.getLoopLable(this.messageFlowRef.getMessageRef().getName(), InlineLoopLabel.SENDING);
				this.loopLabelReceiving = InlineLoopLabel.getLoopLable(this.messageFlowRef.getMessageRef().getName(), InlineLoopLabel.RECEIVING);
			}
			this.increasePass();
			
			if(this.getOutgoing().getTargetRef() instanceof ChoreographyTask) {
				ChoreographyTask c = (ChoreographyTask)this.getOutgoing().getTargetRef();
				if(c.getIncomings().size() > 1) {
					c.setCurrentIncoming(this.getOutgoing());
				}
			}
			this.getOutgoing().getTargetRef().goThrough();
		}

		if(this.incomings.size() > this.getPassTime()) {			
			if(this.currentIncoming != null) {
				this.currentIncoming.getSourceRef().add2Path(this);
				this.increasePass();
			}
		}		
	}	

	@Override
	public void handleElementInEachPath(List<InterfaceFlowNode> pathList,
			Stack<InterfaceLogicalParent> symbolStack,
			Map<InterfaceLogicalParent, Boolean> m) {
		if(this.getContainerInfo() == null || Tool.matchFinalElementInList(pathList, this)) {
			int index = 0;
			m.put(symbolStack.peek(), true);
			index = symbolStack.peek().getPathIndex();
			
			ParentInfo info = new ParentInfo(symbolStack.peek(), index);
			
			if(Tool.matchFinalElementInList(pathList, this)) {
				if(!info.equals(this.getContainerInfo())) {
					this.addContainerInfoLoop(info);
				}
			}
			else {
				this.setContainerInfo(info);
			}
		}		
	}

	@Override
	public void setContainerInfo(ParentInfo containerInfo) {
		this.containerInfo = containerInfo;
	}

	@Override
	public ParentInfo getContainerInfo() {
		return this.containerInfo;
	}
	
	
	@Override
	public InterfaceTranslator accept(InterfaceTranslatorFactory visitor) {
		return visitor.createChoreographyTaskTranslator(this);
	}
}