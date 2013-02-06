package generator.jim.entites;

import java.util.Iterator;

import generator.jim.composite.AbstractBlock;
import generator.jim.composite.InterfaceComponent;

public class BlockColon extends AbstractBlock {

	public BlockColon() {
		super("::", "");
	}
	
	@Override
	public void removeEmptyIf() {
		Iterator<InterfaceComponent> it = this.getChildren().iterator();
		InterfaceComponent c = null;
		while(it.hasNext()) {
			if(this.isChildrenChange()) {
				break;
			}
			c = it.next();
			if(c instanceof BlockIf) {
				((BlockIf)c).removeEmptyIf();
			}
		}
		if(this.isChildrenChange()) {
			this.setChildrenChange(false);
			this.removeEmptyIf();
		}
	}
}
