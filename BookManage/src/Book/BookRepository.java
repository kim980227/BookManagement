package Book;

import jdbc.JdbcComm;

import java.sql.*;
import java.sql.Statement;
import java.util.LinkedList;

public class BookRepository {

    public LinkedList<Book> getAllByIdAndCategoryAndName() throws SQLException {
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
        resultSet.close();
        statement.close();
        jdbc.closeConnection();
        return liBook;
    }
}
