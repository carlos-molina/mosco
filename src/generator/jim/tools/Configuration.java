package generator.jim.tools;

import java.io.*;
import java.util.*;

public class Configuration {
	
	private static Configuration configuration;

	private Properties propertie;
	

	private Configuration() {
		InputStream inputFile = null;
		this.propertie = new Properties();
		try {
			inputFile = new FileInputStream("src/config.properties");
			this.propertie.load(inputFile);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (inputFile != null) {
					inputFile.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static synchronized Configuration getInstance() {
		if(configuration == null) {
			configuration = new Configuration();
		}
		return configuration;
	}

	
	public String getValue(String key) {
		if (this.propertie.containsKey(key)) {
			String value = this.propertie.getProperty(key);
			if(value != null) {
				return value.trim();
			}
		} 
		throw new RuntimeException("No such property");
	}

}
