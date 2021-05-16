package AAP.GitLabTest;

public class Staging extends AbstractLayer {
	static String tableName;
	static String str ="";
	public Staging() {
    }

    public Staging(String colName, String dataType, String comment) {
        super(colName, dataType, comment);
    }

	public static String getTableName() {
		return tableName;
	}

	public static void setTableName(String tableName) {
		Staging.tableName = tableName;
	}
	
	public static String buildStagingToRawLendingDateSqlqueryStr() {
		str = "and st.landing_date in (select max(landing_date) from staging."+Staging.getTableName()+") and rw.landing_date=st.landing_date";
		return str;
		
	}

	
}
