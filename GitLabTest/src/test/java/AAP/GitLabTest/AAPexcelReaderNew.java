package AAP.GitLabTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

public class AAPexcelReaderNew {
	
	private static final String filePath = "C:\\Users\\sauk0719\\Documents\\chrome 83\\Address.xls";
	static HSSFSheet spreadsheet = null;
	static int startRowIndex;
	static List<Map> stagingErList;
	static List<Map> rawErList;
	static String stagingDescribeSqlScripts;
	static String rawDescribeSqlScripts;
	static boolean landingDate = false;

	public static void main(String[] args) throws ParseException {
		
		try {
			File file = new File(filePath);
			long lastModified = file.lastModified();
			
			DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		     System.out.println(file.getName()+ " , "+ sdf.format(new Date(lastModified)));
		   
		     
		     DateFormat sdfnew = new SimpleDateFormat("dd-MM-yyyy");
		     System.out.println(sdfnew.format(new Date()));
		     
		
		     
		     Date date1 = sdf.parse(sdf.format(new Date(lastModified)));
		     
		     Date date3 = sdf.parse(sdf.format(yesterday()));
		     
		     Date date2 = sdf.parse(sdf.format(new Date()));
		     
		    System.out.println(date2.equals(date1));
		    
		    System.out.println(date3.before(date2));
		     
			FileInputStream fis = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
		for(Sheet sheet: workbook) {
			LayerDataParser layer = new LayerDataParser(sheet, sheet.getSheetName());
			SchemaConfig config = layer.parse();
			List<BVJsonBuilder> list = LayerDataToBvJsonConvertor.convert(config);
		}
        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
            throw new RuntimeException("An error occurred while reading Excel file", e);
        }
		
	}
	
	private static Date yesterday() {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -1);
	    return cal.getTime();
	}

}
