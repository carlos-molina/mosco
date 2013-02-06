package generator.jim.entites;

import java.util.Iterator;

import generator.jim.composite.AbstractBlock;
import generator.jim.composite.InterfaceComponent;

public class BlockBlank extends AbstractBlock {

	public BlockBlank() {
		super("", "");
	}

	@Override
	public void removeEmptyIf() {
		Iterator<InterfaceComponent> it = this.getChildren().iterator();
		InterfaceComponent c = null;
		AbstractBlock block = null;
		while(it.hasNext()) {
			if(this.isChildrenChange()) {
				break;
			}
			c = it.next();
			if(c instanceof AbstractBlock) {
				block = (AbstractBlock)c;				
				block.removeEmptyIf();
			}
		}
		if(this.isChildrenChange()) {
			this.setChildrenChange(false);
			this.removeEmptyIf();
		}
	}
}
