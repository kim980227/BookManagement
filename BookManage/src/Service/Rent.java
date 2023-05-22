package Service;

import Book.BookRepository;

import java.io.*;
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
//        setRent();
        setReturn();
//        payRent();
    }

    public static boolean setRent() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("사용자 명을 입력하세요: ");
        String UserName = scanner.nextLine();

        // 한국소설 1, 자기개발 1
        System.out.print("대출할 책을 입력하세요: ");
        String bookName = scanner.nextLine();

        try {
            boolean b = bookRepository.validateName(bookName);
            if (!b) {
                System.out.println("책이없습니다.");
                return false;
            }
        } catch (SQLException e) {
            // SQL 예외 처리
            System.err.println("SQL 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }

        LocalDate currentDate = LocalDate.now();
        // 7일을 더한 날짜 계산
        LocalDate futureDate = currentDate.plusDays(7);
        // 날짜 포맷 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 문자열로 변환
        String futureDateStr = futureDate.format(formatter);


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

            sb.append(UserName).append("|").append(bookName).append("|").append(futureDateStr).append("|").append("0");

            Writer writer = new FileWriter("rent.txt");
            writer.write(sb.toString());
            writer.close();        } catch (Exception e) {

            e.printStackTrace();
        }
        return true;
    }

    public static boolean setReturn() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("사용자 명을 입력하세요: ");
        String userName = scanner.nextLine();

        // 한국소설 1
        System.out.print("반납할 책을 입력하세요: ");
        String bookName = scanner.nextLine();

        try {
            boolean b = bookRepository.validateName(bookName);
            if (!b) {
                System.out.println("책이없습니다.");
                return false;
            }
        } catch (SQLException e) {
            // SQL 예외 처리
            System.err.println("SQL 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }


        StringBuffer sb = new StringBuffer("");
        try {
            Reader r = new FileReader("rent.txt");
            BufferedReader br = new BufferedReader(r);
            // 한 줄씩 읽기
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split("\\|\\s*");
                System.out.println(Arrays.toString(arr));
                if (Objects.equals(arr[0], userName) && Objects.equals(arr[1], bookName)) {
                    if (Integer.parseInt(arr[3]) > 0) {
                        System.out.println("연체료는 " + arr[3] + "원 입니다.");
                    }
                    System.out.println("반납 완료");
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

        return true;
    }

    public static void payRent() {

        StringBuffer sb = new StringBuffer("");
        try {
            Reader r = new FileReader("rent.txt");
            BufferedReader br = new BufferedReader(r);
            LocalDate currentDate = LocalDate.now();

            // 한 줄씩 읽기
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split("\\|\\s*");
                String[] arrDate = arr[2].split("-");
                System.out.println(Arrays.toString(arrDate));
                LocalDate date2 = LocalDate.of(Integer.valueOf(arrDate[0]), Integer.valueOf(arrDate[1]), Integer.valueOf(arrDate[2]));
                // 날짜 간의 일수 차이 계산
                long daysBetween = ChronoUnit.DAYS.between(currentDate, date2);
                System.out.println(daysBetween);

            }

            Writer writer = new FileWriter("rent.txt");
            writer.write(sb.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
