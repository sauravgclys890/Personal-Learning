package ParquetFile.testFile;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.util.JSON;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestFile {

	public static void main(String[] args) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-00-00-00");
	    String filename = dateFormat.format(yesterday());
	    System.out.println(filename); 
	    
	    Pattern pattern = Pattern.compile("^[A-Za-z_-][A-Za-z0-9_-]*$"); 
		RestAssured.baseURI ="http://hadoop-master.openshift.sdntest.netcracker.com:9870";
		
		
		Response res=RestAssured.given().param("op", "LISTSTATUS").when().get("/webhdfs/v1/datahub/netcracker/staging/r_pb_phone_number/"+filename+"/");
	
	  JSONObject obj = new JSONObject(res.getBody().asString());
	  
	  getKey(obj, "pathSuffix");
	  
	  
	  JSONObject filestatus = obj.getJSONObject("FileStatuses");
	  JSONArray array = filestatus.getJSONArray("FileStatus");
	  
	  JSONObject obj1 = array.getJSONObject(1);
	  
	  System.out.println(obj1.getString("pathSuffix"));
	  
	  System.out.println();
	  
	  Response response=RestAssured.given().param("op", "OPEN").when().get("/webhdfs/v1/datahub/netcracker/staging/r_pb_phone_number/"+filename+"/"+obj1.getString("pathSuffix"));
	  
	  System.out.println(response.getBody().asString());
	  
	  //System.out.println(response.getBody().asString().indexOf("type"));
	  
	 // System.out.println(response.getBody().asString().indexOf("{}}]"));
	  
	  
	  //System.out.println(response.getBody().asString().substring(response.getBody().asString().indexOf("type")-2, response.getBody().asString().indexOf("{}}]")+5));
	 
	     JSONObject myobj = new JSONObject(response.getBody().asString().substring(response.getBody().asString().indexOf("type")-2, response.getBody().asString().indexOf("{}}]")+5));
	
	     JSONArray myarray = myobj.getJSONArray("fields");
	     Map<String, String> map = new LinkedHashMap<String, String>();
	     for(int i=0; i< myarray.length(); i++) {
	    	 JSONObject obj2 = myarray.getJSONObject(i);
	    	map.put(obj2.getString("name"), obj2.getString("type")); 
	     }
	     
	     System.out.println(map);
	}
	
	private static Date yesterday() {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -1);
	    return cal.getTime();
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
							JSONObject innerJSON= new JSONObject(jsonArrayString);
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

public static void parseJSONkey(JSONObject json, String key) {
	System.out.println(json.get(key));
}

}
