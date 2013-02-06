package generator.jim.entites;

import generator.jim.composite.AbstractInline;

public class InlineRun extends AbstractInline {
	
	private static final String RUN = "run";
	
	private String processName;

	public InlineRun(String processName) {
		this.processName = processName;
	}

	@Override
	public String construct() {
		return RUN + " " + this.processName + "();" + super.construct();
	}
}
