package Service.Search;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class KeywordParserByPK {
    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\asdf\\Desktop\\dx_servlet\\pkkeyword.txt";
        String outputDirectory = "C:\\Users\\asdf\\Desktop\\dx_servlet\\parser";

        try {
            Map<Integer, StringBuilder> keywordMap = parseKeywords(inputFilePath);
            writeKeywordFiles(outputDirectory, keywordMap);
            System.out.println("파일 처리가 완료되었습니다.");
        } catch (IOException e) {
            System.out.println("파일 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    private static Map<Integer, StringBuilder> parseKeywords(String inputFilePath) throws IOException {
        Map<Integer, StringBuilder> keywordMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    int pk = Integer.parseInt(parts[1].trim());
                    String keyword = parts[0].trim();

                    keywordMap.computeIfAbsent(pk, k -> new StringBuilder()).append(keyword).append("\n");
                }
            }
        }

        return keywordMap;
    }

    private static void writeKeywordFiles(String outputDirectory, Map<Integer, StringBuilder> keywordMap) throws IOException {
        for (Map.Entry<Integer, StringBuilder> entry : keywordMap.entrySet()) {
            int pk = entry.getKey();
            StringBuilder keywords = entry.getValue();

            String outputFilePath = outputDirectory + "\\" + pk + ".txt";
            try (FileWriter writer = new FileWriter(outputFilePath)) {
                writer.write(keywords.toString());
            }
        }
    }
}
