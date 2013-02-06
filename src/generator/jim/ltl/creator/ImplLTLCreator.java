package generator.jim.ltl.creator;

import generator.jim.tools.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ncl.b1037041.exception.AlertException;

public class ImplLTLCreator implements InterfaceLTLCreator {
	
	private static String SPIN_PATH = Configuration.getInstance().getValue("spin.path");
	
	private ProcessBuilder builder;
	private String formula;
	
	public ImplLTLCreator(String formula) {
		this.formula = formula;
		this.builder = new ProcessBuilder(SPIN_PATH, "-f", this.formula);
	}

	@Override
	public String create() throws AlertException {
		Process process;
		InputStream is;
		InputStreamReader isr;
		BufferedReader br;
		StringBuffer buffer = new StringBuffer("");
		try {
			process = this.builder.start();
			is = process.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				if(line.startsWith("tl_spin:")) {
					throw new AlertException("Syntax error in formula.");
				}
				buffer.append(line).append("\n");
				//System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return buffer.toString();
	}
}
