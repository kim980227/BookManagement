package Service;

import Book.Book;
import Book.BookRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;

public class Insert {
    public static void main(String[] args) throws SQLException {
        BookRepository bookRepository = new BookRepository();

//        Scanner scanner = new Scanner(System.in);
//        System.out.print("카테고리 입력 (10: 소설, 11: 한국소설, 20: 역사, 30: 자기개발, 40: 종교 ...): ");
//        int category = scanner.nextInt();
//        scanner.nextLine(); // 개행문자 처리
//
//        System.out.print("책 이름 입력: ");
//        String bookName = scanner.nextLine();
//
//        System.out.print("한 줄 요약 입력: ");
//        String summary = scanner.nextLine();
//
//        System.out.print("저자 입력: ");
//        String author = scanner.nextLine();
//
//        System.out.print("출판사 입력: ");
//        String publisher = scanner.nextLine();
//
//        System.out.print("입고 날짜 입력 (YYYY-MM-DD): ");
//        String inDate = scanner.nextLine();
//
//        System.out.print("입고 여부 입력 (T/F): ");
//        String isIn = scanner.nextLine();
//
//        Book book = new Book();
//
//        book.setCategory(category);
//        book.setBookName(bookName);
//        book.setSummary(summary);
//        book.setAuthor(author);
//        book.setPublisher(publisher);
//        book.setInDate(Date.valueOf(inDate));
//        book.setIsIn(isIn.charAt(0));
//
//        int result = bookRepository.insert(book);
//
//        if (result > 0) {
//            System.out.println("성공");
//        } else {
//            System.out.println("실패");
//        }
    }
}
