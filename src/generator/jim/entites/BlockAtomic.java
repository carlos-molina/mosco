package generator.jim.entites;

import generator.jim.composite.AbstractBlock;

public class BlockAtomic extends AbstractBlock {

	public BlockAtomic() {
		super("atomic {", "}", false);
	}

	@Override
	public void removeEmptyIf() {
		return;
	}
}
