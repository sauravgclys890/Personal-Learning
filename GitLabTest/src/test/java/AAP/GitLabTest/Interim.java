package AAP.GitLabTest;

import java.util.List;

public class Interim extends AbstractLayer {

    private static String tableName;

    public Interim() {
    }

    public Interim(String colName, String dataType, String comment) {
        super(colName, dataType, comment);
    }

    public static String getTableName() {
        return tableName;
    }

    public static void setTableName(String tableName) {
        Interim.tableName = tableName;
    }

    public static String getRawToInterimSelectsqlStr() {
        return "select count(*) from raw." + Raw.getTableName() + " rw full join interim." + Interim.getTableName() + " nt on ";
    }

    public static String buildWhereConditionSqlQueryStrForRawToInterim(List<Interim> interimList, List<Raw> rawList) {
        String str = "";
        if (!interimList.isEmpty()) {
            str = "where (rw." + rawList.get(0).getColName() + " is " + null + " or nt." + interimList.get(0).getColName() + " is " + null + ") ";
        } else {
            str = " ";
        }
        return str;

    }
}