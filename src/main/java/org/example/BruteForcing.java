package org.example;

import java.util.Map;

public class BruteForcing {
    public static String bruteForce(String encryptedText) {

        for (int shift = 1; shift < AlphabetMap.TOTAL_LENGTH; shift++) {
            Map<Character, Character> cipherMap = AlphabetMap.createAlphabetMap(-shift);
            StringBuilder decryptedText = new StringBuilder();

            for (char ch : encryptedText.toCharArray()) {
                decryptedText.append(cipherMap.getOrDefault(ch, ch));
            }

            String decryptedString = decryptedText.toString();
            if (isValidDecryption(decryptedString)) {
                System.out.println("Автоматично знайдений зсув: " + shift);
                return decryptedString;
            }
        }

        return "Не вдалося знайти правильний зсув.";
    }

    private static boolean isValidDecryption(String text) {
        for (int i = 0; i < text.length() - 2; i++) {

            if (text.charAt(i) == '.' && text.charAt(i + 1) == ' ' && Character.isUpperCase(text.charAt(i + 2))) {
                return true;
            }
            else if (text.charAt(i) == ',' && text.charAt(i + 1) == ' ') {
                return true;
            }
            else if (text.charAt(i) == ',' && Character.isUpperCase(text.charAt(i + 1))) {
                return false;
            }
            else if ((text.charAt(i) == '.' || text.charAt(i) == ',' || text.charAt(i) == '!' || text.charAt(i) == '?') &&
                    (text.charAt(i + 1) == '.' || text.charAt(i + 1) == ',' || text.charAt(i + 1) == '!' || text.charAt(i + 1) == '?')) {
                return false;
            }
        }
        return false;
    }
}
