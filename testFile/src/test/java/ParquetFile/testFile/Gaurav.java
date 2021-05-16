package ParquetFile.testFile;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.htrace.fasterxml.jackson.core.JsonProcessingException;
import org.apache.htrace.fasterxml.jackson.databind.JsonNode;
import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;

public class Gaurav {

	public static void main(String[] args) {
		Client bvClient = ClientBuilder.newClient();
		WebTarget bvApi = bvClient.target("http://hadoop-master.openshift.sdntest.netcracker.com:9870")./*path("bvtool").*/path("/webhdfs/v1/datahub/netcracker/staging/r_adm_address_unit/2020-09-25-00-00-00/part-00000-7e236bb4-351d-4f59-8518-e8560a93114e-c000.snappy.parquet?op=OPEN&offset=231");

		ObjectMapper mapper = new ObjectMapper();
        String request = null;
        Response response = null;
        Object responseEntity = null;
        JsonNode responseNode = null;
    
            try {
            	response = bvApi.request(MediaType.APPLICATION_OCTET_STREAM_TYPE).get();
                
                responseEntity = response.getEntity();
                responseNode =mapper.readTree(responseEntity.toString());
			} catch (JsonProcessingException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}finally {
	            bvClient.close();
	        }
            
       System.out.println(responseNode);
	}

}
