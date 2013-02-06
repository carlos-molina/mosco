package generator.jim.composite;

public abstract class AbstractComponent implements InterfaceComponent {
	
	//Not be used here, deal with indentation
	//protected int level;
	
	private AbstractBlock container;
	
	protected AbstractComponent() {}
	
	protected String newLine() {
		return "\n";
	}
	
	@Override
	public final void identifyContainer(AbstractBlock container) {
		this.container = container;
	}
	
	@Override
	public final AbstractBlock getContainer() {
		return this.container;
	}

}
