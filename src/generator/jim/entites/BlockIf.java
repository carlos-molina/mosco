package generator.jim.entites;

import java.util.Iterator;
import java.util.List;

import generator.jim.composite.AbstractBlock;
import generator.jim.composite.InterfaceComponent;

public class BlockIf extends AbstractBlock {

	public BlockIf() {
		super("if", "fi;");
	}
	
	@Override
	public void removeEmptyIf() {
		//if if block has a empty colon, then remove the empty colon
		Iterator<InterfaceComponent> it = this.getChildren().iterator();
		BlockColon colon = null;
		while(it.hasNext()) {
			colon = (BlockColon)it.next();
			if(colon.getChildren().size() == 0) {
				it.remove();
			}
		}
		
		//if there is no child in this if block, then remove the block form its container
		if(this.getChildren().size() == 0) {
			this.getContainer().removeChild(this);
			this.getContainer().setChildrenChange(true);
		}
		
		//if it has only one child, then append them to this block's container
		else if(this.getChildren().size() == 1) {

			List<InterfaceComponent> brothers = this.getContainer().getChildren();
			int currentIndex = brothers.indexOf(this);
			brothers.remove(currentIndex);
			brothers.addAll(currentIndex, ((BlockColon)this.getChildren().get(0)).getChildren());
			
			Iterator<InterfaceComponent> it_parent = ((BlockColon)this.getChildren().get(0)).getChildren().iterator();
			while(it_parent.hasNext()) {
				it_parent.next().identifyContainer(this.getContainer());
			}

			this.getContainer().setChildrenChange(true);
			System.out.println();
		}

		else {
			Iterator<InterfaceComponent> it_colon = this.getChildren().iterator();
			BlockColon c = null;
			while(it_colon.hasNext()) {
				c = (BlockColon)it_colon.next();
				c.removeEmptyIf();
			}
		}
	}
}
