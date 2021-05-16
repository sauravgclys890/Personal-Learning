package AAP.GitLabTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.swing.text.html.parser.TagElement;

public class LayerDataToBvJsonConvertor {
	
	
	
	public static List<BVJsonBuilder> convert(SchemaConfig config) {
        List<BVJsonBuilder> list = new ArrayList<>();
       String sqlScript = null ;
       PrimaryKey pk = new PrimaryKey();
        for (TestCaseConfig testCaseConfig : config.getTestCaseConfig()) {
            List<Map> erList = new ArrayList<>();

            String layer = testCaseConfig.getLayer();
            if ("Staging".equalsIgnoreCase(layer)) {
            	
                for (Staging staging : config.getStagings()) {
                	//System.out.println(staging.getColName() +", "+ staging.getDataType() +","+ staging.getComment());
                    erList.add(builderListMap(staging));
                }
                System.out.println(Staging.getTableName());
                sqlScript = "Describe staging."+Staging.getTableName();
                
            }else if ("Interim".equalsIgnoreCase(layer)) {
            	
                for (Interim interims : config.getInterims()) {
                	//System.out.println("Interim data ="+interims.getColName() +", "+ interims.getDataType() +","+ interims.getComment());
                    erList.add(builderListMap(interims));
                }
                //System.out.println(Staging.getTableName());
                sqlScript = "Describe staging."+Staging.getTableName();
                
            } else if ("Staging_PK_Null".equalsIgnoreCase(layer)) {
            	
            	
                sqlScript = buildPKnotNullSqlQueryStr(config.getRefinedPkLists(), "refined");
                
                System.out.println(sqlScript);
                
                sqlScript = buildRefinedStr(config.getRefinedPkLists());
                //System.out.println(sqlScript);
                
                sqlScript = buildStagingRawAndInterimPKIsUnique(config.getRefinedPkLists(), "refined");
                
                System.out.println(sqlScript);
                
                sqlScript = buildObjectHistorySqlQuery(config.getHistoryList(), "Raw");
                
                //ystem.out.println(sqlScript);
                
            }else if ("Raw".equalsIgnoreCase(layer)) {
                for (Raw raw : config.getRaws()) {
                    erList.add(builderListMap(raw));
                }
                System.out.println(Raw.getTableName()); 
               sqlScript = "Describe raw."+Raw.getTableName();
               
            } else if ("Staging and Raw".equalsIgnoreCase(layer)) {
            	
            	 
            	 Map<String, String> stagingsColumnNames = new LinkedHashMap<String, String>();
            	 
            	 Map<String, String> rawColumnNames = new LinkedHashMap<String, String>();
            	 
            	 for(int i=0; i< config.getStagings().size(); i++) {
            		 stagingsColumnNames.put(config.getStagings().get(i).getColName(), config.getStagings().get(i).getDataType() );
            	 }
                
                
            	 for(int i=0; i< config.getRaws().size(); i++) {
            		 rawColumnNames.put(config.getRaws().get(i).getColName(), config.getRaws().get(i).getDataType() );
            	 }
                
                sqlScript = Raw.getStagingToRawSelectsqlStr()+ buildDataMovementSqlQueryStr(stagingsColumnNames, rawColumnNames);
                
                sqlScript = sqlScript + buildWhereConditionSqlQueryStr(config.getRaws(), config.getStagings());
                
                if(rawColumnNames.containsKey("landing_date")) {
                	sqlScript = sqlScript + Staging.buildStagingToRawLendingDateSqlqueryStr();
                }
                
                System.out.println(sqlScript);

                erList.add(buildERMap());
                 
            } else if ("Staging and Refined".equalsIgnoreCase(layer)) {
                for (Raw raw : config.getRaws()) {
                    erList.add(builderListMap(raw));
                }
            }

            list.add(new BVJsonBuilder(testCaseConfig.getTcName(), erList, sqlScript));
        }

        return list;
    }

    private static Map<String, String> builderListMap(AbstractLayer abstractLayer) {
        Map<String, String> map = new HashMap<>();
        map.put("col_name", abstractLayer.getColName());
        map.put("data_type", abstractLayer.getDataType());
        map.put("comment", abstractLayer.getComment());
        return map;
    }
    
    private static Map<String, String> buildERMap(){
    	Map<String, String> map = new HashMap<>();
    	map.put("count", "0");
    	return map;
    }
    
