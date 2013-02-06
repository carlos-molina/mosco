package simulator.jim.choreographies.entites;

import simulator.jim.choreographies.interfaces.InterfaceFlowNode;

public class SequenceFlow {

	private String id;
	private String name;
	private String sourceRefId;
	private InterfaceFlowNode sourceRef;
	private String targetRefId;
	private InterfaceFlowNode targetRef;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public InterfaceFlowNode getSourceRef() {
		return sourceRef;
	}
	public void setSourceRef(InterfaceFlowNode sourceRef) {
		this.sourceRef = sourceRef;
	}
	public InterfaceFlowNode getTargetRef() {
		return targetRef;
	}
	public void setTargetRef(InterfaceFlowNode targetRef) {
		this.targetRef = targetRef;
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
