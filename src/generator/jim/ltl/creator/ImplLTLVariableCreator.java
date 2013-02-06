package generator.jim.ltl.creator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import generator.jim.tools.Configuration;

public class ImplLTLVariableCreator implements InterfaceLTLVariableCreator {
	
	private Pattern p = Pattern.compile("\\b[a-zA-TV-Z_][a-zA-TV-Z_0-9]*\\b");
	private Matcher m;
	private String formula;
	
	public ImplLTLVariableCreator() {
		this.formula = Configuration.getInstance().getValue("generation.LTL.formula");
		this.m = p.matcher(formula);
	}

	@Override
	public List<String> create() {
		List<String> vars = new ArrayList<String>();
		if(this.m != null) {
			while(m.find()){
				vars.add(this.m.group());
			}
		}
		return vars;
	}
}
