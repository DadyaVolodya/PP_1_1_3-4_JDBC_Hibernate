package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/Connection2";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Xapakupu11vtczwtd@@";

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public static Statement createStatement(Connection connection) throws SQLException {
        try {
            return connection.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
