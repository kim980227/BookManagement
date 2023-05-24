package Service.Search;


import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class Search3 {
    private static boolean isValidSearchTopic(String searchTopic, Map<String, String> columnMap) {
        return columnMap.containsKey(searchTopic);
    }

    private static boolean isValidCategoryOption(String categoryOption) {
        return categoryOption.equals("소설") || categoryOption.equals("비소설");
    }

    public static void main(String[] args) {
        // 데이터베이스 연결 정보
        String url = "jdbc:mysql://localhost:3306/db01";
        String username = "root";
        String password = "1234";

        // 한글 검색 항목과 영어 컬럼 이름 매핑
        Map<String, String> columnMap = new HashMap<>();
        columnMap.put("카테고리", "category");
        columnMap.put("책제목", "book_name");
        columnMap.put("내용", "summary");
        columnMap.put("출판사", "publisher");
        columnMap.put("소설", "category_1");
        columnMap.put("비소설", "category_2");

        ////////////////////// 사용자 입력 받기
        Scanner scanner = new Scanner(System.in);
        System.out.print("검색을 원하는 항목을 고르시오 \n 1.카테고리 2.책제목 3.내용 4.출판사 \n");
        String searchTopic = scanner.nextLine();
        String selectedColumn = columnMap.get(searchTopic);
        String searchKeyword;
        String categoryOption = null;

        while (!isValidSearchTopic(searchTopic, columnMap)) {
            System.out.println("유효하지 않은 검색 항목입니다. 다시 입력해주세요.");
            searchTopic = scanner.nextLine();
        }

        String closestColumn = columnMap.get(searchTopic);

        if (closestColumn == null) {
            System.out.println("유효하지 않은 검색 항목입니다.");
            return;
        }

        if (!searchTopic.equals("카테고리")) {
            System.out.println(selectedColumn + " 내에서 결과를 재검색합니다.");

            System.out.print("검색할 키워드 입력: ");
            searchKeyword = scanner.nextLine();

            // 검색 결과를 저장할 리스트
            List<String> matchedKeywords = new ArrayList<>();

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

                    ///pk 별 키워드 검색 데이터 수집
                    String dataToSave = searchKeyword + ":" + bookId;
                    String filePath = "C:\\Users\\wkdtn\\Desktop\\dx_servlet";
                    String fileName = "pkkeyword.txt";
                    saveDataToFile(filePath, fileName, dataToSave);


                    ///
                    System.out.println("Book ID: " + bookId);
                    System.out.println("Category_1: " + category_1);
                    System.out.println("Category_2: " + category_2);
                    System.out.println("Book Name: " + bookName);
                    System.out.println("Summary: " + summary);
                    System.out.println("Author: " + author);
                    System.out.println("Publisher: " + publisher);
                    // ... (필요한 열에 따라 출력)

                    // 검색된 키워드를 리스트에 추가
                    matchedKeywords.add(searchKeyword);

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

            // 검색된 키워드를 파일에 저장
            if (!matchedKeywords.isEmpty()) {
                saveKeywordsToFile(matchedKeywords, "검색된_키워드.txt");
            }
        } else {
            if (searchTopic.equals("카테고리")) {
                System.out.print("세부 카테고리를 선택하십시오 \n 1.소설 2.비소설 \n");
                categoryOption = scanner.nextLine();

                while (!isValidCategoryOption(categoryOption)) {
                    System.out.println("유효하지 않은 선택입니다. 다시 입력해주세요.");
                    categoryOption = scanner.nextLine();
                }

                if (categoryOption.equals("소설")) {
                    closestColumn = "category_1";
                    categoryOption = "소설";
                    System.out.println(categoryOption + " 내에서 결과를 재검색합니다.");
                } else if (categoryOption.equals("비소설")) {
                    closestColumn = "category_2";
                    categoryOption = "비소설";
                    System.out.println(categoryOption + " 내에서 결과를 재검색합니다.");
                } else {
                    return;
                }
            }

            System.out.print("검색할 키워드 입력: ");
            searchKeyword = scanner.nextLine();

            // 검색 결과를 저장할 리스트
            List<String> matchedKeywords = new ArrayList<>();

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

                    ///pk 별 키워드 검색 데이터 수집
                    String dataToSave = searchKeyword + ":" + bookId;
                    String filePath = "C:\\Users\\wkdtn\\Desktop\\dx_servlet";
                    String fileName = "pkkeyword.txt";
                    saveDataToFile(filePath, fileName, dataToSave);

                    // ... (필요한 열에 따라 추가)

                    System.out.println("Book ID: " + bookId);
                    System.out.println("Category_1: " + category_1);
                    System.out.println("Category_2: " + category_2);
                    System.out.println("Book Name: " + bookName);
                    System.out.println("Summary: " + summary);
                    System.out.println("Author: " + author);
                    System.out.println("Publisher: " + publisher);
                    // ... (필요한 열에 따라 출력)

                    // 검색된 키워드를 리스트에 추가
                    matchedKeywords.add(searchKeyword);

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

            // 검색된 키워드를 파일에 저장
            if (!matchedKeywords.isEmpty()) {
                saveKeywordsToFile(matchedKeywords, "검색된_키워드.txt");
            }
        }
        // ... (나머지 코드)
    }

    private static void saveKeywordsToFile(List<String> keywords, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String keyword : keywords) {
                writer.write(keyword + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void saveDataToFile(String filePath, String fileName, String data) {
        try {
            FileWriter writer = new FileWriter(filePath + "\\" + fileName, true);
            writer.write(data + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}