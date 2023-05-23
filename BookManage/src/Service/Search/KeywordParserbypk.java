package Service.Search;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class KeywordParserbypk {
    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\asdf\\Desktop\\dx_servlet\\pkkeyword.txt";
        String outputFilePath = "C:\\Users\\asdf\\Desktop\\dx_servlet\\키워드파서";

        try {
            Map<String, Integer> keywordCountMap = parseKeywords(inputFilePath);
            writeKeywordCounts(outputFilePath, keywordCountMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Integer> parseKeywords(String filePath) throws IOException {
        Map<String, Integer> keywordCountMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String keyword = parts[0].trim();
                    int count = Integer.parseInt(parts[1].trim());
                    keywordCountMap.put(keyword, keywordCountMap.getOrDefault(keyword, 0) + count);
                }
            }
        }

        return keywordCountMap;
    }

    private static void writeKeywordCounts(String filePath, Map<String, Integer> keywordCountMap) throws IOException {
        Map<String, Integer> sortedMap = new TreeMap<>((k1, k2) -> keywordCountMap.get(k2).compareTo(keywordCountMap.get(k1)));

        sortedMap.putAll(keywordCountMap);

        try (FileWriter writer = new FileWriter(filePath)) {
            for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + System.lineSeparator());
            }
        }
    }
}