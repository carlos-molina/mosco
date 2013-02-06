package generator.jim.translator.command.interfaces;

import simulator.jim.choreographies.entites.EndEvent;

public abstract class AbstractEndEventTranslator implements InterfaceTranslator {

	private EndEvent endEvent;
	
	
	protected AbstractEndEventTranslator() {}
	
	protected AbstractEndEventTranslator(EndEvent endEvent) {
		this.endEvent = endEvent;
	}

	
	public void setEndEvent(EndEvent endEvent) {
		this.endEvent = endEvent;
	}

	protected EndEvent getEndEvent() {
		if(this.endEvent == null) {
			throw new RuntimeException("endEvent has not been initailized.");
		}
		return this.endEvent;
	}
}
