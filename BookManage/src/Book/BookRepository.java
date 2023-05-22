package Book;

import jdbc.JdbcComm;

import java.sql.*;
import java.sql.Statement;
import java.util.LinkedList;

public class BookRepository {

    public LinkedList<Book> getAllByIdAndCategoryAndName(int Limit) throws SQLException {
        JdbcComm jdbc = new JdbcComm();
        Statement statement = jdbc.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT book_id, category, book_name FROM t_book LIMIT" + Limit );
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

    public int insert(Book book) throws SQLException {
        JdbcComm jdbc = new JdbcComm();
        Statement statement = jdbc.getConnection().createStatement();

        String insertQuery = "INSERT INTO t_book (category, book_name, summary, author, publisher, in_date, is_in) " +
                "VALUES (" + book.getCategory() + ", '" + book.getBookName() + "', '" + book.getSummary() + "', '" + book.getAuthor() + "', '" + book.getPublisher() + "', '" + book.getInDate() + "', '" + book.getIsIn() + "')";

        int result = statement.executeUpdate(insertQuery);
        statement.close();
        jdbc.closeConnection();
        return result;
    }
}
