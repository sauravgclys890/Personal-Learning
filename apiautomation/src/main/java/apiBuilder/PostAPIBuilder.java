package apiBuilder;

import java.util.HashMap;
import java.util.Map;

public class PostAPIBuilder {
	
	public Map<String, String> postRequestBody(String name, String job) {
		Map<String, String> body= new HashMap<String, String>();
		
		body.put("name", name);
		body.put("job", job);
		return body;
		
		
	}

}
