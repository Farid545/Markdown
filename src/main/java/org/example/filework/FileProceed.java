package org.example.filework;

import org.example.constant.Constants;
import org.example.enums.MessageCodes;
import org.example.exception.InvalidTextFormatException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileProceed {

    private static String content = "";
    private static List<String> regexes = new ArrayList<>();


    public static void fileProcessing(String filePath) throws InvalidTextFormatException {
        fillTheListOfMarkdowns();
        readFileToString(filePath);
        readMarkdownText(Constants.BOLD_TEXT_START, Constants.BOLD_TEXT_END,
                " <b>$2", "$1</b> "); // BOLD
        readMarkdownText(Constants.ITALIC_TEXT_START, Constants.ITALIC_TEXT_END,
                " <i>$2", "$1</i> "); // ITALIC
        readMarkdownText(Constants.MONO_TEXT_START, Constants.MONO_TEXT_END,
                " <tt>$2", "$1</tt> "); // MONO
        readMarkdownText(Constants.PRE_TEXT_START, Constants.PRE_TEXT_END,
                " <pre>$2", "$1</pre> ");

        System.out.println(content);
    }

    private static void readFileToString(String filePath) {
        try {
            Path path = Paths.get(filePath);
            byte[] bytesContent = Files.readAllBytes(path);
            content = new String(bytesContent);
            content = content.concat(" ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void fillTheListOfMarkdowns() {
        regexes.add(Constants.BOLD_TEXT_START);
        regexes.add(Constants.BOLD_TEXT_END);
        regexes.add(Constants.ITALIC_TEXT_START);
        regexes.add(Constants.ITALIC_TEXT_END);
        regexes.add(Constants.MONO_TEXT_START);
        regexes.add(Constants.MONO_TEXT_END);
    }

    private static void checkTheError(int startCounter, int endCounter, String regex, String replaceTo) throws InvalidTextFormatException {
        if (startCounter == endCounter)
            content = content.replaceAll(regex, replaceTo);
        else
            throw new InvalidTextFormatException(MessageCodes.INVALID_TEXT_FORMAT);
    }

    private static void readMarkdownText(String regexStart, String regexEnd, String replaceStart, String replaceEnd) throws InvalidTextFormatException {
        Pattern patternStart = Pattern.compile(regexStart);
        Pattern patternEnd = Pattern.compile(regexEnd);
        Matcher matcher;

        // Для відкриття
        matcher = patternStart.matcher(content);
        int startCounter = findReegax(matcher);

        // Для закриття
        matcher = patternEnd.matcher(content);
        int endCounter = findReegax(matcher);

        checkTheError(startCounter, endCounter, regexStart, replaceStart);
        checkTheError(startCounter, endCounter, regexEnd, replaceEnd);
    }

    private static int findReegax(Matcher matcher) {
        int counter = 0;
        while (matcher.find())
            counter++;

        return counter;
    }

}
