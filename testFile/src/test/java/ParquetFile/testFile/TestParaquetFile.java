package ParquetFile.testFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.hadoop.util.HadoopInputFile;

public class TestParaquetFile {

	public static void main(String[] args) {
		try {
			URL url = new URL("http://hadoop-master.openshift.sdntest.netcracker.com:9870/webhdfs/v1/datahub/netcracker/staging/r_pb_phone_number/2020-10-07-00-00-00/part-00000-64dd0c21-a292-43b1-9498-3c6b4f826f0d-c000.snappy.parquet?op=OPEN");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setDoInput(true);
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			System.out.println(br.readLine());
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
