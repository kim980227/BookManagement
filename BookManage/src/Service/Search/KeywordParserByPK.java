package Service.Search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class KeywordParserByPK {
    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\asdf\\Desktop\\dx_servlet\\pkkeyword.txt";
        String outputDirectory = "C:\\Users\\asdf\\Desktop\\dx_servlet\\parser";

        try {
            Map<Integer, Map<String, Integer>> keywordMap = parseKeywords(inputFilePath);
            writeKeywordFiles(outputDirectory, keywordMap);
            System.out.println("파일 처리가 완료되었습니다.");
        } catch (IOException e) {
            System.out.println("파일 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    private static Map<Integer, Map<String, Integer>> parseKeywords(String inputFilePath) throws IOException {
        Map<Integer, Map<String, Integer>> keywordMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    int pk = Integer.parseInt(parts[1].trim());
                    String keyword = parts[0].trim();

                    if (!keywordMap.containsKey(pk)) {
                        keywordMap.put(pk, new HashMap<>());
                    }

                    Map<String, Integer> keywordCountMap = keywordMap.get(pk);
                    keywordCountMap.put(keyword, keywordCountMap.getOrDefault(keyword, 0) + 1);
                }
            }
        }

        return keywordMap;
    }

    private static void writeKeywordFiles(String outputDirectory, Map<Integer, Map<String, Integer>> keywordMap) throws IOException {
        for (Map.Entry<Integer, Map<String, Integer>> entry : keywordMap.entrySet()) {
            int pk = entry.getKey();
            Map<String, Integer> keywordCountMap = entry.getValue();

            /////// 내림차순 키워드 정렬
            List<Map.Entry<String, Integer>> keywordList = new ArrayList<>(keywordCountMap.entrySet());
            keywordList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

            String outputFilePath = outputDirectory + "\\" + pk + ".txt";
            try (FileWriter writer = new FileWriter(outputFilePath)) {
                for (Map.Entry<String, Integer> keywordEntry : keywordList) {
                    String keyword = keywordEntry.getKey();
                    int count = keywordEntry.getValue();
                    String line = keyword + ": " + count + "\n";
                    writer.write(line);
                }
            }
        }
    }
}
