package generator.jim.tools;

import java.io.*;

public abstract class FileGenerator {

	private static String FOLDER = Configuration.getInstance().getValue(
			"file.location");
	private static String SUFFIX = Configuration.getInstance().getValue(
			"file.suffix");

	public static void generatorFile(String fileName, String content, String currentTime) {
		if(content == null) {
			throw new IllegalArgumentException();
		}
		File folder = new File(FOLDER);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		File promelaCode = new File(FOLDER + "\\" + fileName + "_" + currentTime
				+ SUFFIX);

		FileWriter fw = null;
		try {
			fw = new FileWriter(promelaCode);
			fw.write(content);
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fw != null) {
					fw.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void generatorFile(String content, String currentTime) {
		generatorFile("", content, currentTime);
	}
}
