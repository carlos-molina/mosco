package generator.jim.tools;

public class XMLTool {
	
	private static final int TAB_SPACE = 4;

	private StringBuffer buffer = new StringBuffer("");
	
	public XMLTool space(int num) {
		for(int i=0; i<num; i++) {
			this.buffer.append(" ");
		}
		return this;
	} 
	
	public XMLTool tab() {
		return this.space(TAB_SPACE);
	}
	
	public XMLTool tab(int num) {
		for(int i=0; i<num; i++) {
			this.tab();
		}
		return this;
	}
	
	public XMLTool enter() {
		this.buffer.append("\n");
		return this;
	}
	
	public XMLTool appendString(String s) {
		this.buffer.append(s);
		return this;
	}
	
	public String toString() {
		return this.buffer.toString();
	}
}