package generator.jim.translator.factory.impl;

import simulator.jim.choreographies.entites.*;
import generator.jim.translator.command.impl.*;
import generator.jim.translator.command.interfaces.*;
import generator.jim.translator.factory.interfaces.*;

public class OrchestratedTranslatorFactory extends AbstractTranslatorFactory {

	@Override
	public InterfaceTranslator createStartEventTranslator(StartEvent startEvent) {
		if(this.translatorMap.get(startEvent) == null) {
			StartEventDecentralizedTranslator translator = (StartEventDecentralizedTranslator)super.createTranslator(TranslatorEnum.StartEventDecentralizedTranslator);
			translator.setStartEvent(startEvent);
			this.translatorMap.put(startEvent, translator);
		}		
		return this.translatorMap.get(startEvent);
	}

	@Override
	public InterfaceTranslator createEndEventTranslator(EndEvent endEvent) {
		if(this.translatorMap.get(endEvent) == null) {
			EndEventDecentralizedTranslator translator = (EndEventDecentralizedTranslator)super.createTranslator(TranslatorEnum.EndEventDecentralizedTranslator);
			translator.setEndEvent(endEvent);
			this.translatorMap.put(endEvent, translator);
		}		
		return this.translatorMap.get(endEvent);
	}

	@Override
	public InterfaceTranslator createChoreographyTaskTranslator(
			ChoreographyTask choreographyTask) {
		if(this.translatorMap.get(choreographyTask) == null) {
			ChoreographyTaskDecentralizedTranslator translator = (ChoreographyTaskDecentralizedTranslator)super.createTranslator(TranslatorEnum.ChoreographyTaskDecentralizedTranslator);
			translator.setChoreographyTask(choreographyTask);
			this.translatorMap.put(choreographyTask, translator);
		}		
		return this.translatorMap.get(choreographyTask);
	}

	@Override
	public InterfaceTranslator createExclusiveGatewaySplitTranslator(
			ExclusiveGatewaySplit exclusiveGatewaySplit) {
		if(this.translatorMap.get(exclusiveGatewaySplit) == null) {
			ExclusiveGatewaySplitDecentralizedTranslator translator = (ExclusiveGatewaySplitDecentralizedTranslator)super.createTranslator(TranslatorEnum.ExclusiveGatewaySplitDecentralizedTranslator);
			translator.setExclusiveGatewaySplit(exclusiveGatewaySplit);
			this.translatorMap.put(exclusiveGatewaySplit, translator);
		}		
		return this.translatorMap.get(exclusiveGatewaySplit);
	}

	@Override
	public InterfaceTranslator createExclusiveGatewayMergeTranslator(
			ExclusiveGatewayMerge exclusiveGatewayMerge) {
		if(this.translatorMap.get(exclusiveGatewayMerge) == null) {
			ExclusiveGatewayMergeDecentralizedTranslator translator = (ExclusiveGatewayMergeDecentralizedTranslator)super.createTranslator(TranslatorEnum.ExclusiveGatewayMergeDecentralizedTranslator);
			translator.setExclusiveGatewayMerge(exclusiveGatewayMerge);
			this.translatorMap.put(exclusiveGatewayMerge, translator);
		}
		return this.translatorMap.get(exclusiveGatewayMerge);
	}
}
