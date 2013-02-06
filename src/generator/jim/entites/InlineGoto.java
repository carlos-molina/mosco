package generator.jim.entites;

import generator.jim.composite.AbstractInline;
import generator.jim.meta.MataData;

public class InlineGoto extends AbstractInline {
	
	private InlineLoopLabel loopLabel;

	public InlineGoto(InlineLoopLabel loopLabel) {
		this.loopLabel = loopLabel;
	}

	public InlineLoopLabel getLoopLabel() {
		return loopLabel;
	}
	
	@Override
	public String construct() {
		return MataData.JUMP_GOTO + " " + this.loopLabel.getLableFullName() + ";" + super.construct();
	}
}
