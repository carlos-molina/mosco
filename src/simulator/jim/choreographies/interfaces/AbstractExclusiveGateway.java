package simulator.jim.choreographies.interfaces;

import simulator.jim.choreographies.entites.*;
import simulator.jim.choreographies.interfaces.AbstractGateway;

public abstract class AbstractExclusiveGateway extends AbstractGateway {
	
	public static final String EXCLUSIVE_GATEWAY_SPLIT = "ExclusiveGatewaySplit";
	public static final String EXCLUSIVE_GATEWAY_MERGE = "ExclusiveGatewayMerge";
		
	public static AbstractExclusiveGateway getExclusiveGateway(String type) {
		if("ExclusiveGatewaySplit".equals(type)) {
			return new ExclusiveGatewaySplit();
		}
		else if("ExclusiveGatewayMerge".equals(type)) {
			return new ExclusiveGatewayMerge();
		}
		else {
			throw new IllegalArgumentException("Wrong type of exclusive gateway.");
		}
	}
}