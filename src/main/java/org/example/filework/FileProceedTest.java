package org.example.filework;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileProceedTest {

    private static Pattern pattern;
    private static Matcher matcher;

    public static void fileProcessing(String filePath) {
        String content = readFileToString(filePath);
//        content = convertForBold(content);


    }

    private static String readFileToString(String filePath) {
        String content;
        try {
            Path path = Paths.get(filePath);
            byte[] bytesContent = Files.readAllBytes(path);
            content = new String(bytesContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return content;
    }

    private static byte[] readMarkdown(byte[] content) {
        int boldCounter = 0;
        int italicCounter = 0;
        int monoCounter = 0;
        List<String> bolds = new ArrayList<>();
        List<String> italic = new ArrayList<>();
        List<String> mono = new ArrayList<>();
        for (int i = 0; i < content.length; i++) {
            if (content[i] == '*' && content[i+1] == '*') {
//                Arrays.
                boldCounter++;
                if (boldCounter%2==1)
                    if (content[i + 2] != ' ')
                        bolds.add("<b>");
                if (boldCounter%2==0)
                    if (content[i-2] != ' ')
                        bolds.add("</b>");
            } else if (content[i] == '_' && content[i+1] == '_') {
                italicCounter++;
                if (italicCounter%2==1)
                    if (content[i + 2] != ' ')
                        italic.add("<i>");
                if (italicCounter%2==0)
                    if (content[i-2] != ' ')
                        italic.add("</i>");
            }
        }

        return null;
    }

    private static void writeMarcdown() {

    }

}
