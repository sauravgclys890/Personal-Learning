package ParquetFile.testFile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.column.page.PageReadStore;
import org.apache.parquet.example.data.simple.SimpleGroup;
import org.apache.parquet.example.data.simple.convert.GroupRecordConverter;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.hadoop.util.HadoopInputFile;
import org.apache.parquet.io.ColumnIOFactory;
import org.apache.parquet.io.MessageColumnIO;
import org.apache.parquet.io.RecordReader;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.Type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class ParquetReaderUtils {
	 private static final String filePath = "C:\\Users\\sauk0719\\Downloads\\part-00000-17a3d8f3-5460-48aa-bafc-6e8928da253b-c000.snappy.parquet";
 public static void main(String[] args) throws IOException {
	   
	 
	  Parquet parquet = ParquetReaderUtils.getParquetData(filePath);
	  SimpleGroup simpleGroup = parquet.getData().get(0);
	 System.out.println(simpleGroup );
}


public static Parquet getParquetData(String filePath) throws IOException {
    List<SimpleGroup> simpleGroups = new ArrayList<SimpleGroup>();
    ParquetFileReader reader= ParquetFileReader.open(new Configuration(), new Path(filePath));
    MessageType schema = reader.getFooter().getFileMetaData().getSchema();
    List<Type> fields = schema.getFields();
    System.out.println(schema.getFieldName(0));
    PageReadStore pages;
    while ((pages = reader.readNextRowGroup()) != null) {
        long rows = pages.getRowCount(); 
        MessageColumnIO columnIO = new ColumnIOFactory().getColumnIO(schema);
        RecordReader recordReader = columnIO.getRecordReader(pages, new GroupRecordConverter(schema));

        for (int i = 0; i < rows; i++) {
        	SimpleGroup simpleGroup = (SimpleGroup) recordReader.read();
            simpleGroups.add(simpleGroup);
        }
    }
    reader.close();
    return new Parquet(simpleGroups, fields);
}
}
