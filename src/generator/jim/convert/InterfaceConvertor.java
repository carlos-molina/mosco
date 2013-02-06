package generator.jim.convert;

import ncl.b1037041.bpmn.choreographies.Context;
import generator.jim.entites.BlockRoot;
import generator.jim.translator.factory.interfaces.InterfaceTranslatorFactory;

/**
 * Conversion tool which converts context of choreography into promela code
 * 
 * @author b1037041
 */
public interface InterfaceConvertor {
	
	/**
	 * Get Root element of the content(the empty file)
	 * @return
	 */
	BlockRoot getRoot();
	
	/**
	 * Get the context which to be converted
	 * @return
	 */
	Context getContext();
	
	/**
	 * Get translator factory for certain convertor
	 * @return
	 */
	InterfaceTranslatorFactory getTranslatorFactory();

	/**
	 * Convert the context into a string(the promela code)
	 * @return
	 */
	String convert();
}
