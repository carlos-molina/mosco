package simulator.jim.choreographies.interfaces;

import java.util.Set;

import simulator.jim.choreographies.entites.SequenceFlow;

/**
 * A Merger is the element with more (can be one) outgoing flows and exactly one incoming flow
 * 
 * @author b1037041 Jim
 */
public interface InterfaceSplitter {

	public SequenceFlow getIncoming();
	
	public Set<SequenceFlow> getOutgoings();
}
