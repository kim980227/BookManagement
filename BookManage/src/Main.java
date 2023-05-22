import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/db01";
        String userName = "root";
        String password = "1234";

        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from t_book");

        resultSet.next();
        String book_id = resultSet.getString("book_name");
        System.out.println(book_id);

        resultSet.close();
        statement.close();
        connection.close();
    }
}