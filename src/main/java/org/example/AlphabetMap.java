package org.example;

import java.util.HashMap;
import java.util.Map;

public class AlphabetMap {
    public static final String ALPHABET = "абвгґдеєжзийіїйклмнопрстуфхцчшщьюяАБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.,«»\"':!? ";
    public static final int TOTAL_LENGTH = ALPHABET.length();

    public static Map<Character, Character> createAlphabetMap(int shift) {
        Map<Character, Character> map = new HashMap<>();

        int newShift = (shift % TOTAL_LENGTH + TOTAL_LENGTH) % TOTAL_LENGTH;

        for (int i = 0; i < TOTAL_LENGTH; i++) {
            char currentChar = ALPHABET.charAt(i);
            char shiftedChar = ALPHABET.charAt((i + newShift) % TOTAL_LENGTH);
            map.put(currentChar, shiftedChar);
        }
        return map;
    }

    public static Map<Character, Character> createReverseMap(Map<Character, Character> originalMap) {
        Map<Character, Character> reverseMap = new HashMap<>();
        for (Map.Entry<Character, Character> entry : originalMap.entrySet()) {
            reverseMap.put(entry.getValue(), entry.getKey());
        }
        return reverseMap;
    }
}

