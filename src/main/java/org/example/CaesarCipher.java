package org.example;

import java.util.HashMap;
import java.util.Map;

public class CaesarCipher {

    public static String processText(String text, int shift, Command command) {
        StringBuilder result = new StringBuilder();
        Map<Character, Character> cipherMap = AlphabetMap.createAlphabetMap(shift);;

        switch (command) {
            case ENCRYPT:
                for (char ch : text.toCharArray()) {
                    result.append(cipherMap.getOrDefault(ch, ch));
                }
                break;
            case DECRYPT:
                Map<Character, Character> decryptMap = createReverseMap(cipherMap);
                for (char ch : text.toCharArray()) {
                    result.append(decryptMap.getOrDefault(ch, ch));
                }
                break;
            case BRUTE_FORCE:
                result.append(BruteForcing.bruteForce(text));
                break;
            default:
                System.out.println("Invalid command. Use ENCRYPT, DECRYPT, or BRUTE_FORCE.");
        }

        return result.toString();
    }

    private static Map<Character, Character> createReverseMap(Map<Character, Character> originalMap) {

        Map<Character, Character> reverseMap = new HashMap<>();
        for (Map.Entry<Character, Character> entry : originalMap.entrySet()) {
            reverseMap.put(entry.getValue(), entry.getKey());
        }
        return reverseMap;
    }
}