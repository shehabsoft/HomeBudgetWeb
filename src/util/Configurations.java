package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public   class Configurations {

  private String backendUrl;

	public  String  initialize() throws Exception {
		try {
			Properties prop = new Properties();
			String propFileName = "properties.properties";
 
			InputStream	inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
 
			// get the property value and print it out
			String backendUrl = prop.getProperty("backEndUrl");
			System.out.println(backendUrl);
			return backendUrl;
		
	   
	}catch(Exception e)
		{
		System.out.println(e.toString());
		throw new Exception(e);
		}
	}
	public String getBackendUrl() {
	return backendUrl;
}
	public void setBackendUrl(String backendUrl) {
		this.backendUrl = backendUrl;
	}

}
