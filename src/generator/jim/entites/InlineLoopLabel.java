package generator.jim.entites;

import generator.jim.composite.AbstractInline;

public class InlineLoopLabel extends AbstractInline {
	
	public static final String SENDING = "Sending";
	public static final String RECEIVING = "Receiving";
	
	//Message name
	private String lableName;
	//Sending or receiving
	private String type;

	private InlineLoopLabel(String lableName, String type) {
		this.lableName = lableName;
		this.type = type;
	}
	
	public static InlineLoopLabel getLoopLable(String lableName, String type) {
		if(lableName == null || type == null) {
			throw new IllegalArgumentException();
		}
		return new InlineLoopLabel(lableName, type);
	}

	public String getLableFullName() {
		return this.lableName + this.type;
	}
	
	@Override
	public String construct() {
		return this.getLableFullName() + ":" + super.construct();
	}
}
