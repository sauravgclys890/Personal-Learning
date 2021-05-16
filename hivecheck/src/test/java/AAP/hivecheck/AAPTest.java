package AAP.hivecheck;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AAPTest {

	public static void main(String[] args) {
		System.out.println("Saurav");
		try {
			Class.forName("org.apache.hive.jdbc.HiveDriver");
			Connection con =DriverManager.getConnection("jdbc:hive2://hadoop-gateway.openshift.sdntest.netcracker.com:10000/raw","nc_admin","nc_admin");
		    System.out.println(con);
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

}
