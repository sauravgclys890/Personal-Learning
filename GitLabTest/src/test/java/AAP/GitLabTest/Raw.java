package AAP.GitLabTest;

public class Raw extends AbstractLayer {
	private static String tableName;
	public Raw() {
    }

    public Raw(String colName, String dataType, String comment) {
        super(colName, dataType, comment);
    }

	public static String getTableName() {
		return tableName;
	}

	public static void setTableName(String tableName) {
		Raw.tableName = tableName;
	}

	public static String getStagingToRawSelectsqlStr() {
		return "select count(*) from staging."+ Staging.getTableName() +" st full join raw."+ Raw.getTableName()+" rw on ";
	}

}
