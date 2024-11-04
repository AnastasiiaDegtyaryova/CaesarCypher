package org.example;

import java.io.File;

public class Runner {

    private static Runner instance;

    private Runner() {
    }

    public static Runner getInstance() {
        if (instance == null) {
            instance = new Runner();
        }
        return instance;
    }

    public void run(final String[] commands) {
        final Command command = checkCommand(commands[0]);
        final File file = checkFilePath(commands[1]);
        final int shift = command == Command.BRUTE_FORCE ? 0 : checkShift(commands);

        runCommand(command, file, shift);
    }

    public void runCli() {
        final Cli cli = Cli.getInstance();
        final Command command = checkCommand(cli.getUserInput("Write your command:"));
        final File file = checkFilePath(cli.getUserInput("Write file's path:"));
        final int shift = command == Command.BRUTE_FORCE ? 0 : checkShift(cli.getUserInput("Write your shift:"));

        runCommand(command, file, shift);
    }

    private void runCommand(final Command command, final File file, final int shift) {
        final String text = FileReading.readFromFile(file);
        switch (command) {
            case ENCRYPT:
                String encryptedText = CaesarCipher.processText(text, shift, Command.ENCRYPT);
                FileWriting.writeToFile(file, encryptedText, "[ENCRYPTED]");
                System.out.println("Зашифрований текст: " + encryptedText);
                break;

            case DECRYPT:
                String decryptedText = CaesarCipher.processText(text, shift, Command.DECRYPT);
                FileWriting.writeToFile(file, decryptedText, "[DECRYPTED]");
                System.out.println("Розшифрований текст: " + decryptedText);
                break;

            case BRUTE_FORCE:
                String autoDecryptedText = BruteForcing.bruteForce(text);
                FileWriting.writeToFile(file, autoDecryptedText, "[BRUTE_FORCED]");
                System.out.println("Розшифрований текст (Brute Force): " + autoDecryptedText);
                break;

            default:
                throw new IllegalArgumentException("Неправильна команда. Використовуйте ENCRYPT, DECRYPT або BRUTE_FORCE.");
        }
    }

    private int checkShift(final String[] commands) {
        if (commands.length != 3) {
            throw new IllegalArgumentException("Неправильна кількість аргументів.");
        }
        return checkShift(commands[2]);
    }

    private int checkShift(final String shift) {
        try {
            return Integer.parseInt(shift);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Зсув повинен бути цілим числом.");
        }
    }

    private File checkFilePath(final String filePath) {
        final File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("Файл не знайдено.");
        }
        return file;
    }

    private Command checkCommand(final String command) {
        try {
            return Command.valueOf(command);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Неправильна команда. Використовуйте ENCRYPT, DECRYPT або BRUTE_FORCE.");
        }
    }
}
