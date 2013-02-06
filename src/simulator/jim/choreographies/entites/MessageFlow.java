package simulator.jim.choreographies.entites;

public class MessageFlow {

	private String id;
	private String messageRefId;
	private Message messageRef;
	private String name;
	private String sourceRefId;
	private Participant sourceRef;
	private String targetRefId;
	private Participant targetRef;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Message getMessageRef() {
		return messageRef;
	}
	public void setMessageRef(Message messageRef) {
		this.messageRef = messageRef;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Participant getSourceRef() {
		return sourceRef;
	}
	public void setSourceRef(Participant sourceRef) {
		this.sourceRef = sourceRef;
	}
	public Participant getTargetRef() {
		return targetRef;
	}
	public void setTargetRef(Participant targetRef) {
		this.targetRef = targetRef;
	}
	public String getMessageRefId() {
		return messageRefId;
	}
	public void setMessageRefId(String messageRefId) {
		this.messageRefId = messageRefId;
	}
	public String getSourceRefId() {
		return sourceRefId;
	}
	public void setSourceRefId(String sourceRefId) {
		this.sourceRefId = sourceRefId;
	}
	public String getTargetRefId() {
		return targetRefId;
	}
	public void setTargetRefId(String targetRefId) {
		this.targetRefId = targetRefId;
	}
	
}
