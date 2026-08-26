package components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {

	public static Connection getConnection() throws SQLException {
		
		String url = "jdbc:mysql://localhost:3306/qadb";

        String username = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");
        
		return DriverManager.getConnection(
				url,
				username,
				password
				);
	}
	
}
