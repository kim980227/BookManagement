package Service;

import Book.*;
import BookRent.BookRent;

import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Rent {
    static BookRepository bookRepository = new BookRepository();

    public static void main(String[] args) throws SQLException {

        setRent();
//        setReturn();
    }

    BookRent getRentList(){
        return new BookRent();
    }
    /**
     * 대출
     *
     * @return
     * @throws SQLException
     */
    public static boolean setRent() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("사용자 명을 입력하세요: ");
        String userName = scanner.nextLine();

        // 한국소설 1, 자기개발 1
        System.out.print("대출할 책을 입력하세요: ");
        String bookName = scanner.nextLine();
        Book book = bookRepository.getBookQtyByBookName(bookName);
        if (book == null || book.getQty() <= 0) {
            System.out.println("책이 없습니다.");
            return false;
        }
        System.out.println("책 개수: " + book.getQty());

        // 책 대출 로직 start
        LocalDate currentDate = LocalDate.now();
        // 7일을 더한 날짜 계산
        LocalDate futureDate = currentDate.plusDays(7);
        // 날짜 포맷 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 문자열로 변환
        String futureDateStr = futureDate.format(formatter);
        BookRent bookRent = new BookRent();
        bookRent.setBook_id(book.getBookId());
        bookRent.setEnd_date(Date.valueOf(futureDateStr));
        bookRent.setUser_name(userName);

        StringBuffer sb = new StringBuffer("");
        try {
            Reader r = new FileReader("rent.txt");
            BufferedReader br = new BufferedReader(r);
            // 한 줄씩 읽기
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
                String[] arr = line.split("\\|\\s*");
            }
            sb.append(bookRent.getUser_name()).append("|").append(bookRent.getBook_id()).append("|").append(bookRent.getEnd_date()).append("|").append(bookRent.getRent_pay());
            Writer writer = new FileWriter("rent.txt");
            writer.write(sb.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 책 대출 로직 end

        // 수량 업데이트
        book.setQty(book.getQty() - 1);
        int result = bookRepository.updateQty(book);
        if (!(result > 0)) {
            System.out.println("업데이트 실패");
            return false;
        }

        return true;
    }

    /**
     * 반납
     *
     * @return
     * @throws SQLException
     */
    public static boolean setReturn() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("사용자 명을 입력하세요: ");
        String userName = scanner.nextLine();

        // 한국소설 1
        System.out.print("반납할 책을 입력하세요: ");
        String bookName = scanner.nextLine();

        Book book = bookRepository.getBookQtyByBookName(bookName);
        if (book == null || book.getQty() <= 0) {
            System.out.println("책이 없습니다.");
            return false;
        }

        LocalDate currentDate = LocalDate.now();
        StringBuffer sb = new StringBuffer("");
        boolean whileResult = false;

        try {
            Reader r = new FileReader("rent.txt");
            BufferedReader br = new BufferedReader(r);
            // 한 줄씩 읽기
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split("\\|\\s*");

                BookRent bookRent = new BookRent(arr[0], Integer.parseInt(arr[1]), Date.valueOf(arr[2]), Integer.parseInt(arr[3]));

                if (Objects.equals(bookRent.getUser_name(), userName) && Objects.equals(bookRent.getBook_id(), book.getBookId())) {

                    long i = ChronoUnit.DAYS.between(bookRent.getEnd_date().toLocalDate(), currentDate);

                    if (i > 0) {
                        System.out.println("연체일은 " + i);
                        System.out.println("연체료는 " + bookRent.getRent_pay() * i + "원 입니다.");
                    }

                    System.out.println("반납 완료");
                    whileResult = true;
                } else {
                    sb.append(line).append("\n");
                }
            }

            Writer writer = new FileWriter("rent.txt");
            writer.write(sb.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (!whileResult) {
            System.out.println("반납할 책이 없습니다.");
            return false;
        }

        // 수량 업데이트
        book.setQty(book.getQty() + 1);
        int result = bookRepository.updateQty(book);
        if (!(result > 0)) {
            System.out.println("업데이트 실패");
            return false;
        }

        return true;
    }
}
