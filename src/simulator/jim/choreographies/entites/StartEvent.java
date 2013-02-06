package simulator.jim.choreographies.entites;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import generator.jim.translator.command.interfaces.InterfaceTranslator;
import generator.jim.translator.factory.interfaces.InterfaceTranslatorFactory;
import simulator.jim.choreographies.interfaces.AbstractEvent;
import simulator.jim.choreographies.interfaces.InterfaceFlowNode;
import simulator.jim.choreographies.interfaces.InterfaceLogicalParent;
import simulator.jim.path.*;

public class StartEvent extends AbstractEvent implements
		InterfaceLogicalParent {
	
	private SequenceFlow outgoing;

	public StartEvent(AddPathAction addPathAction) {
		super(addPathAction);
	}

	public StartEvent() {
		this(new AddPathStart());
	}

	public SequenceFlow getOutgoing() {
		return outgoing;
	}

	@Override
	public void addOutgoing(SequenceFlow outgoing) {
		this.outgoing = outgoing;
	}

	@Override
	public void addIncoming(SequenceFlow incoming) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void goThrough() {
		this.increasePass();
		this.outgoing.getTargetRef().goThrough();
	}

	@Override
	public void handleElementInEachPath(List<InterfaceFlowNode> pathList,
			Stack<InterfaceLogicalParent> symbolStack,
			Map<InterfaceLogicalParent, Boolean> m) {
		symbolStack.push(this);
	}
	
	@Override
	public void setNextPathIndex() {}

	@Override
	public void minusPathIndex() {}
	
	@Override
	public int getPathIndex() {
		return 0;
	}
	
	@Override
	public InterfaceTranslator accept(InterfaceTranslatorFactory visitor) {
		return visitor.createStartEventTranslator(this);
	}
}