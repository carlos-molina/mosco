package generator.jim.translator.factory.interfaces;

import simulator.jim.choreographies.entites.*;
import generator.jim.translator.command.interfaces.InterfaceTranslator;

public interface InterfaceTranslatorFactory {

	InterfaceTranslator createStartEventTranslator(StartEvent startEvent);
	
	InterfaceTranslator createEndEventTranslator(EndEvent endEvent);
	
	InterfaceTranslator createChoreographyTaskTranslator(ChoreographyTask choreographyTask);
	
	InterfaceTranslator createExclusiveGatewaySplitTranslator(ExclusiveGatewaySplit exclusiveGatewaySplit);
	
	InterfaceTranslator createExclusiveGatewayMergeTranslator(ExclusiveGatewayMerge exclusiveGatewayMerge);
}
