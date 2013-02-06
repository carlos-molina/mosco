package simulator.jim.choreographies.entites;

import generator.jim.beans.ParentInfo;
import generator.jim.translator.command.interfaces.InterfaceTranslator;
import generator.jim.translator.factory.interfaces.InterfaceTranslatorFactory;

import java.util.*;

import simulator.jim.choreographies.interfaces.*;

public class ExclusiveGatewayMerge extends AbstractExclusiveGateway implements InterfaceMerger, InterfaceLogicalChild {

	private SequenceFlow outgoing;
	private Set<SequenceFlow> incomings = new HashSet<SequenceFlow>();

	private ParentInfo containerInfo;

	@Override
	public SequenceFlow getOutgoing() {
		return outgoing;
	}
	
	@Override
	public Set<SequenceFlow> getIncomings() {
		return incomings;
	}	

	@Override
	public void addIncoming(SequenceFlow incoming) {
		this.incomings.add(incoming);
	}

	@Override
	public void addOutgoing(SequenceFlow outgoing) {
		this.outgoing = outgoing;
	}
	
	@Override
	public void goThrough() {
		Iterator<SequenceFlow> it_in = incomings.iterator();
		this.increasePass();
		SequenceFlow sf = null;
		while(it_in.hasNext()) {
			sf = it_in.next();
			sf.getSourceRef().add2Path(this);
		}
		outgoing.getTargetRef().goThrough();
	}

	@Override
	public void handleElementInEachPath(List<InterfaceFlowNode> pathList,
			Stack<InterfaceLogicalParent> symbolStack,
			Map<InterfaceLogicalParent, Boolean> m) {
		if(this.getContainerInfo() == null) {
			int thisIndex = pathList.indexOf(this);
			//if it is next to a split, containerInfo is not null for this merge
			if(pathList.get(thisIndex-1) instanceof ExclusiveGatewaySplit) {
				int index = 0;
				m.put((ExclusiveGatewaySplit)symbolStack.peek(), true);
				index = symbolStack.peek().getPathIndex();
				ParentInfo info = new ParentInfo(symbolStack.peek(), index);
				this.setContainerInfo(info);
			}
		}
		symbolStack.pop();
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
		return visitor.createExclusiveGatewayMergeTranslator(this);
	}
}
