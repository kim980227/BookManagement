package Service;

import Book.Book;
import Book.BookRepository;
import BookSaleHistory.BookSaleHistory;

import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Sale {
    static BookRepository bookRepository = new BookRepository();

    public static void main(String[] args) throws SQLException {
        setSaleHistory();
    }

    static boolean setSaleHistory() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        BookSaleHistory bookSaleHistory = new BookSaleHistory();
        System.out.print("사용자 명을 입력하세요: ");
        String userName = scanner.nextLine();

        // 한국소설 1, 자기개발 1
        System.out.print("구입할 책을 입력하세요: ");
        String bookName = scanner.nextLine();
        Book book = bookRepository.getBookQtyByBookName(bookName);
        if (book == null || book.getQty() <= 0) {
            System.out.println("책이 없습니다.");
            return false;
        }
        System.out.println("책 개수: " + book.getQty());

        System.out.print("구입할 권수를 입력하세요: ");
        int buyQty = Integer.parseInt(scanner.nextLine());
        bookSaleHistory.setBookId(book.getBookId());
        bookSaleHistory.setUser_name(userName);
        bookSaleHistory.setQty(buyQty);
        bookSaleHistory.setPurchasePrice(book.getPurchasePrice());
        bookSaleHistory.setSellingPrice(book.getSellingPrice());
        bookSaleHistory.setSale_date(Date.valueOf(LocalDate.now()));

        StringBuffer sb = new StringBuffer("");
        try {
            Reader r = new FileReader("book_sale_history.txt");
            BufferedReader br = new BufferedReader(r);
            // 한 줄씩 읽기
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            sb.append(bookSaleHistory.getSale_date()).append("|")
                    .append(bookSaleHistory.getBookId()).append("|")
                    .append(bookSaleHistory.getUser_name()).append("|")
                    .append(bookSaleHistory.getQty()).append("|")
                    .append(bookSaleHistory.getPurchasePrice()).append("|")
                    .append(bookSaleHistory.getSellingPrice());

            Writer writer = new FileWriter("book_sale_history.txt");
            writer.write(sb.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        // 수량 업데이트
        book.setQty(book.getQty() - buyQty);
        int result = bookRepository.updateQty(book);
        if (!(result > 0)) {
            System.out.println("업데이트 실패");
            return false;
        }

        return true;
    }

    static void setStat() {

    }
}
