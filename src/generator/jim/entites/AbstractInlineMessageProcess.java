package generator.jim.entites;

import ncl.b1037041.LTL.entites.ChoreographyMessageInfo;
import generator.jim.composite.AbstractInline;

public abstract class AbstractInlineMessageProcess extends AbstractInline {
	
	private InlineChannel channel;
	private ChoreographyMessageInfo messageInfo;
	
	protected AbstractInlineMessageProcess(InlineChannel channel, ChoreographyMessageInfo messageInfo) {
		this.channel = channel;
		this.messageInfo = messageInfo;
	}

	protected final String handleMessage() {
		return this.channel.getVariableName() + this.handleType() + this.messageInfo.getMessage() + this.parameter() + ";";
	}
	
	@Override
	public String construct() {
		return this.handleMessage() + super.construct();
	}
	
	protected abstract String handleType();
	
	protected abstract String parameter();
}
