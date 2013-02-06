package generator.jim.composite;

import java.util.*;

public abstract class AbstractBlock extends AbstractComponent {

	private List<InterfaceComponent> children = new LinkedList<InterfaceComponent>();
	private String start;
	private String end;
	//When iterating children, if a child is changed, then stop iterating and re do it again
	private boolean childrenChange = false;

	protected AbstractBlock(String start, String end) {
		this.start = start + super.newLine();
		this.end = end + super.newLine();
	}
	
	protected AbstractBlock(String start, String end, boolean isNewLine) {
		this.start = start;
		this.end = end + super.newLine();
	}
	
	public boolean isChildrenChange() {
		return this.childrenChange;
	}

	public void setChildrenChange(boolean childrenChange) {
		this.childrenChange = childrenChange;
	}

	/*************************** getter *************************/

	public List<InterfaceComponent> getChildren() {
		return children;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}
	
	/**
	 * Remove inappropriate if block
	 */
	public abstract void removeEmptyIf();
	
	public AbstractBlock getBlockChild(int index) {
		int pointer = -1;
		InterfaceComponent c;
		for(int i=0;i<this.children.size();i++) {
			c = this.children.get(i);
			if(c instanceof AbstractInline) {
				continue;
			}
			if(c instanceof AbstractBlock) {
				pointer++;
				if(pointer == index) {
					return (AbstractBlock)c;
				}
			}
		}
		return null;
	}

	@Override
	public final void addChild(InterfaceComponent component) {
		if(component != null) {
			component.identifyContainer(this);
			this.children.add(component);
		}		
	}
	
	@Override
	public final void removeChild(InterfaceComponent component) {
		if(this.children.contains(component)) {
			this.children.remove(component);
		}		
	}
	
	@Override
	public final String construct() {
		StringBuffer sb = new StringBuffer(this.start);
		Iterator<InterfaceComponent> it = this.children.iterator();
		InterfaceComponent component;
		while(it.hasNext()) {
			component = it.next();
			sb.append(component.construct());
		}
		return sb.append(this.end).toString();
	}
}
