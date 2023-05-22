import jdbc.JdbcComm;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

import Book.*;

public class Select1 {
    public static void main(String[] args) throws SQLException {

        JdbcComm jdbc = new JdbcComm();
        Statement statement = jdbc.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT book_id, category, book_name FROM t_book");
        LinkedList<Book> liBook = new LinkedList<>();

        while (resultSet.next()) {
            Book book = new Book();
            book.setBookId(resultSet.getInt("book_id"));
            book.setCategory(resultSet.getInt("category"));
            book.setBookName(resultSet.getString("book_name"));

            liBook.add(book);
        }

        for (Book b : liBook) System.out.println(b);

        resultSet.close();
        statement.close();
        jdbc.closeConnection();
    }
}