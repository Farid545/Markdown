package org.example.filework;

import org.example.constant.Constants;
import org.example.enums.MessageCodes;
import org.example.exception.InvalidFileFormatException;
import org.example.exception.InvalidTextFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    public static void markdownPath() throws InvalidTextFormatException {
        String filePath = "";

        System.out.println("*** Формат введення файлу - .txt ***");
        System.out.print("Введіть шлях до файлу: ");
        filePath = scanner.next();

        Pattern pattern = Pattern.compile(Constants.FILE_TYPE);
        Matcher matcher = pattern.matcher(filePath);

        if (filePath.equalsIgnoreCase("exit"))
            System.exit(1);

        try {
            if (matcher.find()) {
                FileProceed.fileProcessing(filePath);


            } else
                throw new InvalidFileFormatException(MessageCodes.NOT_FOUND_FILE);
        } catch (InvalidFileFormatException e) {
            markdownPath();
        } catch (InvalidTextFormatException e) {
            throw new InvalidTextFormatException(MessageCodes.INVALID_TEXT_FORMAT);
        }

    }

    private static File createFileByPath(String filePath) throws FileNotFoundException {
        File file = new File(filePath);

        if (!file.exists())
            throw new FileNotFoundException();

        return file;
    }
}
