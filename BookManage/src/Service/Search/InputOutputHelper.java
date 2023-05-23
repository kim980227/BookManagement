package Service.Search;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class InputOutputHelper {
    public static void saveInputValues(String filePath, String[] inputValues1, String[] inputValues2, String[] inputValues3) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (String value : inputValues1) {
                writer.write(value);
                writer.newLine();
            }

            for (String value : inputValues2) {
                writer.write(value);
                writer.newLine();
            }

            for (String value : inputValues3) {
                writer.write(value);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printInputValues(String[] inputValues1, String[] inputValues2, String[] inputValues3) {
        for (String value : inputValues1) {
            System.out.println(value);
        }

        for (String value : inputValues2) {
            System.out.println(value);
        }

        for (String value : inputValues3) {
            System.out.println(value);
        }
    }
}