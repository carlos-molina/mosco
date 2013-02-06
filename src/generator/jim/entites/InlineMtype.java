package generator.jim.entites;

import java.util.*;

import generator.jim.composite.AbstractInline;

public class InlineMtype extends AbstractInline {

	public static String VARIABLE = "mtype";
	private Set<String> protentialOutcomes = new HashSet<String>();
	
	public InlineMtype(Set<String> events) {
		this.protentialOutcomes = events;
	}

	@Override
	public String construct() {
		return VARIABLE + " = " + this.protentialOutcomes.toString().replaceAll("\\[", "\\{").replaceAll("\\]", "\\}") + ";" + super.construct();
	}
}
