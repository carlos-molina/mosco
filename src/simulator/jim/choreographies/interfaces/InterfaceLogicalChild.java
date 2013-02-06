package simulator.jim.choreographies.interfaces;

import generator.jim.beans.ParentInfo;

public interface InterfaceLogicalChild {

	/**
	 * Identify the parent element of this element in BPMN diagram
	 * @param containerInfo
	 */
	public void setContainerInfo(ParentInfo containerInfo);
	
	/**
	 * The parent element of this element, e.g. StartEvent, ExclusiveGateway
	 * @return ContainerInfo
	 */
	public ParentInfo getContainerInfo();
}
