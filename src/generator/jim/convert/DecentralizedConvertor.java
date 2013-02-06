package generator.jim.convert;

import ncl.b1037041.bpmn.choreographies.Context;
import generator.jim.translator.factory.impl.OrchestratedTranslatorFactory;

public class DecentralizedConvertor extends AbstractConvertor {

	DecentralizedConvertor(Context context) {
		super(context, new OrchestratedTranslatorFactory());
	}
}
