package generator.jim.sequence.creator;

import java.util.Iterator;
import java.util.Queue;

import ncl.b1037041.bpmn.choreographies.Context;

import generator.jim.tools.XMLTool;
import simulator.jim.choreographies.entites.ChoreographyTask;
import simulator.jim.choreographies.interfaces.InterfaceFlowNode;

public class SequenceCreatorXML extends AbstractSequenceCreator {

	public SequenceCreatorXML(Context context) {
		super(context);
	}

	@Override
	public String create() {
		XMLTool xml = new XMLTool();
		xml.appendString("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").enter();
		
		xml.appendString("<sequences>").enter();
		Iterator<Queue<InterfaceFlowNode>> it_valid = super.getContext().getValidPath().iterator();
		Queue<InterfaceFlowNode> queue = null;
		while(it_valid.hasNext()) {
			xml.tab().appendString("<sequence type=\"normal\">").enter();
			xml.tab(2).appendString("<message_queue>").enter();
			queue = it_valid.next();
			Iterator<InterfaceFlowNode> it_element = queue.iterator();
			InterfaceFlowNode node = null;
			ChoreographyTask task = null;
			while(it_element.hasNext()) {
				node = it_element.next();
				if(node instanceof ChoreographyTask) {
					task = (ChoreographyTask)node;
					xml.tab(3).appendString("<message=\"" + task.getMessageFlowRef().getMessageRef().getName() + "\"/>").enter();
				}
			}
			xml.tab(2).appendString("</message_queue>").enter();
			xml.tab().appendString("</sequence>").enter();
		}
		
		Iterator<Queue<InterfaceFlowNode>> it_loop = super.getContext().getLoopPath().iterator();
		while(it_loop.hasNext()) {
			xml.tab().appendString("<sequence type=\"loop\">").enter();
			xml.tab(2).appendString("<message_queue>").enter();
			queue = it_loop.next();
			Iterator<InterfaceFlowNode> it_element = queue.iterator();
			InterfaceFlowNode node = null;
			ChoreographyTask task = null;
			while(it_element.hasNext()) {
				node = it_element.next();
				if(node instanceof ChoreographyTask) {
					task = (ChoreographyTask)node;
					xml.tab(3).appendString("<message=\"" + task.getMessageFlowRef().getMessageRef().getName() + "\"/>").enter();
				}
			}
			xml.tab(2).appendString("</message_queue>").enter();
			xml.tab().appendString("</sequence>").enter();
		}
				
		xml.appendString("</sequences>").enter();
		return xml.toString();
	}

}
