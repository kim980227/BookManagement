import jdbc.JdbcComm;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        JdbcComm jdbc  = new JdbcComm();
        Statement statement = jdbc.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from t_book");


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

        jdbc.closeConnection();
    }
}