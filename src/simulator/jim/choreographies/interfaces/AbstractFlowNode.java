package simulator.jim.choreographies.interfaces;

import generator.jim.translator.command.interfaces.InterfaceTranslator;
import simulator.jim.path.AddPathAction;

public abstract class AbstractFlowNode implements InterfaceFlowNode {
	
	private String id;

	private AddPathAction addPathAction;
	private int passTime;

	protected InterfaceTranslator translator;

	protected AbstractFlowNode(AddPathAction addPathAction) {
		this.addPathAction = addPathAction;
		this.passTime = 0;
	}
	
	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public int getPassTime() {
		return this.passTime;
	}
	
	@Override
	public void increasePass() {
		this.passTime++;
	}
	
	@Override
	public void add2Path(InterfaceFlowNode nextConnector) {
		this.addPathAction.add2Path(this, nextConnector);
	}

	@Override
	public String toString() {
		return this.id;
	}
}
