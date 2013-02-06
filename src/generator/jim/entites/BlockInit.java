package generator.jim.entites;

import generator.jim.composite.AbstractBlock;

public class BlockInit extends AbstractBlock {

	public BlockInit() {
		super("init {", "}");
	}

	@Override
	public void removeEmptyIf() {
		return;
	}	
}
