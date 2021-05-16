package APIAutomation.apiConfig;

import java.util.HashMap;
import java.util.Map;

public class HeaderConfig {

	public Map<String, String> defaultHeader(){
		
		Map<String, String> defaultHeader= new HashMap<String, String>();
		
		defaultHeader.put("Content-Type", "application/json");
		
		return defaultHeader;
	}
	
	public Map<String, String> headerWithToken(){
		
		Map<String, String> tokenHeader= new HashMap<String, String>();
		tokenHeader.put("Content-Type", "application/json");
		tokenHeader.put("Access_Token", "");
		tokenHeader.put("jwt_token", "");
		return tokenHeader;
	}
}
