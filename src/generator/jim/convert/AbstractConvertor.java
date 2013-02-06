package generator.jim.convert;

import java.util.*;

import ncl.b1037041.bpmn.choreographies.Context;

import generator.jim.entites.BlockProctype;
import generator.jim.entites.BlockRoot;
import generator.jim.tools.Configuration;
import generator.jim.translator.factory.interfaces.InterfaceTranslatorFactory;

public abstract class AbstractConvertor implements InterfaceConvertor {
	
	public static final String TRANSLATOR_TYPE_DECENTRALIZED = "orchestrated";
	public static final String TRANSLATOR_TYPE_CENTRALIZED = "choreographed";
	
	//public static InterfaceTranslatorGenerator generator;
	public static InterfaceConvertor convertor;

	protected BlockRoot root;
	protected Context context;
	private InterfaceTranslatorFactory factory;
	
	protected AbstractConvertor(Context context, InterfaceTranslatorFactory factory) {
		this.context = context;
		this.root = new BlockRoot();
		this.factory = factory;
	}
	
	public static InterfaceConvertor getConvertor(Context context) {
		if(context == null) {
			throw new IllegalArgumentException("Paramter is invalid.");
		}

		if(TRANSLATOR_TYPE_DECENTRALIZED.equals(Configuration.getInstance().getValue("tranlator.type"))) {
			convertor = new DecentralizedConvertor(context);
		}
		else if(TRANSLATOR_TYPE_CENTRALIZED.equals(Configuration.getInstance().getValue("tranlator.type"))) {
			convertor = new CentralizedConvertor(context);
		}
		else {
			throw new IllegalArgumentException("Wrong type of convertor.");
		}

		return convertor;
	}
	
	public static InterfaceConvertor getInitializedConvertor() {
		if(convertor == null) {
			throw new IllegalArgumentException("Convertor has not been initialized.");
		}
		return convertor;
	}

	@Override
	public InterfaceTranslatorFactory getTranslatorFactory() {
		return this.factory;
	}
	
	@Override
	public Context getContext() {
		return this.context;
	}

	@Override
	public BlockRoot getRoot() {
		return this.root;
	}
	
	@Override
	public String convert() {
	
		//Translate each element into promela section
		this.context.getStartEvent().accept(getInitializedConvertor().getTranslatorFactory()).translate();

		//Some blank or one branch if should be removed
		this.removeEmptyNode();
		
		//Turn the tree like structure into promela code
		return this.root.construct();
	}
	
	private void removeEmptyNode() {
		Collection<BlockProctype> proctypes = StoreDecentralized.getStoreDecentralized().getAllProctypes();
		Iterator<BlockProctype> it = proctypes.iterator();
		BlockProctype p = null;
		while(it.hasNext()) {
			p = it.next();
			p.removeEmptyIf();
		}
	}
}
