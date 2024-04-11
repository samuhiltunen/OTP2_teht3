package teht3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DatabaseConnection {
    private static final String DATABASE_URL = "jdbc:mariadb://localhost:3306/otp2_teht3";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "password";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }
}
