import java.sql.*;

public class Select2 {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/db01";
        String userName = "root";
        String password = "1234";

        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM t_book WHERE category = 10");
        while (resultSet.next()) {
            int bookId = resultSet.getInt("book_id");
            String bookName = resultSet.getString("book_name");
            String summary = resultSet.getString("summary");
            String author = resultSet.getString("author");
            // ... 다른 컬럼들도 필요에 따라 가져올 수 있습니다.
            System.out.println("Book ID: " + bookId + ", Book Name: " + bookName + ", Summary: " + summary + ", Author: " + author);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
