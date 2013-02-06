package generator.jim.translator.visitor.interfaces;

import generator.jim.translator.command.interfaces.InterfaceTranslator;
import generator.jim.translator.factory.interfaces.InterfaceTranslatorFactory;

public interface InterfaceTranslatable {

	public InterfaceTranslator accept(InterfaceTranslatorFactory visitor);
}
