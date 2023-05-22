package jdbc;

import java.sql.*;

public class JdbcComm {
    String url = "jdbc:mysql://localhost:3306/db01";
    String userName = "root";
    String password = "1234";

    Connection connection = DriverManager.getConnection(url, userName, password);

    public JdbcComm() throws SQLException {
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
