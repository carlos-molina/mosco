package generator.jim.composite;

public abstract class AbstractInline extends AbstractComponent {

	protected AbstractInline() {}

	@Override
	public final void addChild(InterfaceComponent component) {
		throw new UnsupportedOperationException();
	}	
	
	@Override
	public final void removeChild(InterfaceComponent component) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String construct() {
		return super.newLine();
	}
}
