package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriting {

    public static void writeToFile(String originalFilePath, String content, String suffix) {
        String outputPath;
        if (suffix.equals("[DECRYPTED]") && originalFilePath.contains("[ENCRYPTED]")) {
            outputPath = originalFilePath.replace("[ENCRYPTED]", "[DECRYPTED]");
        } else {
            outputPath = addSuffixToFileName(originalFilePath, suffix);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath.toString()))) {
            writer.write(content);
            System.out.println("Записано у файл: " + outputPath);
        } catch (IOException e) {
            System.out.println("Помилка при записі у файл: " + e.getMessage());
        }
    }

    private static String addSuffixToFileName(String filePath, String suffix) {
        int dotIndex = filePath.lastIndexOf('.');
        if (dotIndex != -1) {
            return filePath.substring(0, dotIndex) + suffix + filePath.substring(dotIndex);
        } else {
            return filePath + suffix;
        }
    }
}

