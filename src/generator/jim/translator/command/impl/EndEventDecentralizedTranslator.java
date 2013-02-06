package generator.jim.translator.command.impl;

import generator.jim.translator.command.interfaces.AbstractEndEventTranslator;
import simulator.jim.choreographies.entites.EndEvent;

public class EndEventDecentralizedTranslator extends AbstractEndEventTranslator {

	public EndEventDecentralizedTranslator() {}

	public EndEventDecentralizedTranslator(EndEvent endEvent) {
		super(endEvent);
	}

	
	@Override
	public void translate() {
		return;
	}
}
