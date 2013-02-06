package generator.jim.translator.command.impl;

import simulator.jim.choreographies.entites.EndEvent;
import generator.jim.translator.command.interfaces.AbstractEndEventTranslator;

public class EndEventCentralizedTranslator extends AbstractEndEventTranslator {

	public EndEventCentralizedTranslator() {}

	public EndEventCentralizedTranslator(EndEvent endEvent) {
		super(endEvent);
	}

	
	@Override
	public void translate() {
		return;
	}
}
