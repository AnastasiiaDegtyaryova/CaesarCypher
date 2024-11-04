package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cli {
    private static Cli instance;

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private Cli() {
    }

    public static Cli getInstance() {
        if (instance == null) {
            instance = new Cli();
        }
        return instance;
    }

    public String getUserInput(final String massage) {
        System.out.println(massage);
        try {
            return reader.readLine();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
