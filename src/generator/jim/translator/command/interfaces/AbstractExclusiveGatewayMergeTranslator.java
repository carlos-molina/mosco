package generator.jim.translator.command.interfaces;

import simulator.jim.choreographies.entites.ExclusiveGatewayMerge;

public abstract class AbstractExclusiveGatewayMergeTranslator implements InterfaceTranslator {

	private ExclusiveGatewayMerge exclusiveGatewayMerge;
	protected boolean translated = false;
	
	protected AbstractExclusiveGatewayMergeTranslator() {}

	protected AbstractExclusiveGatewayMergeTranslator(ExclusiveGatewayMerge exclusiveGatewayMerge) {
		this.exclusiveGatewayMerge = exclusiveGatewayMerge;
	}

	
	public void setExclusiveGatewayMerge(ExclusiveGatewayMerge exclusiveGatewayMerge) {
		this.exclusiveGatewayMerge = exclusiveGatewayMerge;
	}

	protected ExclusiveGatewayMerge getExclusiveGatewayMerge() {
		if(this.exclusiveGatewayMerge == null) {
			throw new RuntimeException("exclusiveGatewayMerge has not been initailized.");
		}
		return this.exclusiveGatewayMerge;
	}
}
