package generator.jim.entites;

import java.util.*;

import generator.jim.composite.AbstractBlock;

public class BlockRoot extends AbstractBlock {

	private Map<String, AbstractBlock> blockMap = new HashMap<String, AbstractBlock>();
	
	private static final String DEFINE_BOLCK = "defineBlock";
	private static final String MESSAGE_BOLCK = "messageBlock";
	private static final String CHANNEL_BOLCK = "channelBlock";
	private static final String PROCESS_BOLCK = "processBlock";
	private static final String INIT_BOLCK = "initBlock";

	public BlockRoot() {
		super("", "");
		this.blockMap.put(DEFINE_BOLCK, new BlockBlank());
		this.blockMap.put(MESSAGE_BOLCK, new BlockBlank());
		this.blockMap.put(CHANNEL_BOLCK, new BlockBlank());
		this.blockMap.put(PROCESS_BOLCK, new BlockBlank());
		this.blockMap.put(INIT_BOLCK, new BlockBlank());

		this.addChild(this.getDefineBlock());
		this.addChild(this.getMessageBlock());
		this.addChild(this.getChannelBlock());
		this.addChild(this.getProcessBlock());
		this.addChild(this.getInitBlock());		
	}
	
	public final AbstractBlock getDefineBlock() {
		return this.blockMap.get(DEFINE_BOLCK);
	}

	public final AbstractBlock getMessageBlock() {
		return this.blockMap.get(MESSAGE_BOLCK);
	}
	
	public final AbstractBlock getChannelBlock() {
		return this.blockMap.get(CHANNEL_BOLCK);
	}
	
	public final AbstractBlock getProcessBlock() {
		return this.blockMap.get(PROCESS_BOLCK);
	}
	
	public final AbstractBlock getInitBlock() {
		return this.blockMap.get(INIT_BOLCK);
	}

	@Override
	public final void removeEmptyIf() {
		throw new UnsupportedOperationException();
	}
}
