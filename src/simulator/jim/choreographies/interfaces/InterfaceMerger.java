package simulator.jim.choreographies.interfaces;

import java.util.Set;

import simulator.jim.choreographies.entites.SequenceFlow;

/**
 * A Merger is the element with more (can be one) incoming flows and exactly one outgoing flow
 * 
 * @author b1037041 Jim
 */
public interface InterfaceMerger {

	public Set<SequenceFlow> getIncomings();
	
	public SequenceFlow getOutgoing();
}
