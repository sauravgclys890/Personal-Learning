package ParquetFile.testFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.avro.generic.GenericData;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetReader;
import org.apache.parquet.hadoop.ParquetReader;

public class ParquetFileReader {
 
 String path = "C:\\Users\\sauk0719\\Downloads\\part-00000-17a3d8f3-5460-48aa-bafc-6e8928da253b-c000.snappy.parquet";
	public static void main(String[] args) throws IOException {
		
		ParquetFileReader reader = new ParquetFileReader();
		System.out.println(reader.getSchema());
		reader.getRecords(0);

	}
	
	public String getSchema() throws IOException {
	    ParquetReader<Object> parquetReader =
	        AvroParquetReader.builder(new Path(this.path)).build();
	    GenericData.Record firstRecord = (GenericData.Record) parquetReader.read();
	    System.out.println(firstRecord);
	    if (firstRecord == null) {
	      throw new IOException("Can't process empty Parquet file");
	    }
	    return firstRecord.getSchema().toString(true);
	  }
	
	
	  public List<String> getRecords(int numRecords) throws IOException, IllegalArgumentException {
	    List<String> records = new ArrayList<>();
	    try (ParquetReader<Object> parquetReader =
	        AvroParquetReader.builder(new Path(this.path))
	            .withDataModel(null)
	            .build()) {
	      GenericData.Record value;
	      for (int i = 0; i < numRecords; i++) {
	        value = (GenericData.Record) parquetReader.read();
	        System.out.println(value);
	        if (value == null) {
	         System.out.println(records);
	          return records;
	        } else {
	          records.add(value.toString());
	        }
	      }
	    }
	    return records;
	  }
	}


