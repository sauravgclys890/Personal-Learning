package ParquetFile.testFile;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.column.page.PageReadStore;
import org.apache.parquet.example.data.simple.convert.GroupRecordConverter;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.io.ColumnIOFactory;
import org.apache.parquet.io.MessageColumnIO;
import org.apache.parquet.io.RecordReader;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.Type;


public class Saurav {

	private static final String filePath = "C:\\Users\\sauk0719\\Documents\\staging.parquet";
	public static void main(String[] args) {
		try {
			ParquetFileReader reader = ParquetFileReader.open(new Configuration(), new Path("hdfs://hadoop-master.openshift.sdntest.netcracker.com:9870/webhdfs/v1/datahub/netcracker/staging/r_pb_phone_number/2020-10-07-00-00-00/part-00000-64dd0c21-a292-43b1-9498-3c6b4f826f0d-c000.snappy.parquet"));
			 MessageType schema = reader.getFooter().getFileMetaData().getSchema();
		        List<Type> fields = schema.getFields();
		        
		        PageReadStore pages;
		        while ((pages = reader.readNextRowGroup()) != null) {
		            long rows = pages.getRowCount();
		            MessageColumnIO columnIO = new ColumnIOFactory().getColumnIO(schema);
		        
		            RecordReader recordReader = columnIO.getRecordReader(pages, new GroupRecordConverter(schema));
		            
                   // System.out.println(recordReader.read());
		            for (int i = 0; i < rows; i++) {
		                
		                System.out.println(recordReader.read());
		            }
		        }
		        reader.close();
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		

	}

}
