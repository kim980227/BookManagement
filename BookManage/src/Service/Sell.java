package Service;

import Book.Book;
import jdbc.JdbcComm;
import Book.BookRepository;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class Sell {
    // 책이름과 에디션으로 로우 구분하여 해당 책의 가격 리턴
    public BigDecimal sell(String book_name, int edition) throws SQLException {
        JdbcComm jdbc = new JdbcComm();
        Connection connection = jdbc.getConnection();
        Statement statement = connection.createStatement();

        String query = "UPDATE t_book SET qty = qty - 1 WHERE book_name = '" + book_name + "' AND edition = " + edition;
        statement.executeUpdate(query);

        query = "SELECT selling_price FROM t_book WHERE book_name = '" + book_name + "' AND edition = " + edition;
        ResultSet resultSet = statement.executeQuery(query);

        BigDecimal price = BigDecimal.valueOf(0.00);
        if (resultSet.next()) {
            price = resultSet.getBigDecimal("selling_price");
        }

        resultSet.close();
        statement.close();
        connection.close();
        return price;
    }

    // 사고싶은 Book list, discount_rate를 받아 최종 가격 리턴
    public BigDecimal sellALot(LinkedList<Book> books, BigDecimal discount_rate) throws SQLException {
        JdbcComm jdbc = new JdbcComm();
        Sell sell = new Sell();
        BigDecimal sum = BigDecimal.valueOf(0.00);

        for (Book book : books) {
            String bookName = book.getBookName();
            int edition = book.getEdition();
            sum = sum.add(sell.sell(bookName, edition));
        }

        sum = sum.multiply(discount_rate);

        return sum;
    }
}
