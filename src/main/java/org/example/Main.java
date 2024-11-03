package org.example;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Використання: java -jar myApp.jar <ENCRYPT/DECRYPT/BRUTE_FORCE> <filePath> [key]");
            return;
        }

        String command = args[0];
        String filePath = args[1];
        Integer shift = null;

        if (command.equals("ENCRYPT") || command.equals("DECRYPT")) {
            if (args.length < 3) {
                System.out.println("Для команд ENCRYPT та DECRYPT необхідно вказати ключ (зсув).");
                return;
            }
            try {
                shift = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.out.println("Ключ (зсув) має бути цілим числом.");
                return;
            }
        }

        String text = FileReading.readFromFile(filePath);
        if (text.isEmpty()) {
            System.out.println("Файл порожній або не вдалося прочитати вміст.");
            return;
        }

        switch (command) {
            case "ENCRYPT":
                String encryptedText = CaesarCipher.processText(text, shift, Command.ENCRYPT);
                FileWriting.writeToFile(filePath, encryptedText, "[ENCRYPTED]");
                System.out.println("Зашифрований текст: " + encryptedText);
                break;

            case "DECRYPT":
                String decryptedText = CaesarCipher.processText(text, shift, Command.DECRYPT);
                FileWriting.writeToFile(filePath, decryptedText, "[DECRYPTED]");
                System.out.println("Розшифрований текст: " + decryptedText);
                break;

            case "BRUTE_FORCE":
                String autoDecryptedText = BruteForcing.bruteForce(text);
                FileWriting.writeToFile(filePath, autoDecryptedText, "[BRUTE_FORCED]");
                System.out.println("Розшифрований текст (Brute Force): " + autoDecryptedText);
                break;

            default:
                System.out.println("Неправильна команда. Використовуйте ENCRYPT, DECRYPT або BRUTE_FORCE.");
        }
    }
}
