package generator.jim.ltl.creator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import generator.jim.convert.AbstractConvertor;
import generator.jim.convert.InterfaceConvertor;
import ncl.b1037041.LTL.entites.BPMNChoreography;
import ncl.b1037041.bpmn.choreographies.Context;
import ncl.b1037041.exception.AlertException;

public class ImplModelCreator implements InterfaceModelCreator {
	
	private static final String PROMELA_FILE_PATH = "./promela/promela.txt";
	
	private BPMNChoreography bpmn;
	private InterfaceLTLCreator ltlCreator;
	
	private ProcessBuilder builder;
	
	public ImplModelCreator(BPMNChoreography bpmn, InterfaceLTLCreator ltlCreator) {
		this.bpmn = bpmn;
		this.ltlCreator = ltlCreator;
	}

	@Override
	public String create() throws AlertException {
		String model = null;
		Process process = null;
		try {
			Context context = Context.init(this.bpmn);
			InterfaceConvertor convertor = AbstractConvertor.getConvertor(context);
			String content = convertor.convert();		
			String ltl = this.ltlCreator.create();
			model = content + ltl;

			File promelaFile = new File(PROMELA_FILE_PATH);
			if(promelaFile.exists()) {
				promelaFile.delete();
				promelaFile = new File(PROMELA_FILE_PATH);
			}
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(promelaFile)));
			out.write(model, 0, model.length());
			out.flush();
			out.close();

			this.builder = new ProcessBuilder("spin", "-a", promelaFile.getAbsolutePath());			
			InputStream is;
			InputStreamReader isr;
			BufferedReader br;
			process = this.builder.start();
			is = process.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				if(line.indexOf("syntax error") != -1) {
					throw new AlertException("Syntax error in PROMELA model.");
				}
			}
		} catch (Exception e) {
			throw new AlertException(e);
		}
		return model;
	}
}
