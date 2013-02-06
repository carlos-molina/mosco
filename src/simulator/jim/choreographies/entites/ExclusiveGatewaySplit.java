package simulator.jim.choreographies.entites;

import generator.jim.beans.ParentInfo;
import generator.jim.translator.command.interfaces.InterfaceTranslator;
import generator.jim.translator.factory.interfaces.InterfaceTranslatorFactory;

import java.util.*;

import simulator.jim.choreographies.interfaces.*;
import simulator.jim.tools.Tool;

public class ExclusiveGatewaySplit extends AbstractExclusiveGateway implements InterfaceSplitter, InterfaceLogicalParent, InterfaceLogicalChild {
	
	private int pathIndex = 0;

	private SequenceFlow incoming;
	private Set<SequenceFlow> outgoings = new HashSet<SequenceFlow>();

	private ParentInfo containerInfo;

	@Override
	public SequenceFlow getIncoming() {
		return incoming;
	}
	
	public Set<SequenceFlow> getOutgoings() {
		return outgoings;
	}

	@Override
	public void addIncoming(SequenceFlow incoming) {
		this.incoming = incoming;
	}

	@Override
	public void addOutgoing(SequenceFlow outgoing) {
		this.outgoings.add(outgoing);
	}
	
	@Override
	public void goThrough() {
		incoming.getSourceRef().add2Path(this);
		this.increasePass();
		Iterator<SequenceFlow> it_out = outgoings.iterator();
		SequenceFlow s = null;
		while(it_out.hasNext()) {
			s = it_out.next();
			
			if(s.getTargetRef() instanceof ChoreographyTask) {
				ChoreographyTask c = (ChoreographyTask)s.getTargetRef();
				if(c.getIncomings().size() > 1) {
					c.setCurrentIncoming(s);
				}
			}
			
			s.getTargetRef().goThrough();
		}		
	}

	@Override
	public void handleElementInEachPath(List<InterfaceFlowNode> pathList,
			Stack<InterfaceLogicalParent> symbolStack,
			Map<InterfaceLogicalParent, Boolean> m) {
		if(this.getContainerInfo() != null && !Tool.matchFinalElementInList(pathList, this)) {
			m.put(this, false);
			symbolStack.push(this);	
		}
		else {
			int index = 0;
			m.put(symbolStack.peek(), true);
			index = symbolStack.peek().getPathIndex();
			
			//first add container
			ParentInfo info = new ParentInfo(symbolStack.peek(), index);
			this.setContainerInfo(info);
			
			m.put(this, false);
			//second add itself to stack
			symbolStack.push(this);
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
	public void setNextPathIndex() {
		this.pathIndex++;
	}

	@Override
	public void minusPathIndex() {
		this.pathIndex--;
	}
	
	@Override
	public int getPathIndex() {
		return this.pathIndex;
	}
	
	@Override
	public InterfaceTranslator accept(InterfaceTranslatorFactory visitor) {
		return visitor.createExclusiveGatewaySplitTranslator(this);
	}
}
