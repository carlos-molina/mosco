package simulator.jim.path;

import simulator.jim.choreographies.interfaces.InterfaceFlowNode;

public interface AddPathAction {

	public void add2Path(InterfaceFlowNode currentConnector, InterfaceFlowNode nextConnector);
}
