package generator.jim.ltl.creator;

import java.io.File;
import java.io.IOException;

import ncl.b1037041.exception.AlertException;

public class ImplCompilerCreator implements InterfaceCompilerCreator {
	
	private ProcessBuilder builder;

	@Override
	public void create() throws AlertException {
		this.builder = new ProcessBuilder("gcc",
				"-w", "-o", "pan", "-D_POSIX_SOURCE", "-DMEMLIM=128", 
				"-DSAFETY", "-DXUSAFE", "-DNOFAIR", "pan.c");
		Process process = null;
		try {
			File pan_c = new File("./promela/pan.c");
			if(pan_c != null) {
				process = this.builder.start();
				// Block current thread, until the process finished
				process.waitFor();
			}			
		} catch (IOException e) {
			throw new AlertException(e);
		} catch (InterruptedException e) {
			throw new AlertException(e);
		}
	}
}
