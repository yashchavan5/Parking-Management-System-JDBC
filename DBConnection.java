import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/parking_system";
    private static final String USER = "root";
    private static final String PASS = "Yashsql@1";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
