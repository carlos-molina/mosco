package generator.jim.translator.command.interfaces;

import generator.jim.convert.AbstractConvertor;
import generator.jim.entites.InlineDefine;
import generator.jim.ltl.creator.InterfaceLTLVariableCreator;
import generator.jim.ltl.creator.ImplLTLVariableCreator;

import java.util.Iterator;
import java.util.List;

import simulator.jim.choreographies.entites.StartEvent;

public abstract class AbstractStartEventTranslator implements InterfaceTranslator {

	private StartEvent startEvent;
	
	
	protected AbstractStartEventTranslator() {}
	
	protected AbstractStartEventTranslator(StartEvent startEvent) {
		this.startEvent = startEvent;
	}

	
	public void setStartEvent(StartEvent startEvent) {
		this.startEvent = startEvent;
	}

	protected StartEvent getStartEvent() {
		if(this.startEvent == null) {
			throw new RuntimeException("startEvent has not been initailized.");
		}
		return this.startEvent;
	}
	
	protected void initLTLVariables() {
		InterfaceLTLVariableCreator varCreator = new ImplLTLVariableCreator();
		List<String> vars = varCreator.create();
		Iterator<String> it = vars.iterator();
		String var = null;
		while(it.hasNext()) {
			var = it.next();
			InlineDefine define = new InlineDefine(var);
			AbstractConvertor.getInitializedConvertor().getRoot().getDefineBlock().addChild(define);
		}
	}
}
