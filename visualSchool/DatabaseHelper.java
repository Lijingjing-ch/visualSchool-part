package visualSchool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
	
	private static final String dbURL = "jdbc:ucanaccess://D:/����ѧУ-2/רҵʵѵ/Database1.accdb";

	public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbURL);
    }

}
