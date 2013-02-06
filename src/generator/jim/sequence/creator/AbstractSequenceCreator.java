package generator.jim.sequence.creator;

import ncl.b1037041.bpmn.choreographies.Context;

public abstract class AbstractSequenceCreator implements InterfaceSequenceCreator {
	
	private static final String GENERATION_SEQUENCES_FORMAT_PLAIN = "plain";
	private static final String GENERATION_SEQUENCES_FORMAT_XML = "xml";
	
	private Context context;

	protected AbstractSequenceCreator(Context context) {
		this.context = context;
	}
	
	protected Context getContext() {
		return this.context;
	}
	
	public static InterfaceSequenceCreator getSequenceCreator(Context context, String type) {
		if(GENERATION_SEQUENCES_FORMAT_PLAIN.equals(type)) {
			return new SequenceCreatorPlain(context);
		}
		else if(GENERATION_SEQUENCES_FORMAT_XML.equals(type)) {
			return new SequenceCreatorXML(context);
		}
		else {
			throw new IllegalArgumentException("Wrong type of sequence creator.");
		}
	}
}
