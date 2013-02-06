package simulator.jim.choreographies.interfaces;

import simulator.jim.path.AddPathAction;
import simulator.jim.path.AddPathCommon;

public abstract class AbstractGateway extends AbstractFlowNode {

	public AbstractGateway(AddPathAction addPathAction) {
		super(addPathAction);
	}
	
	public AbstractGateway() {
		this(new AddPathCommon());
	}
}
