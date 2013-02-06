package generator.jim.entites;

import ncl.b1037041.LTL.entites.ChoreographyMessageInfo;

public class InlineMessageSending extends AbstractInlineMessageProcess {
	
	private static final String SYMBOL_SENDING = "!";
	private static final String SENDING_PARAMRTER = "1";
	
	protected InlineMessageReceiving correspondingMessageReceiving;

	public InlineMessageSending(InlineChannel channel, ChoreographyMessageInfo messageInfo) {
		super(channel, messageInfo);
		this.correspondingMessageReceiving = new InlineMessageReceiving(channel, messageInfo);
	}

	@Override
	protected String handleType() {
		return " " + SYMBOL_SENDING + " ";
	}
	
	@Override
	protected String parameter() {
		return "(" + SENDING_PARAMRTER + ")";
	}
	
	public InlineMessageReceiving getCorrespondingMessageReceiving() {
		return this.correspondingMessageReceiving;
	}
}
