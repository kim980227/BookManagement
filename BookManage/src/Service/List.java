package Service;

import Book.*;

import java.sql.SQLException;
import java.util.LinkedList;

public class List {

    public static void main(String[] args) throws SQLException {
        BookRepository  bookRepository = new BookRepository();
        LinkedList<Book> liBook = bookRepository.getAllByIdAndCategoryAndName();
        for (Book b : liBook) System.out.println(b);
    }
}
