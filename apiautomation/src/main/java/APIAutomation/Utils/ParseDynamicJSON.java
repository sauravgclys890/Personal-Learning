package APIAutomation.Utils;

import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParseDynamicJSON {
	
	public static void parseJSONkey(JSONObject json, String key) {
		//System.out.println(json.has(key));
		System.out.println(json.get(key));
	}
	
	public static void getKey(JSONObject json, String key) {
		
		boolean exits= json.has(key);
		if(!exits) {
			Set<String> set=json.keySet();
			Iterator<?> itr= set.iterator();
			String nextKey;
			while(itr.hasNext()) {
				nextKey= (String) itr.next();
				try {
					if(json.get(nextKey) instanceof JSONObject) {
						if(exits == false)
							getKey(json.getJSONObject(nextKey), key);
					}else if(json.get(nextKey) instanceof JSONArray) {
						JSONArray array= json.getJSONArray(nextKey);
						for(int i=0; i<array.length(); i++) {
							String jsonArrayString= array.get(i).toString();
							JSONObject innerJSgON= new JSONObject(jsonArrayString);
							if(exits == false)
								getKey(innerJSON, key);
						}
						
					}
					
				}catch(Exception e) {
					
				}
			}
			
		}else {
			parseJSONkey(json, key);
		}
	}
	
	public static void main(String[] args) {
		String response= "{\r\n" + 
				"  \"markers\": [\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"Rixos The Palm Dubai\",\r\n" + 
				"      \"position\": [25.1212, 55.1535],\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"Shangri-La Hotel\",\r\n" + 
				"      \"location\": [25.2084, 55.2719]\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"name\": \"Grand Hyatt\",\r\n" + 
				"      \"location\": [25.2285, 55.3273]\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}";
		
		JSONObject json= new JSONObject(response);
		getKey(json, "name");
	}

}
