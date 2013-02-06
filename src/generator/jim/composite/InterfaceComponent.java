package generator.jim.composite;

public interface InterfaceComponent {

	/**
	 * Add child component
	 * @param component Child
	 */
	void addChild(InterfaceComponent component);
	
	/**
	 * Remove a child of the component
	 * @param component
	 */
	void removeChild(InterfaceComponent component);
	
	/**
	 * Add parent component
	 * @param container Parent component
	 */
	void identifyContainer(AbstractBlock container);
	
	/**
	 * Get parent component
	 * @return
	 */
	AbstractBlock getContainer();

	/**
	 * Construct the content
	 * @return
	 */
	String construct();
}
