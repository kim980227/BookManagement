import java.sql.*;
import java.util.Scanner;

public class Inset {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/db01";
        String userName = "root";
        String password = "1234";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             Statement statement = connection.createStatement()) {

            Scanner scanner = new Scanner(System.in);

            System.out.print("카테고리 입력 (10: 소설, 11: 한국소설, 20: 역사, 30: 자기개발, 40: 종교 ...): ");
            int category = scanner.nextInt();
            scanner.nextLine(); // 개행문자 처리

            System.out.print("책 이름 입력: ");
            String bookName = scanner.nextLine();

            System.out.print("한 줄 요약 입력: ");
            String summary = scanner.nextLine();

            System.out.print("저자 입력: ");
            String author = scanner.nextLine();

            System.out.print("출판사 입력: ");
            String publisher = scanner.nextLine();

            System.out.print("입고 날짜 입력 (YYYY-MM-DD): ");
            String inDate = scanner.nextLine();

            System.out.print("입고 여부 입력 (T/F): ");
            String isIn = scanner.nextLine();

            String insertQuery = "INSERT INTO t_book (category, book_name, summary, author, publisher, in_date, is_in) " +
                    "VALUES (" + category + ", '" + bookName + "', '" + summary + "', '" + author + "', '" + publisher + "', '" + inDate + "', '" + isIn + "')";

            int rowsAffected = statement.executeUpdate(insertQuery);
            System.out.println(rowsAffected + " row(s) affected.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
