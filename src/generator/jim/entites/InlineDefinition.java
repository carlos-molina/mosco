package generator.jim.entites;

import generator.jim.composite.AbstractInline;

public class InlineDefinition extends AbstractInline {
	
	private String definition;

	public InlineDefinition(String definition) {
		this.definition = definition;
	}
	
	@Override
	public String construct() {		
		return this.definition + super.construct();
	}
}
