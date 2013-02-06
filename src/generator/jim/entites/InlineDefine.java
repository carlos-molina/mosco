package generator.jim.entites;

import generator.jim.composite.AbstractInline;

public class InlineDefine extends AbstractInline {

	private static final String DEFINE = "#define";
	
	private String varable;
	
	public InlineDefine(String varable) {
		this.varable = varable;
	}
	
	@Override
	public String construct() {
		return DEFINE + " " + this.varable + " " + "(true)" + super.construct();
	}
}
