package generator.jim.convert;

import ncl.b1037041.bpmn.choreographies.Context;
import generator.jim.translator.factory.impl.ChoreographedTranslatorFactory;

public class CentralizedConvertor extends AbstractConvertor {

	CentralizedConvertor(Context context) {
		super(context, new ChoreographedTranslatorFactory());	
	}
}
