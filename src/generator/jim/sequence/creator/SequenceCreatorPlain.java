package generator.jim.sequence.creator;

import java.util.Iterator;
import java.util.Queue;

import ncl.b1037041.bpmn.choreographies.Context;

import simulator.jim.choreographies.entites.ChoreographyTask;
import simulator.jim.choreographies.interfaces.InterfaceFlowNode;

public class SequenceCreatorPlain extends AbstractSequenceCreator {

	public SequenceCreatorPlain(Context context) {
		super(context);
	}

	@Override
	public String create() {
		StringBuffer content = new StringBuffer("");
		
		Iterator<Queue<InterfaceFlowNode>> it_valid = super.getContext().getValidPath().iterator();
		Queue<InterfaceFlowNode> queue = null;
		content.append("normal sequence size: " + super.getContext().getValidPath().size()).append("\n");
		while(it_valid.hasNext()) {
			StringBuffer sb = new StringBuffer("");			
			queue = it_valid.next();
			Iterator<InterfaceFlowNode> it_element = queue.iterator();
			InterfaceFlowNode node = null;
			ChoreographyTask task = null;
			while(it_element.hasNext()) {
				node = it_element.next();
				if(node instanceof ChoreographyTask) {
					task = (ChoreographyTask)node;
					sb.append(task.getMessageFlowRef().getMessageRef().getName()).append(",");
				}
			}
			content.append(sb.toString().substring(0, sb.toString().length()-1)).append("\n");
		}
		
		Iterator<Queue<InterfaceFlowNode>> it_loop = super.getContext().getLoopPath().iterator();
		content.append("loop sequence size: " + super.getContext().getLoopPath().size()).append("\n");
		while(it_loop.hasNext()) {
			StringBuffer sb = new StringBuffer("");			
			queue = it_loop.next();
			Iterator<InterfaceFlowNode> it_element = queue.iterator();
			InterfaceFlowNode node = null;
			ChoreographyTask task = null;
			while(it_element.hasNext()) {
				node = it_element.next();
				if(node instanceof ChoreographyTask) {
					task = (ChoreographyTask)node;
					sb.append(task.getMessageFlowRef().getMessageRef().getName()).append(",");
				}
			}
			content.append(sb.toString().substring(0, sb.toString().length()-1)).append("\n");
		}
		
		return content.toString();
	}

}
