package simulator.jim.tools;

import java.util.*;

import simulator.jim.choreographies.entites.EndEvent;
import simulator.jim.choreographies.interfaces.InterfaceFlowNode;

public class Tool {
	
	/**
	 * Check the given object, see whether it is the last element of the queue
	 * 
	 * @param q Element Queue
	 * @param o The object need to be verified
	 * @return Match or not
	 */
	public static boolean matchFinalElementInQueue(Queue<InterfaceFlowNode> q, Object o) {
		if(q == null || q.isEmpty()) {
			return false;
		}
		Object[] oArr = q.toArray();
		if(oArr[oArr.length-1].equals(o)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Check the given object, see whether it is the last element of the List
	 * 
	 * @param q Element List
	 * @param o The object need to be verified
	 * @return Match or not
	 */
	public static boolean matchFinalElementInList(List<InterfaceFlowNode> q, Object o) {
		if(q == null || q.isEmpty()) {
			return false;
		}
		Object[] oArr = q.toArray();
		if(oArr[oArr.length-1].equals(o)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Check the type of last element, see whether it is a EndEvent
	 * 
	 * @param q Element Queue
	 * @return Match or not
	 */
	public static boolean isLastElementEndEvent(Queue<InterfaceFlowNode> q) {
		if(q == null || q.isEmpty()) {
			return false;
		}
		Object[] oArr = q.toArray();
		if(oArr[oArr.length-1] instanceof EndEvent) {
			return true;
		}
		else {
			return false;
		}
	}
}
