package AAP.GitLabTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AAPJsonTest {
	
	private final static String filePath = "C:\\Users\\sauk0719\\Documents\\BV-AAP-ATP Actions\\scripts\\PhoneBookDomain.json";

	public static void main(String[] args) {
		AAPJsonTest json = new AAPJsonTest();
		try {
			json.BuildSqlquery(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	
	public void BuildSqlquery(String filePath) throws FileNotFoundException, IOException, ParseException {
		
		 JSONParser parser = new JSONParser();
		 
		 Object jsonObj = parser.parse(new FileReader(filePath));
         JSONObject tableNameJsonObject =  (JSONObject) jsonObj;
         Set<String> tableNameJsonkeySet = tableNameJsonObject.keySet();
         Iterator<?> tableNameJsonkeyItr= tableNameJsonkeySet.iterator();
         
         while (tableNameJsonkeyItr.hasNext()){
        	 
        	 String tableNameKey = (String)tableNameJsonkeyItr.next();
             jsonObj= tableNameJsonObject.get(tableNameKey);
             JSONObject tcNameJson= (JSONObject) jsonObj;
             String tcName = tcNameJson.get("tcName").toString();
             List<Map> erList = getTcERFromJson(tcNameJson);
             
             for(Map map: erList) {
            	 for(Object o: map.entrySet()) {
            		 Map.Entry en = (Map.Entry)o;
            		 String key = en.getKey().toString();
            		 String value = en.getValue().toString();
            		 
            		 System.out.println("key ="+ key+ ","+ "Value ="+ value);
            	 }
            	 
             }
             
         }
		
	}
	
	public List<Map> getTcERFromJson(JSONObject json){
        List<Map> list= new ArrayList<Map>();
        Map<String, String> results;
        if(!json.isEmpty()){
            JSONArray TcERArray = (JSONArray)json.get("ER");
            for(int i=0; i<TcERArray.size(); i++) {
                JSONObject erJson= (JSONObject) TcERArray.get(i);
                results = new HashMap();
                for (Object o : erJson.entrySet()) {
                    Map.Entry entry = (Map.Entry)o;
                    String value = entry.getValue().toString();
                    String key = entry.getKey().toString();
                    results.put(key, value);
                }
                list.add(results);
            }
        }else{
            
        }
        return list;
    }

}
