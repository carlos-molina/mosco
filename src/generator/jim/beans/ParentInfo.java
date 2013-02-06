package generator.jim.beans;

import simulator.jim.choreographies.interfaces.InterfaceLogicalParent;

public class ParentInfo {

	private InterfaceLogicalParent container;
	private int pathIndex;
	
	
	public ParentInfo(InterfaceLogicalParent container, int pathIndex) {
		this.container = container;
		this.pathIndex = pathIndex;
	}
	
	
	public InterfaceLogicalParent getContainer() {
		return this.container;
	}

	public int getPathIndex() {
		return this.pathIndex;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((container == null) ? 0 : container.hashCode());
		result = prime * result + pathIndex;
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
		ParentInfo other = (ParentInfo) obj;
		if (container == null) {
			if (other.container != null)
				return false;
		} else if (!container.equals(other.container))
			return false;
		if (pathIndex != other.pathIndex)
			return false;
		return true;
	}
}
