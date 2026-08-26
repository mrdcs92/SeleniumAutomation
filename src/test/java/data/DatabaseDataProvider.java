package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;

import components.DatabaseUtils;

public class DatabaseDataProvider {

	@DataProvider(name = "testinfodata")
	public Object[][] getTestInfoData() throws SQLException {

		List<Object[]> data = new ArrayList<>();

		try (Connection con = DatabaseUtils.getConnection();
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery("SELECT email, password, product from testinfo")) {
			while (rs.next()) {
				HashMap<String, String> map = new HashMap<>();

				map.put("email", rs.getString("email"));
				map.put("password", rs.getString("password"));
				map.put("product", rs.getString("product"));
				
				data.add(new Object[] { map });
			}
		}

		return data.toArray(new Object[0][]);

	}
}
