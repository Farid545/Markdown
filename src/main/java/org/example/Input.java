package org.example;

import org.example.constant.Constants;
import org.example.enums.MessageCodes;
import org.example.exception.InvalidFileFormatException;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {
    private static Scanner scanner = new Scanner(System.in);
    private static String filePath = "";

    public static void markdownPath() {
        Pattern pattern = Pattern.compile(Constants.FILE_TYPE);
        Matcher matcher = pattern.matcher(filePath);

        System.out.println("*** Формат введення файлу - .txt ***");
        System.out.print("Введіть шлях до файлу: ");
        filePath = scanner.next();

        if (filePath.equalsIgnoreCase("exit"))
            System.exit(1);

        try {
            if (matcher.find()) {
                // Продовжуємо роботу над файлом
            } else
                throw new InvalidFileFormatException(MessageCodes.NOT_FOUND_FILE);
        } catch (InvalidFileFormatException e) {
            markdownPath();
        }

    }
}
