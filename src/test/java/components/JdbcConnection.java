package components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnection {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		String host = "localhost";
		String port = "3306";

		Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/qadb", "root",
				"19922dst!taN19922");

		Statement s = con.createStatement();

		ResultSet rs = s.executeQuery("select * from employeeInfo");

		while (rs.next()) {
			System.out.println("name: " + rs.getString("name"));
			System.out.println("id: " + rs.getString("id"));
			System.out.println("location: " + rs.getString("location"));
			System.out.println("age: " + rs.getString("age"));
		}

	}
}
