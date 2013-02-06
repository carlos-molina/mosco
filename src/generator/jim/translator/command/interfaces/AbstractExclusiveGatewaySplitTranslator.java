package generator.jim.translator.command.interfaces;

import simulator.jim.choreographies.entites.ExclusiveGatewaySplit;

public abstract class AbstractExclusiveGatewaySplitTranslator implements InterfaceTranslator {

	private ExclusiveGatewaySplit exclusiveGatewaySplit;

	
	protected AbstractExclusiveGatewaySplitTranslator() {}

	protected AbstractExclusiveGatewaySplitTranslator(ExclusiveGatewaySplit exclusiveGatewaySplit) {
		this.exclusiveGatewaySplit = exclusiveGatewaySplit;
	}

	
	public void setExclusiveGatewaySplit(ExclusiveGatewaySplit exclusiveGatewaySplit) {
		this.exclusiveGatewaySplit = exclusiveGatewaySplit;
	}

	protected ExclusiveGatewaySplit getExclusiveGatewaySplit() {
		if(this.exclusiveGatewaySplit == null) {
			throw new RuntimeException("exclusiveGatewaySplit has not been initailized.");
		}
		return this.exclusiveGatewaySplit;
	}
}
