package AAP.GitLabTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.aventstack.extentreports.utils.StringUtil;

public class AAPExcelReader {
	private static final String filePath = "C:\\Users\\sauk0719\\Documents\\AT_New_concept_example.xls";
	static HSSFSheet spreadsheet = null;
	static int startRowIndex;
	static List<Map> stagingErList;
	static List<Map> rawErList;
	static String stagingDescribeSqlScripts;
	static String rawDescribeSqlScripts;
	static boolean landingDate = false;

	public static void main(String[] args) {
		
		try {
			FileInputStream fis = new FileInputStream(new File(filePath));
			HSSFWorkbook myWorkBook = new HSSFWorkbook(fis);
			spreadsheet = myWorkBook.getSheetAt(0);
			
			  
			 Row row;
			 
			 for(int i=0; i< spreadsheet.getLastRowNum(); i++) {
				 row = spreadsheet.getRow(i);
				 
				if(row != null) {
					String tcName = row.getCell(0).getStringCellValue();
					
					if(tcName.contains("Staging 1. Check the structure of table")) {
						stagingDescribeSqlScripts = "DESCRIBE staging."+ spreadsheet.getSheetName();
						//System.out.println(stagingDescribeSqlScripts);
						startRowIndex = row.getRowNum();
						int stagingColIndex = 1;
						stagingErList=getTableStructure(startRowIndex, stagingColIndex);
						
						for(Map map: stagingErList) {
							for (Object o : map.entrySet()) {
				                Map.Entry en = (Map.Entry)o;
				                String value = en.getValue().toString();
				                String key = en.getKey().toString();
				                //System.out.println(key+ ", " + value);
				            }
						}
					}else if (tcName.contains("Raw 1. Check the structure of table")) {
						rawDescribeSqlScripts = "DESCRIBE raw."+ spreadsheet.getSheetName();
						System.out.println(rawDescribeSqlScripts);
						int rawColIndex = 4;
						rawErList=getTableStructure(startRowIndex, rawColIndex);
						
						for(Map map: rawErList) {
							for (Object o : map.entrySet()) {
				                Map.Entry en = (Map.Entry)o;
				                String value = en.getValue().toString();
				                String key = en.getKey().toString();
				               // System.out.println(key+ ", " + value);
				            }
						}
						
					}else if (tcName.contains("Raw 10. Check that data from STAGING successfully moved to RAW")) {
						String str = "select count(*) from "+ stagingDescribeSqlScripts +" s full join "+ rawDescribeSqlScripts+" r on ";
						String landindDateSubQuery = "and s.landing_date in (select max(landing_date) from staging."+spreadsheet.getSheetName()+") and r.landing_date=s.landing_date";
						StringBuilder sqlScripts = new StringBuilder(str);
						String sqlQuery= createSqlQuery(stagingErList, rawErList, sqlScripts);
						if(landingDate) {
							sqlQuery = sqlQuery + landindDateSubQuery;
						}
						
						System.out.println(sqlQuery);
						System.out.println(landingDate);
						
					}
					 
				}
				 
			 }

            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		

	}
	
	public static List<Map> getTableStructure(int startRowIndex, int stagingColIndex){
		List<Map> list= new ArrayList<Map>();
        Map<String, String> results;
        Row row;
        	for(int i= startRowIndex; i< spreadsheet.getLastRowNum(); i++) {
        		row = spreadsheet.getRow(i);
        		results = new HashMap();
        		if(!row.getCell(1).getStringCellValue().equalsIgnoreCase("end")) {
        			String col_name= row.getCell(stagingColIndex).getStringCellValue();
            		String data_type = row.getCell(stagingColIndex+1).getStringCellValue();
            		String comments = row.getCell(stagingColIndex+ 2).getStringCellValue();
            		results.put("col_name",col_name);
            		results.put("data_type",data_type);
            		results.put("comments",comments);
            		
            		list.add(results);
        		}else
        		{
        			break;
        		}
        		
        	}
        
		return list;
		
	}
	
	public static String createSqlQuery(List<Map> erList1, List<Map> erList2, StringBuilder sqlString) {
		String sqlScript = sqlString.toString();
		List<String> erCharge1= new ArrayList<String>();
		List<String> erCharge2= new ArrayList<String>();
		
		for(int i=0; i<erList2.size(); i++) {
			Map erMap1 = erList1.get(i);
			Map erMap2 = erList2.get(i);
			sqlScript = sqlScript + " and ";
		 String ercol_name1 = erMap1.get("col_name").toString();
		 String ercol_name2 = erMap2.get("col_name").toString();
		 if(ercol_name2.equalsIgnoreCase("landing_date")) {
			  landingDate = true;
		 }
		 if(erMap2.get("data_type").toString().contains("decimal")) {
			 sqlScript= sqlScript+ "nvl(r."+ercol_name1+",1)=nvl(s."+ercol_name2+",1)";
			 
		 }else if (erMap2.get("data_type").toString().contains("string")) {
			 sqlScript= sqlScript+ "nvl(r."+ercol_name1+",'1')=nvl(s."+ercol_name2+",'1')";
			 
		}else if(erMap2.get("data_type").toString().contains("timestamp")){
			sqlScript= sqlScript+ "nvl(r."+ercol_name1+",'2000-01-01')=nvl(s."+ercol_name2+",'2000-01-01')";
			
		}
		 
		}
		
		sqlScript = sqlScript + " where (s."+erList1.get(0).get("col_name").toString()+" is "+ null+ " or r."+erList2.get(0).get("col_name").toString()+ " is "+ null+")";
					return sqlScript;
	}
	
	
    

}
