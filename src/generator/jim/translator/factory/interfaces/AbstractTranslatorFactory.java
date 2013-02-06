package generator.jim.translator.factory.interfaces;

import java.util.HashMap;
import java.util.Map;

import simulator.jim.choreographies.interfaces.InterfaceFlowNode;
import generator.jim.translator.command.interfaces.InterfaceTranslator;

public abstract class AbstractTranslatorFactory implements InterfaceTranslatorFactory {
	
	protected final Map<InterfaceFlowNode, InterfaceTranslator> translatorMap = new HashMap<InterfaceFlowNode, InterfaceTranslator>();

	protected InterfaceTranslator createTranslator(TranslatorEnum translatorEnum) {
		InterfaceTranslator translator = null;
		if (!translatorEnum.getValue().equals("")) {
			try {
				translator = (InterfaceTranslator)(Class.forName(translatorEnum.getValue()).newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return translator;
	}
}
