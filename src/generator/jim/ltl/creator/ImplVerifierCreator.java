package generator.jim.ltl.creator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ncl.b1037041.exception.AlertException;

public class ImplVerifierCreator implements InterfaceVerifierCreator {
	
	private ProcessBuilder builder = new ProcessBuilder("pan",
			"-v", "-X", "-m10000", "-w19", "-A", "-E", "-n", "-c1");

	@Override
	public String create() throws AlertException {
		Process process = null;
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
				buffer.append(line).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

}
