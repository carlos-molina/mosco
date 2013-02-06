package generator.jim.tools;

import java.util.LinkedList;
import java.util.Queue;

public final class LTLSymbolGenerator {

	private final Queue<String> symbol = new LinkedList<String>();
	String[] s = {"a","b","c","d","e","f","g","h","i","j","k","l",
			"m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	
	public LTLSymbolGenerator() {
		for(int i=0; i<this.s.length; i++) {
			symbol.add(s[i]);
		}
	}
	
	public String getNextSymbol() {
		return this.symbol.poll();
	}
}
