package HiveTest.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {

	public static void main(String[] args) throws Exception {
		
		Class.forName("org.apache.hive.jdbc.HiveDriver");
		Connection con = DriverManager.getConnection("jdbc:hive2://hadoop-gateway.openshift.sdntest.netcracker.com:10000/raw", "nc_admin", "nc_admin");
		
		System.out.println(con);
		
		Statement stmt = con.createStatement();
		
		System.out.println(stmt);
	    ResultSet res = stmt.executeQuery("select max (landing_date) as landing_date FROM STAGING.R_POC_DISTRIBUTION_CHANNEL");
	    System.out.println(res.getString(0));

	}

}
