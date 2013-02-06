package generator.jim.translator.factory.interfaces;

public enum TranslatorEnum {

	StartEventCentralizedTranslator("generator.jim.translator.command.impl.StartEventCentralizedTranslator"),

	StartEventDecentralizedTranslator("generator.jim.translator.command.impl.StartEventDecentralizedTranslator"),
	
	
	EndEventCentralizedTranslator("generator.jim.translator.command.impl.EndEventCentralizedTranslator"),

	EndEventDecentralizedTranslator("generator.jim.translator.command.impl.EndEventDecentralizedTranslator"),
	
	
	ChoreographyTaskCentralizedTranslator("generator.jim.translator.command.impl.ChoreographyTaskCentralizedTranslator"),

	ChoreographyTaskDecentralizedTranslator("generator.jim.translator.command.impl.ChoreographyTaskDecentralizedTranslator"),
	
	
	ExclusiveGatewaySplitCentralizedTranslator("generator.jim.translator.command.impl.ExclusiveGatewaySplitCentralizedTranslator"),

	ExclusiveGatewaySplitDecentralizedTranslator("generator.jim.translator.command.impl.ExclusiveGatewaySplitDecentralizedTranslator"),
	
	
	ExclusiveGatewayMergeCentralizedTranslator("generator.jim.translator.command.impl.ExclusiveGatewayMergeCentralizedTranslator"),

	ExclusiveGatewayMergeDecentralizedTranslator("generator.jim.translator.command.impl.ExclusiveGatewayMergeDecentralizedTranslator");

	private String value = "";

	
	private TranslatorEnum(String value) {
		this.value = value;
	}

	
	public String getValue() {
		return this.value;
	}
}