package ncl.b1037041.LTL.entites;

public class LTLInstance {

	private int id;
	private BPMNChoreography bpmn;
	private LTLDefinition definition;
	private String specificFormula;
	private String specificDescription;
	private int isSetup;
	
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
	public LTLDefinition getDefinition() {
		return definition;
	}
	public void setDefinition(LTLDefinition definition) {
		this.definition = definition;
	}
	public String getSpecificFormula() {
		return specificFormula;
	}
	public void setSpecificFormula(String specificFormula) {
		this.specificFormula = specificFormula;
	}
	public String getSpecificDescription() {
		return specificDescription;
	}
	public void setSpecificDescription(String specificDescription) {
		this.specificDescription = specificDescription;
	}
	public int getIsSetup() {
		return isSetup;
	}
	public void setIsSetup(int isSetup) {
		this.isSetup = isSetup;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LTLInstance other = (LTLInstance) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
