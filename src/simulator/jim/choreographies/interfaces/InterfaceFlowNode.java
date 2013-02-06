package simulator.jim.choreographies.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import generator.jim.translator.visitor.interfaces.InterfaceTranslatable;
import simulator.jim.choreographies.entites.SequenceFlow;

/**
 * @author Jim
 *
 * A type which can be connected by sequence flow.
 * So far, StartEvent, EndEvent, ChoreographyTask and ExclusiveGateway.
 */
public interface InterfaceFlowNode extends InterfaceTranslatable {
	
	/**
	 * Return the id
	 * @return Id of the element
	 */
	public String getId();
	
	/**
	 * Set the id of the element
	 * @param id
	 */
	public void setId(String id);

	/**
	 * From this node to next node.
	 */
	public void goThrough();
	
	/**
	 * Add itself to the path set.
	 */
	public void add2Path(InterfaceFlowNode nextConnector);
	
	/**
	 * Add incoming sequence flow
	 * @param incoming
	 */
	public void addIncoming(SequenceFlow incoming);
	
	/**
	 * Add outgoing sequence flow
	 * @param outgoing
	 */
	public void addOutgoing(SequenceFlow outgoing);
	
	/**
	 * The number of passing
	 */
	public int getPassTime();
	
	/**
	 * Add the passing number
	 */
	public void increasePass();

	/**
	 * Identify the parent and path index for each element
	 * @param pathList
	 * @param symbolStack
	 * @param m
	 */
	public void handleElementInEachPath(List<InterfaceFlowNode> pathList, Stack<InterfaceLogicalParent> symbolStack, Map<InterfaceLogicalParent, Boolean> m);
}
