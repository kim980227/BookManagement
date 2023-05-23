package Book;

import jdbc.JdbcComm;

import java.math.BigDecimal;
import java.sql.*;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Scanner;

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

    public BigDecimal getDiscountRate(int book_id) throws SQLException {
        JdbcComm jdbc = new JdbcComm();
        Statement statement = jdbc.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT discount_rate FROM t_book where book_id = " + book_id);
        BigDecimal discountRate = null;
        while (resultSet.next()) {
            Book book = new Book();
//            book.setIsDeleted(resultSet.getString("is_deleted").charAt(0));
            book.setDiscountRate(resultSet.getBigDecimal("discount_rate"));
            discountRate = book.getDiscountRate();
        }
        resultSet.close();
        statement.close();
        jdbc.closeConnection();
        return discountRate;
    }

    // BOOK의 ID가 있는지 없는지 검사
    public boolean validateId(int book_id) throws SQLException {
        JdbcComm jdbc = new JdbcComm();
        Statement statement = jdbc.getConnection().createStatement();

        String query = "SELECT COUNT(*) AS count FROM t_book WHERE book_id = " + book_id;
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            int count = resultSet.getInt("count");
            return count > 0;
        }

        return false;
    }

    public boolean validateName(int book_name) throws SQLException {
        JdbcComm jdbc = new JdbcComm();
        Statement statement = jdbc.getConnection().createStatement();

        String query = "SELECT COUNT(*) AS count FROM t_book WHERE book_name = " + book_name;
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            int count = resultSet.getInt("count");
            return count > 0;
        }

        return false;
    }

    // book이 삭제되었는지 확인
    public boolean isDeleted(int bookId) throws SQLException {
        JdbcComm jdbc = new JdbcComm();
        Statement statement = jdbc.getConnection().createStatement();

        String query = "SELECT is_deleted FROM t_book WHERE book_id = " + bookId;
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            String isDeleted = resultSet.getString("is_deleted");
            return isDeleted.equalsIgnoreCase("T");
        }

        return false;
    }

    // 할인율 적용시켜서 Selling price 갱신
    public void applyDiscount(int bookId) throws SQLException {
        JdbcComm jdbc = new JdbcComm();
        Statement statement = jdbc.getConnection().createStatement();

        BigDecimal discountRate = getDiscountRate(bookId); // book에서 가져와야 됨

        // Update the selling price with the discount
        String updateQuery = "UPDATE t_book SET selling_price = selling_price * (1 - " + discountRate + ") WHERE book_id = " + bookId;
        int rowsAffected = statement.executeUpdate(updateQuery);

        if (rowsAffected > 0) {
            System.out.println("Selling price updated successfully!");
        } else {
            System.out.println("Book ID not found in the database.");
        }

        // Close resources
        statement.close();
        jdbc.closeConnection();
    }

    // id 받아서 is_deleted 값을 T
    public void deleteBook(int bookId) throws SQLException {
        JdbcComm jdbc = new JdbcComm();
        Statement statement = jdbc.getConnection().createStatement();

        // Update the row with deletion information
        String updateQuery = "UPDATE t_book SET is_deleted = 'T', deleted_ts = CURRENT_TIMESTAMP WHERE book_id = " + bookId;
        int rowsAffected = statement.executeUpdate(updateQuery);

        if (rowsAffected > 0) {
            System.out.println("Book ID " + bookId + " marked as deleted.");
        } else {
            System.out.println("Book ID not found in the database.");
        }

        // Close resources
        statement.close();
        jdbc.getConnection();
    }

    // Book의 제목, 내용, 저자 변경
    public void updateBook() throws SQLException {
        JdbcComm jdbc = new JdbcComm();
        Statement statement = jdbc.getConnection().createStatement();

        // Receive input from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the book ID to edit: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Retrieve the row based on the book ID
        String query = "SELECT * FROM t_book WHERE book_id = " + bookId;
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            // Retrieve the existing values from the row
            String bookName = resultSet.getString("book_name");
            String author = resultSet.getString("author");
            String publisher = resultSet.getString("publisher");

            // Display the existing values to the user
            System.out.println("Existing Book Name: " + bookName);
            System.out.println("Existing Author: " + author);
            System.out.println("Existing Publisher: " + publisher);

            // Receive new values from the user
            System.out.print("Enter the new Book Name: ");
            String newBookName = scanner.nextLine();
            System.out.print("Enter the new Author: ");
            String newAuthor = scanner.nextLine();
            System.out.print("Enter the new Publisher: ");
            String newPublisher = scanner.nextLine();

            // Update the row with the new values
            String updateQuery = "UPDATE t_book SET book_name = '" + newBookName +
                    "', author = '" + newAuthor +
                    "', publisher = '" + newPublisher +
                    "' WHERE book_id = " + bookId;
            statement.executeUpdate(updateQuery);

            System.out.println("Row updated successfully!");
        } else {
            System.out.println("Book ID not found in the database.");
        }

        // Close resources
        resultSet.close();
        statement.close();
        jdbc.getConnection();
        scanner.close();
    }
    public void discountOldBook(String bookName, BigDecimal discount_rate) throws SQLException {
        JdbcComm jdbc = new JdbcComm();
        Connection connection = jdbc.getConnection();
        Statement statement = connection.createStatement();

        // Find the highest edition for the given book name
        String maxEditionQuery = "SELECT MAX(edition) FROM t_book WHERE book_name = '" + bookName + "'";
        ResultSet maxEditionResult = statement.executeQuery(maxEditionQuery);
        int maxEdition = 0;
        if (maxEditionResult.next()) {
            maxEdition = maxEditionResult.getInt(1);
        }
        maxEditionResult.close();

        // Update the selling price of books with lower editions
        String updateQuery = "UPDATE t_book SET selling_price = selling_price * (1 - " + discount_rate + ") "
                + "WHERE book_name = '" + bookName + "' AND edition < " + maxEdition;


        int rowsAffected = statement.executeUpdate(updateQuery);

        if (rowsAffected > 0) {
            System.out.println("Selling price updated for older editions of the book: " + bookName);
        } else {
            System.out.println("No older editions found for the book: " + bookName);
        }

        // Close resources
        statement.close();
        connection.close();
    }

}