package AAP.GitLabTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class FileParser {
	private static FileInputStream fis;
	private static HSSFWorkbook workbook;
	public static HSSFWorkbook fileParser(String filePath) {
		
		try {
			 fis = new FileInputStream(new File(filePath));
			  workbook = new HSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
       
		return workbook;
		
	} 

}
