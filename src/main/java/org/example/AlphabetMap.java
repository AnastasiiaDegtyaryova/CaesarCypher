package org.example;

import java.util.HashMap;
import java.util.Map;

public class AlphabetMap {
    public static final String ALPHABET = "абвгґдеєжзийіїйклмнопрстуфхцчшщьюяАБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.,«»\"':!? ";
    public static final int TOTAL_LENGTH = ALPHABET.length();

    public static Map<Character, Character> createAlphabetMap(int shift) {
        Map<Character, Character> map = new HashMap<>();

        shift = (shift % TOTAL_LENGTH + TOTAL_LENGTH) % TOTAL_LENGTH;

        for (int i = 0; i < ALPHABET.length(); i++) {
            char currentChar = ALPHABET.charAt(i);
            char shiftedChar = ALPHABET.charAt((i + shift) % TOTAL_LENGTH);
            map.put(currentChar, shiftedChar);
        }
        return map;
    }
}

