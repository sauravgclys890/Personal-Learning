package APIAutomation.Utils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FileandEnv {

	public static Map<String, String> fileandenv= new HashMap<String, String>();
	public static Properties propMain= new Properties();
	public static Properties propPreSet= new Properties();
	
	public static Map<String, String> envAndFile(){
		
		String enviroment= System.getProperty("env");
		
		try {
			if(enviroment.equalsIgnoreCase("dev")) {
				FileInputStream fileDev= new FileInputStream(System.getProperty("user.dir")+"/input/dev.properties");
				propMain.load(fileDev);
				fileandenv.put("serverUrl", propMain.getProperty("ServerUrl"));
				fileandenv.put("portNumber", propMain.getProperty("portnumber"));
				fileandenv.put("username", propMain.getProperty("username"));
				fileandenv.put("password", propMain.getProperty("password"));
			}
			else if(enviroment.equalsIgnoreCase("staging")) {
				FileInputStream fileDev= new FileInputStream(System.getProperty("user.dir")+"/input/staging.properties");
				propMain.load(fileDev);
				fileandenv.put("serverUrl", propMain.getProperty("ServerUrl"));
				fileandenv.put("portNumber", propMain.getProperty("portNumber"));
				fileandenv.put("username", propMain.getProperty("username"));
				fileandenv.put("password", propMain.getProperty("password"));
			} else {
				FileInputStream fileDev= new FileInputStream(System.getProperty("user.dir")+"/input/qa.properties");
				propMain.load(fileDev);
				fileandenv.put("serverUrl", propMain.getProperty("ServerUrl"));
				fileandenv.put("portNumber", propMain.getProperty("portNumber"));
				fileandenv.put("username", propMain.getProperty("username"));
				fileandenv.put("password", propMain.getProperty("password"));
			}
		}catch(Exception e) {
			
		}
		return fileandenv;
		
		
	}
	
	public static Map<String, String> getConfigReader(){
		if(fileandenv == null) {
			fileandenv = envAndFile();
		}
			
		return fileandenv;
		
	}
}
