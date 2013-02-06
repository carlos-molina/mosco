package generator.jim.entites;

import generator.jim.composite.AbstractInline;

public class InlineSkip extends AbstractInline {

	@Override
	public String construct() {
		return "true -> skip;" + super.construct();
	}
}
