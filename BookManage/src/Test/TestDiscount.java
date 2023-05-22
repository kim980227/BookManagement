package Test;

import Book.BookRepository;

import java.math.BigDecimal;
import java.sql.SQLException;

public class TestDiscount {
    public static void main(String[] args) throws SQLException {
        BookRepository bookRepository = new BookRepository();
        bookRepository.applyDiscount(3);
    }
}
