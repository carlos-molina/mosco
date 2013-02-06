package generator.jim.translator.factory.impl;

import simulator.jim.choreographies.entites.*;
import generator.jim.translator.command.impl.*;
import generator.jim.translator.command.interfaces.*;
import generator.jim.translator.factory.interfaces.*;

public class ChoreographedTranslatorFactory extends AbstractTranslatorFactory {

	@Override
	public InterfaceTranslator createStartEventTranslator(StartEvent startEvent) {
		if(this.translatorMap.get(startEvent) == null) {
			StartEventCentralizedTranslator translator = (StartEventCentralizedTranslator)super.createTranslator(TranslatorEnum.StartEventCentralizedTranslator);
			translator.setStartEvent(startEvent);
			this.translatorMap.put(startEvent, translator);
		}		
		return this.translatorMap.get(startEvent);
	}

	@Override
	public InterfaceTranslator createEndEventTranslator(EndEvent endEvent) {
		if(this.translatorMap.get(endEvent) == null) {
			EndEventCentralizedTranslator translator = (EndEventCentralizedTranslator)super.createTranslator(TranslatorEnum.EndEventCentralizedTranslator);
			translator.setEndEvent(endEvent);
			this.translatorMap.put(endEvent, translator);
		}		
		return this.translatorMap.get(endEvent);
	}

	@Override
	public InterfaceTranslator createChoreographyTaskTranslator(
			ChoreographyTask choreographyTask) {
		if(this.translatorMap.get(choreographyTask) == null) {
			ChoreographyTaskCentralizedTranslator translator = (ChoreographyTaskCentralizedTranslator)super.createTranslator(TranslatorEnum.ChoreographyTaskCentralizedTranslator);
			translator.setChoreographyTask(choreographyTask);
			this.translatorMap.put(choreographyTask, translator);
		}		
		return this.translatorMap.get(choreographyTask);
	}

	@Override
	public InterfaceTranslator createExclusiveGatewaySplitTranslator(
			ExclusiveGatewaySplit exclusiveGatewaySplit) {
		if(this.translatorMap.get(exclusiveGatewaySplit) == null) {
			ExclusiveGatewaySplitCentralizedTranslator translator = (ExclusiveGatewaySplitCentralizedTranslator)super.createTranslator(TranslatorEnum.ExclusiveGatewaySplitCentralizedTranslator);
			translator.setExclusiveGatewaySplit(exclusiveGatewaySplit);
			this.translatorMap.put(exclusiveGatewaySplit, translator);
		}		
		return this.translatorMap.get(exclusiveGatewaySplit);
	}

	@Override
	public InterfaceTranslator createExclusiveGatewayMergeTranslator(
			ExclusiveGatewayMerge exclusiveGatewayMerge) {
		if(this.translatorMap.get(exclusiveGatewayMerge) == null) {
			ExclusiveGatewayMergeCentralizedTranslator translator = (ExclusiveGatewayMergeCentralizedTranslator)super.createTranslator(TranslatorEnum.ExclusiveGatewayMergeCentralizedTranslator);
			translator.setExclusiveGatewayMerge(exclusiveGatewayMerge);
			this.translatorMap.put(exclusiveGatewayMerge, translator);
		}
		return this.translatorMap.get(exclusiveGatewayMerge);
	}
}
