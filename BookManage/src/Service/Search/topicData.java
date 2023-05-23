package Service.Search;


import java.io.FileWriter;
import java.io.IOException;

public class topicData {
    public static void saveInputValue(String filePath, String inputValue, String fileName) {
        try {
            FileWriter writer = new FileWriter(filePath + "\\" + fileName, true);
            writer.write(inputValue + System.lineSeparator());
            writer.close();
            System.out.println("입력값이 파일에 저장되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printInputValue(String inputValue) {
        System.out.println(inputValue);
    }
}