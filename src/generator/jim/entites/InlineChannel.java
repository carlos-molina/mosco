package generator.jim.entites;

import java.util.*;

import generator.jim.composite.AbstractInline;
import generator.jim.meta.MataData;
import generator.jim.tools.Configuration;

public class InlineChannel extends AbstractInline {
	
	private static String KRY_WORD = MataData.DATA_TYPE_CHAN;
	private static String JOIN = "2";
	private static String MESSAGE_TYPE = Configuration.getInstance().getValue("message.type");
	private static String BUFFER_SIZE = Configuration.getInstance().getValue("buffer.size");
	private static Map<String, InlineChannel> channelMap = new HashMap<String, InlineChannel>();
	
	private String variableName;
	
	private String from;
	private String to;
	
	private InlineChannel(String from, String to) {
		this.from = from;
		this.to = to;
		this.variableName = this.from + JOIN + this.to;
	}
	
	public static InlineChannel getChannel(String from, String to) {
		String vName = from + JOIN + to;
		if(channelMap.get(vName) != null) {
			return channelMap.get(vName);
		}

		InlineChannel c = new InlineChannel(from, to);
		channelMap.put(vName, c);
		return c;
	}

	public String getVariableName() {
		return variableName;
	}

	@Override
	public String construct() {
		return KRY_WORD + " " + variableName + " = [" + BUFFER_SIZE + "] of {" + InlineMtype.VARIABLE+ ", "+ MESSAGE_TYPE +"};" + super.construct();
	}
}
