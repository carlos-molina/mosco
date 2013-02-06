package ncl.b1037041.LTL.entites;

public class ChoreographyMessageInfo {

	private int id;
	private BPMNChoreography bpmn;
	private String message;
	private String ltlSymbol;
	private String boolMessage;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BPMNChoreography getBpmn() {
		return bpmn;
	}
	public void setBpmn(BPMNChoreography bpmn) {
		this.bpmn = bpmn;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLtlSymbol() {
		return ltlSymbol;
	}
	public void setLtlSymbol(String ltlSymbol) {
		this.ltlSymbol = ltlSymbol;
	}
	public String getBoolMessage() {
		return boolMessage;
	}
	public void setBoolMessage(String boolMessage) {
		this.boolMessage = boolMessage;
	}
	
	@Override
	public String toString() {
		return this.message;
	}	
}
