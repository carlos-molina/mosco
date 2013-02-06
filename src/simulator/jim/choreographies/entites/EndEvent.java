package simulator.jim.choreographies.entites;

import generator.jim.translator.command.interfaces.InterfaceTranslator;
import generator.jim.translator.factory.interfaces.InterfaceTranslatorFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import simulator.jim.choreographies.interfaces.AbstractEvent;
import simulator.jim.choreographies.interfaces.InterfaceFlowNode;
import simulator.jim.choreographies.interfaces.InterfaceLogicalParent;
import simulator.jim.path.AddPathAction;
import simulator.jim.path.AddPathEnd;

public class EndEvent extends AbstractEvent {

	private SequenceFlow incoming;

	public EndEvent(AddPathAction addPathAction) {
		super(addPathAction);
	}
	
	public EndEvent() {
		this(new AddPathEnd());
	}

	public SequenceFlow getIncoming() {
		return incoming;
	}
	
	@Override
	public void addIncoming(SequenceFlow incoming) {
		this.incoming = incoming;
	}
	
	@Override
	public void addOutgoing(SequenceFlow outgoing) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void goThrough() {
		incoming.getSourceRef().add2Path(this);
		this.increasePass();
	}

	@Override
	public void handleElementInEachPath(List<InterfaceFlowNode> pathList,
			Stack<InterfaceLogicalParent> symbolStack,
			Map<InterfaceLogicalParent, Boolean> m) {
		Iterator<InterfaceLogicalParent> iii = m.keySet().iterator();
		while(iii.hasNext()) {
			InterfaceLogicalParent g = iii.next();
			if(!m.get(g)) {
				g.minusPathIndex();
			}
			g.setNextPathIndex();
		}
	}
	
	@Override
	public InterfaceTranslator accept(InterfaceTranslatorFactory visitor) {
		return visitor.createEndEventTranslator(this);
	}
}
