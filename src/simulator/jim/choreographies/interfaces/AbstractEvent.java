package simulator.jim.choreographies.interfaces;

import simulator.jim.path.AddPathAction;

public abstract class AbstractEvent extends AbstractFlowNode {

	private String name;
	
	public AbstractEvent(AddPathAction addPathAction) {
		super(addPathAction);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
