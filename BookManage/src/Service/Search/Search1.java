package Service.Search;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/////////////////////////////////////////////// 1차 항목 검색 & 2차 세부 검색 ( 고객용 )
public class Search1 {
    public static void main(String[] args) {
        // 데이터베이스 연결 정보
        String url = "jdbc:mysql://localhost:3306/db01";
        String username = "root";
        String password = "0000";

        // 한글 검색 항목과 영어 컬럼 이름 매핑
        Map<String, String> columnMap = new HashMap<>();
        columnMap.put("카테고리", "category");
        columnMap.put("책제목", "book_name");
        columnMap.put("요약", "summary");
        columnMap.put("출판사", "publisher");

        // 사용자 입력 받기
        Scanner scanner = new Scanner(System.in);
        System.out.print("검색을 원하는 항목을 고르시오 \n 1.카테고리 2.책제목 3.요약 4.출판사 \n");
        String searchTopic = scanner.nextLine();

        // 검색 항목으로 DB의 컬럼 선택
        String closestColumn = columnMap.get(searchTopic);
        if (closestColumn == null) {
            System.out.println("유효하지 않은 검색 항목입니다.");
            return;
        }
        System.out.println("선택된 컬럼: " + closestColumn);

        // 검색 항목 내 Keyword 재검색
        System.out.print("검색할 키워드 입력: ");
        String searchKeyword = scanner.nextLine();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스 연결
            connection = DriverManager.getConnection(url, username, password);

            // SQL 쿼리 실행
            String sql = "SELECT * FROM t_book WHERE " + closestColumn + " LIKE ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + searchKeyword + "%");
            resultSet = preparedStatement.executeQuery();

            // 결과 출력
            while (resultSet.next()) {
                int bookId = resultSet.getInt("book_id");
                int category_1 = resultSet.getInt("category_1");
                int category_2 = resultSet.getInt("category_2");
                String bookName = resultSet.getString("book_name");
                String summary = resultSet.getString("summary");
                String author = resultSet.getString("author");
                String publisher = resultSet.getString("publisher");

                // ... (필요한 열에 따라 추가)

                System.out.println("Book ID: " + bookId);
                System.out.println("Category_1: " + category_1);
                System.out.println("Category_2: " + category_2);
                System.out.println("Book Name: " + bookName);
                System.out.println("Summary: " + summary);
                System.out.println("Author: " + author);
                System.out.println("Publisher: " + publisher);
                // ... (필요한 열에 따라 출력)

                System.out.println("-------------------------");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 리소스 해제
            try {
                if (resultSet != null) {
                    resultSet.close();
                    System.out.println("검색결과가 없습니다.");
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}