    private static String buildDataMovementSqlQueryStr(Map<String, String> actualMap, Map<String, String> targetMap) {
    	String str = "";
    	if(!targetMap.isEmpty()) {
    	
    		Iterator<Entry<String, String>> targetItr = targetMap.entrySet().iterator();
    		Iterator<Entry<String, String>> actualItr = actualMap.entrySet().iterator();
    		
    		while(targetItr.hasNext() && actualItr.hasNext()) {
    		  Entry<String, String> targetEntry = targetItr.next();
    		  Entry<String, String> actaulEntery = actualItr.next();
    		  
    		  if(!targetEntry.getKey().isEmpty() ) {
    			  if(targetEntry.getKey().equalsIgnoreCase("date") && targetEntry.getValue().contains("string")){
                      str = str + "nvl(rw.`" + targetEntry.getKey() + "`,'1')=nvl(st."+"`" + actaulEntery.getKey() + "`"+",'1')" + " and ";
                  } else if(targetEntry.getKey().contains("history") && targetEntry.getValue().contains("string")){
                      str = str +"";
                  }
                  else if(targetEntry.getValue().toString().contains("decimal")) {
 					 str= str+ "nvl(rw."+targetEntry.getKey()+",1)=nvl(st."+actaulEntery.getKey()+",1)" + " and ";
 					 
 				 }else if (targetEntry.getValue().toString().contains("string")) {
 					 str= str+ "nvl(rw."+targetEntry.getKey()+",'1')=nvl(st."+actaulEntery.getKey()+",'1')" + " and ";
 					
 				}else if(targetEntry.getValue().toString().contains("timestamp")){
 					str= str+ "nvl(rw."+targetEntry.getKey()+",'2000-01-01')=nvl(st."+actaulEntery.getKey()+",'2000-01-01')" + " and ";

 				}
    			  
    		  }
    		}
    	}
    	else {
    		str = null;
    	}
    	 str = str.substring(0, str.length()-4);
    	return str;
    	
    }
    
    private static String buildWhereConditionSqlQueryStr(List<Raw> rawList, List<Staging> stagingList) {
    	String str ="";
    	if(!rawList.isEmpty()) {
    		str = "where (st."+stagingList.get(0).getColName()+" is "+ null+ " or r."+rawList.get(0).getColName()+ " is "+ null+") ";
    	}else {
    		str = " ";
    	}
		return str;
    	
    	
    }
    
    
    
    private static String buildRefinedStr(List<AbstractPK> pkList) {
    	String str ="SELECT count(*) as count FROM ";
    	if(!pkList.isEmpty() ) {
    		str = str + "staging."+Staging.getTableName()+" WHERE ";
    		for(AbstractPK pkStr : pkList) {
    		str = str + pkStr.getpKName() + ",";
    		}
    	}else {
    		str = " ";
    	}
    	str = str.substring(0, str.length()-1);
    	str = str + " IS NULL";
		return str;
    	
    }
    
    private static String buildStagingRawAndInterimPKIsUnique(List<AbstractPK> pkList, String layerType) {
    	String str ="";
    	if(!pkList.isEmpty()) {
    		str = "SELECT count(a."+pkList.get(0).getpKName()+") FROM (SELECT "+pkList.get(0).getpKName()+", count("+pkList.get(0).getpKName()+
    				") qty FROM "+layerType+"."+ getTableName(layerType)+ " GROUP BY "+getStringFromLoop(pkList);
    	} 
    	str = str + " extraction_timestamp) a WHERE a.qty > 1";
		return str;
    	
    }
    
    public static String buildPKnotNullSqlQueryStr(List<AbstractPK> pkList, String layerType) {
    	String str ="SELECT count(*) as count FROM ";
    	if(!pkList.isEmpty() ) {
    		str = str + layerType+"."+getTableName(layerType)+" WHERE "+getStringFromLoop(pkList);
    		}
    	else {
    		str = " ";
    	}
    	str = str.substring(0, str.length()-1);
    	str = str + " IS NULL";
		return str;
    	
    	
    }
    
    private static String buildObjectHistorySqlQuery(List<History> hisList, String layerType) {
    	String str ="SELECT count(*) as count FROM ";
    	if(!hisList.isEmpty() && layerType.equalsIgnoreCase("Staging")) {
    		str = str + "staging."+Staging.getTableName()+" WHERE "+ buildObjectHistorySql(hisList);
    		
    	}else if(!hisList.isEmpty() && layerType.equalsIgnoreCase("Raw")){
    		str = str + "raw."+Raw.getTableName()+" WHERE "+ buildObjectHistorySql(hisList);
    	}else {
    		str = " ";
    	}
    	str = str.substring(0, str.length()-4);
		return str;
    	
    	
    }
    
    private static String buildObjectHistorySql(List<History> historyObjectList){
        String str ="";
        for(History pkStr : historyObjectList) {
            if(pkStr.getHistory().equalsIgnoreCase("date")) {
                str = str + "`"+pkStr.getHistory()+"`"+ " IS NULL" + " OR ";
            }else {
                str = str + pkStr.getHistory()+ " IS NULL" + " OR ";
            }
        } 
        return str;
    }
    
    private static String getTableName(String layerType) {
    	String str="";
    	if(layerType.equalsIgnoreCase("Staging")) {
    		str = Staging.getTableName();
    	}else if(layerType.equalsIgnoreCase("Raw")) {
    		str = Raw.getTableName();
    	}else if(layerType.equalsIgnoreCase("Interim")) {
    		str = Interim.getTableName();
    	}else {
    		str = null;
    	}
    	
		return str;
    }
    
    private static String getStringFromLoop(List<AbstractPK> pkList) {
    	String str = "";
    	for(AbstractPK pkStr : pkList) {
			str = str + pkStr.getpKName() + ",";
		}
		return str;
    }

}
