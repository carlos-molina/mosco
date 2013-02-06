package simulator.jim.path;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import ncl.b1037041.bpmn.choreographies.Context;

import simulator.jim.choreographies.interfaces.InterfaceFlowNode;

public class AddPathStart implements AddPathAction {

	@Override
	public void add2Path(InterfaceFlowNode currentConnector,
			InterfaceFlowNode nextConnector) {
		Queue<InterfaceFlowNode> queue = new ConcurrentLinkedQueue<InterfaceFlowNode>();
		queue.add(currentConnector);
		queue.add(nextConnector);
		Context.getContext().addPath(queue);
	}
}
