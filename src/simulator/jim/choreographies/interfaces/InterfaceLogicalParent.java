package simulator.jim.choreographies.interfaces;

/**
 * Element can contain other elements, e.g. StartEvent, ExclusiveGatewaySplit
 * 
 * @author b1037041 Jim
 */
public interface InterfaceLogicalParent {

	public void setNextPathIndex();

	public void minusPathIndex();
	
	public int getPathIndex();
}
