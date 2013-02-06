package simulator.jim.path;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import ncl.b1037041.bpmn.choreographies.Context;

import simulator.jim.choreographies.entites.ChoreographyTask;
import simulator.jim.choreographies.interfaces.InterfaceFlowNode;
import simulator.jim.tools.Tool;

public class AddPathCommon implements AddPathAction {

	@Override
	public void add2Path(InterfaceFlowNode currentConnector,
			InterfaceFlowNode nextConnector) {
		Set<Queue<InterfaceFlowNode>> temp = new HashSet<Queue<InterfaceFlowNode>>(Context.getContext().getAllPath());
		Iterator<Queue<InterfaceFlowNode>> it = temp.iterator();
		Queue<InterfaceFlowNode> q;
		while(it.hasNext()) {
			q = it.next();
			if(Tool.matchFinalElementInQueue(q, currentConnector)) {
				Queue<InterfaceFlowNode> q_change = new ConcurrentLinkedQueue<InterfaceFlowNode>(q);
				q_change.add(nextConnector);
				//Touch a passed element
				if(nextConnector instanceof ChoreographyTask && nextConnector.getPassTime() != 0) {
					//this.dealWithLoopPath(q_change, nextConnector);
					//Add the loop path
					Context.getContext().addLoopPath(q_change);
				}
				//A new element
				else {
					Context.getContext().addPath(q_change);
				}	
			}
		}
		//nextConnector.increasePass();
	}
	
	/**
	 * A B C D E B -> C D E B
	 * @param loopQueue
	 * @param lastElement
	 */
	/*private void dealWithLoopPath(Queue<FlowConnectable> loopQueue, FlowConnectable lastElement) {
		Iterator<FlowConnectable> it = loopQueue.iterator();
		FlowConnectable c = null;
		while(it.hasNext()) {
			c = it.next();
			it.remove();
			if(c.equals(lastElement)) {
				break;
			}			
		}
	}*/
}
