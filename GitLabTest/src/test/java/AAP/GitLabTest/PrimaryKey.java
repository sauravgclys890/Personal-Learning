package AAP.GitLabTest;

import java.util.List;

public class PrimaryKey extends AbstractPK{
	 public PrimaryKey() {
	    }
	 
	 public PrimaryKey(String pkName) {
	        super(pkName);
	    }
	
	 public String buildPrimaryKeySqlQueryStr(List<AbstractPK> pkList, String layerType) {
	    	String str ="SELECT count(*) as count FROM ";
	    	if(!pkList.isEmpty() && layerType.equalsIgnoreCase("Staging")) {
	    		str = str + "staging."+Staging.getTableName()+" WHERE ";
	    		for(AbstractPK pkStr : pkList) {
	    		str = str + pkStr.getpKName() + ",";
	    		}
	    	}else if(!pkList.isEmpty() && layerType.equalsIgnoreCase("raw")){
	    		str = str + "raw."+Raw.getTableName()+" WHERE ";
	    		for(AbstractPK pkStr : pkList) {
	    		str = str + pkStr.getpKName() + ",";
	    		}
	    	}else if(!pkList.isEmpty() && layerType.equalsIgnoreCase("Interim")) {
	    		str = str + "interim."+Interim.getTableName()+" WHERE ";
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
	
}
