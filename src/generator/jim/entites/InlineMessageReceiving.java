package generator.jim.entites;

import ncl.b1037041.LTL.entites.ChoreographyMessageInfo;

public class InlineMessageReceiving extends AbstractInlineMessageProcess {
	
	private static final String SYMBOL_RECEIVING = "?";
	private static final String RECEIVING_PARAMRTER = "_";
	
	private ChoreographyMessageInfo messageInfo;

	public InlineMessageReceiving(InlineChannel channel, ChoreographyMessageInfo messageInfo) {
		super(channel, messageInfo);
		this.messageInfo = messageInfo;
	}

	@Override
	protected String handleType() {
		return " " + SYMBOL_RECEIVING + " ";
	}

	@Override
	protected String parameter() {
		return "(" + RECEIVING_PARAMRTER + "); " + messageInfo.getBoolMessage() + " = TRUE";
	}
}
