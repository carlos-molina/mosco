package simulator.jim.choreographies.entites;

import java.util.*;

import ncl.b1037041.bpmn.choreographies.Context;

import org.dom4j.Element;

import simulator.jim.choreographies.interfaces.AbstractExclusiveGateway;

public class Choreography {

	private Set<Participant> participants = new HashSet<Participant>();
	private Set<MessageFlow> messageFlows = new HashSet<MessageFlow>();
	private Set<SequenceFlow> sequenceFlows = new HashSet<SequenceFlow>();
	private Set<AbstractExclusiveGateway> exclusiveGateways = new HashSet<AbstractExclusiveGateway>();
	private Set<ChoreographyTask> choreographyTasks = new HashSet<ChoreographyTask>();
	private StartEvent startEvent;
	private EndEvent endEvent;	
	private Element choreographyElement;
	
	public Element getChoreographyElemwnt() {
		return choreographyElement;
	}
	public void setChoreographyElemwnt(Element choreographyElement) {
		this.choreographyElement = choreographyElement;
	}
	public Set<Participant> getParticipants() {
		return participants;
	}
	public void addParticipant(Participant participant) {
		this.participants.add(participant);
	}
	public Set<MessageFlow> getMessageFlows() {
		return messageFlows;
	}
	public void addMessageFlow(MessageFlow messageFlow) {
		this.messageFlows.add(messageFlow);
	}
	public Set<SequenceFlow> getSequenceFlows() {
		return sequenceFlows;
	}
	public void addSequenceFlow(SequenceFlow sequenceFlow) {
		this.sequenceFlows.add(sequenceFlow);
	}
	public Set<AbstractExclusiveGateway> getExclusiveGateways() {
		return exclusiveGateways;
	}
	public void addExclusiveGateway(AbstractExclusiveGateway exclusiveGateway) {
		this.exclusiveGateways.add(exclusiveGateway);
	}
	public Set<ChoreographyTask> getChoreographyTasks() {
		return choreographyTasks;
	}
	public void addChoreographyTask(ChoreographyTask choreographyTask) {
		this.choreographyTasks.add(choreographyTask);
	}	
	public StartEvent getStartEvent() {
		return startEvent;
	}
	public EndEvent getEndEvent() {
		return endEvent;
	}	
	public void setStartEvent(StartEvent startEvent) {
		this.startEvent = startEvent;
	}
	public void setEndEvent(EndEvent endEvent) {
		this.endEvent = endEvent;
	}
	
	
	public Message searchSingleMessage(String id) {
		if(Context.getContext().getDefinitions().getMessages().isEmpty() || id == null) {
			return null;
		}
		Iterator<Message> it = Context.getContext().getDefinitions().getMessages().iterator();
		Message m;
		while(it.hasNext()) {
			m = it.next();
			if(id.equals(m.getId())) {
				return m;
			}
		}
		return null;
	}
	
	public SequenceFlow searchSingleSequenceFlow(String id) {
		if(sequenceFlows.isEmpty() || id == null) {
			return null;
		}
		Iterator<SequenceFlow> it = sequenceFlows.iterator();
		SequenceFlow sf;
		while(it.hasNext()) {
			sf = it.next();
			if(id.equals(sf.getId())) {
				return sf;
			}
		}
		return null;
	}
	
	public MessageFlow searchSingleMessageFlow(String id) {
		if(messageFlows.isEmpty() || id == null) {
			return null;
		}
		Iterator<MessageFlow> it = messageFlows.iterator();
		MessageFlow mf;
		while(it.hasNext()) {
			mf = it.next();
			if(id.equals(mf.getId())) {
				return mf;
			}
		}
		return null;
	}
	
	public Participant searchSingleParticipant(String id) {
		if(participants.isEmpty() || id == null) {
			return null;
		}
		Iterator<Participant> it = participants.iterator();
		Participant p;
		while(it.hasNext()) {
			p = it.next();
			if(id.equals(p.getId())) {
				return p;
			}
		}
		return null;
	}
}
