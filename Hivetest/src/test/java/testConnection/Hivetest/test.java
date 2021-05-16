package testConnection.Hivetest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class test {

	private static String driverClass = "org.apache.hive.jdbc.HiveDriver";
	public static void main(String[] args) {
	
		try {
            Class.forName(driverClass);
         
        Connection connection = DriverManager.getConnection("jdbc:hive2://hadoop-gateway.openshift.sdntest.netcracker.com:10000/raw", "nc_admin", "nc_admin");
        System.out.println(connection);
        Statement statement = connection.createStatement();
		}
        catch (ClassNotFoundException exception) {
        	 
            exception.printStackTrace();
        } catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
